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
import br.jus.pjedash.entidade.Alerta;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.IAlertaSrvRemote;

@Path("alertas")
@TratamentoExcecao
@PermitAll
public class AlertaSrvRest {

	private IAlertaSrvRemote alertaService = EJBServiceLocator.locateEJBStateless(IAlertaSrvRemote.class);
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Integer id) {
		Alerta alerta = alertaService.recuperarPorId(id);
		if (alerta != null) {
			alertaService.excluirPorId(id);
			return Response.ok(alerta).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(alertaService.recuperarEntidades()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(alertaService.recuperarPorId(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(Alerta parametro) {
		Alerta alerta = alertaService.inserir(parametro);
		return Response.ok(alerta).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(Alerta parametro) {
		return Response.ok(alertaService.alterar(parametro)).build();
	}

	@GET
	@Path("/usuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarAlertasPorIdUsuario(@PathParam("idUsuario") Integer idUsuario) {
		return Response.ok(alertaService.recuperarAlertasPorIdUsuario(idUsuario)).build();
	}

}
