package br.jus.pjedash.auth.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * Implementação básica de um filtro CORS. Permite acesso ao serviço de qualquer domínio.
 */
@PreMatching
@Provider
public class CorsFilter implements ContainerResponseFilter {
	
	private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
	private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
	
	private static final String DEFAULT_ALLOWED_METHODS  = "GET,POST,PUT,DELETE,OPTIONS,HEAD";
	private static final String DEFAULT_ALLOWED_HEADERS  = "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin";
	private static final String DEFAULT_ALLOWED_ORIGIN   = "*";
	private static final String DEFAULT_MAX_AGE  = "3600";
	

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		responseContext.getHeaders().putSingle(ACCESS_CONTROL_ALLOW_ORIGIN, DEFAULT_ALLOWED_ORIGIN);
		responseContext.getHeaders().putSingle(ACCESS_CONTROL_ALLOW_METHODS, DEFAULT_ALLOWED_METHODS);
		responseContext.getHeaders().putSingle(ACCESS_CONTROL_MAX_AGE, DEFAULT_MAX_AGE);
		responseContext.getHeaders().putSingle(ACCESS_CONTROL_ALLOW_HEADERS, DEFAULT_ALLOWED_HEADERS);
	}

}
