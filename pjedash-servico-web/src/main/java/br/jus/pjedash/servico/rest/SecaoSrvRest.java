package br.jus.pjedash.servico.rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.jus.pjedash.comum.EJBServiceLocator;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.ISecaoSrvRemote;

@Path("secoes")
@TratamentoExcecao
@PermitAll
public class SecaoSrvRest {

	private ISecaoSrvRemote secaoService = EJBServiceLocator.locateEJBStateless(ISecaoSrvRemote.class);


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(secaoService.recuperarEntidades()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(secaoService.recuperarPorId(id)).build();
	}

}
