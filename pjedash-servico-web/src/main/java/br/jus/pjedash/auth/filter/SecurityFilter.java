package br.jus.pjedash.auth.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.joda.time.DateTime;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import br.jus.pjedash.auth.AuthUtils;
import br.jus.pjedash.entidade.Usuario;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

	@Context
    private UriInfo uriInfo;
	
	@Context
	private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

    	Method resourceMethod = resourceInfo.getResourceMethod();
    	Class<?> resourceClass = resourceInfo.getResourceClass();
    	
    	// Recupera as anota��es de seguran�a dos m�todos e classe do recurso
    	boolean rolesOnMethod = resourceMethod.isAnnotationPresent(RolesAllowed.class);
		boolean permitAllOnMethod = resourceMethod.isAnnotationPresent(PermitAll.class);
		boolean rolesOnClass = resourceClass.isAnnotationPresent(RolesAllowed.class);
		boolean permitAllOnClass = resourceClass.isAnnotationPresent(PermitAll.class);
		
		// Checa se � preciso autoriza��o para acessar o recurso. O fato do recurso possuir uma
		// das anota��es de seguran�a � o suficiente para requerer autoriza��o
		//Boolean authorizationRequired = rolesOnMethod || (rolesOnClass && ! permitAllOnMethod);
		
		boolean authorizationRequired = rolesOnMethod || permitAllOnMethod || rolesOnClass || permitAllOnClass;
		
		if (authorizationRequired) {
			
			// Recupera o cabe�alho HTTP 'Authorization' da requisi��o
	        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			
	        // Verifica se o valor do cabe�alho 'Authorization' est� no formato 'Bearer <token>' 
	        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
	        	requestContext.abortWith(
	                    Response.status(Response.Status.UNAUTHORIZED).entity("A requisi��o n�o possui o cabe�alho 'Authorization: Bearer <token>.'").build());
	        } else {
			
				try {
					
					// Recupera os dados do usu�rio a partir do token
					final Usuario user = decodeToken(authorizationHeader);
					
					boolean isAllRolesAllowed = permitAllOnMethod || (permitAllOnClass && !rolesOnMethod);
					
					// Caso todas os pap�is seja permitidos para acessar o recurso, define os dados do
					// usu�rio o SecurityContext
					if (isAllRolesAllowed) {
						
						requestContext.setSecurityContext(new PJeDashSecurityContext(user));
						
					} else {
					
						// Captura os pap�is declarados na anota��o RolesAllowed
						String[] allowedRoles = rolesOnMethod ? resourceMethod.getAnnotation(RolesAllowed.class).value()
								: resourceClass.getAnnotation(RolesAllowed.class).value();
						
						// Checa se o usu�rio possui um dos pap�is definidos anota��o RolesAllowed 
						if (!roleMatch(user.getPapeis(), Arrays.asList(allowedRoles))) {
							
							requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Privil�gios insuficientes para acessar este recurso").build());
						} else {
							
							requestContext.setSecurityContext(new PJeDashSecurityContext(user));
						}
					}

				} catch (ParseException e) {
					requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
							.entity("O Token fornecido � inv�lido").build());
				} catch (JOSEException e) {
					requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
							.entity("O Token fornecido � inv�lido").build());
				} catch (Exception e) {
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build());
				}
	        }
		}
	        	
    }
    
    private Boolean roleMatch(List<String> userRoles, List<String> rolesAllowed) {
		for (String role : userRoles) {
			if (rolesAllowed.contains(role)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
    
    private Usuario decodeToken(String token) throws ParseException, JOSEException, Exception {

    	// Decodifica o token para extrair os dados
    	JWTClaimsSet claimSet = (JWTClaimsSet) AuthUtils.decodeToken(token);
    	

		// Checa se o token expirou
		if (new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now())) {
			throw new Exception("O Token fornecido expirou.");
		}
		
		return AuthUtils.extractUserFromToken(claimSet);
    }

    public class PJeDashSecurityContext implements SecurityContext {

		private Usuario usuario;
		private Principal principal;

		public PJeDashSecurityContext(final Usuario usuario) {
			this.usuario = usuario;
			this.principal = new Principal() {

				@Override
				public String getName() {

					return usuario.getLogin();

				}

			};
		}

		@Override
		public Principal getUserPrincipal() {
			return this.principal;
		}

		@Override
		public boolean isUserInRole(String requiredRole) {
			boolean userInRole = requiredRole == null;
			
			if (!userInRole) {
				for (String role : usuario.getPapeis()) {
					if (requiredRole.equals(role)) {
						userInRole = true;
						break;
					}
				}
			}
			
			return userInRole;
		}

		@Override
		public boolean isSecure() {
			return true; //uriInfo.getAbsolutePath().toString().startsWith("https");
		}

		@Override
		public String getAuthenticationScheme() {
			return SecurityContext.BASIC_AUTH;
		}

	}
}