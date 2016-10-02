package br.jus.pjedash.servico.rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.jus.pjedash.comum.EJBServiceLocator;
import br.jus.pjedash.entidade.Indicador;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.IIndicadorSrvRemote;

@Path("indicadores")
@TratamentoExcecao
@PermitAll
public class IndicadorSrvRest {

	private IIndicadorSrvRemote indicadorService = EJBServiceLocator.locateEJBStateless(IIndicadorSrvRemote.class);


	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Integer id) {
		Indicador indicador = indicadorService.recuperarPorId(id);
		if (indicador != null) {
			indicadorService.excluirPorId(id);
			return Response.ok(indicador).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(indicadorService.recuperarEntidades()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(indicadorService.recuperarPorId(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(Indicador parametro) {
		Indicador indicador = indicadorService.inserir(parametro);
		return Response.ok(indicador).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(Indicador parametro) {
		return Response.ok(indicadorService.alterar(parametro)).build();
	}

}
