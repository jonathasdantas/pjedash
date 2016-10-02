package br.jus.pjedash.servico.rest;

import java.text.ParseException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;

import br.jus.pjedash.auth.AuthUtils;
import br.jus.pjedash.entidade.Usuario;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.servico.rest.cliente.UsuarioSrvRestClient;
import br.jus.pjedash.servico.util.JSONUtil;
import br.jus.pjedash.servico.util.ResponseMessageJson;

@Path("autenticacao")
@TratamentoExcecao
public class AutenticacaoSrvRest {
	
	@Inject
	private UsuarioSrvRestClient usuarioSrvRestClient;

	public static final ObjectMapper MAPPER = new ObjectMapper();
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(Usuario usuario, @Context final HttpServletRequest request)
			throws JOSEException {
		
		Response response = usuarioSrvRestClient.validarUsuario(usuario);
		
		if (response.getStatus() == Status.OK.getStatusCode()) {
			
			String json = response.getEntity().toString();
			Usuario usuarioResponse = JSONUtil.fromJsonToObj(json, Usuario.class);
			String token = AuthUtils.createToken(request.getRemoteHost(), usuarioResponse);
			
			response = Response.ok().entity(ResponseMessageJson.getJsonString("token", token)).build();
			
		}
		
		return response;
	}
	
	@GET
	@Path("/usuarioLogado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioLogado(@Context final HttpServletRequest request) throws ParseException, JOSEException {
		String header = request.getHeader(AuthUtils.AUTH_HEADER_KEY);
		String subject = AuthUtils.getSubject(header);

		return usuarioSrvRestClient.recuperarUsuarioPorId(Integer.parseInt(subject));
	}

}