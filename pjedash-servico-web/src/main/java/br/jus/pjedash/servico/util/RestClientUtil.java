package br.jus.pjedash.servico.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClientUtil {


	public static Response doGet(String url, String path, String pathParamName, Object pathParamValue) {
		Map<String, Object> pathParams = null;
		
		if (pathParamName != null && pathParamName.trim().length() > 0) {
			
			pathParams = new HashMap<String, Object>();
			
			pathParams.put(pathParamName, pathParamValue);
		}

		return doGet(url, path, pathParams);
	}

	public static Response doGet(String url, String path, Map<String, Object> pathParams) {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(url).path(path);

		if (pathParams != null && !pathParams.isEmpty()) {
			target = target.resolveTemplates(pathParams);
		}

		return buildResponse(target.request(MediaType.APPLICATION_JSON).get());
	}

	public static Response doPost(String url, String path, Entity<?> entity) {
		
		return doPost(url, path, null, entity);
	}

	public static Response doPost(String url, String path,  Map<String, Object> pathParams, Entity<?> entity) {
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(url).path(path);
		
		if (pathParams != null && !pathParams.isEmpty()) {
			target = target.resolveTemplates(pathParams);
		}
		
		return buildResponse(target.request().post(entity));
	}

	public static Response buildResponseFromException(WebApplicationException exception) {
		int status = exception.getResponse().getStatus();
		String errorMessage = exception.getResponse().readEntity(String.class);
		
		return Response.status(status).entity(errorMessage).build();
	}

	private static Response buildResponse(Response response) {
		int status = response.getStatus();

		String dadosResposta = response.readEntity(String.class);

		return Response.status(status).entity(dadosResposta).build(); 
	}
	
}
