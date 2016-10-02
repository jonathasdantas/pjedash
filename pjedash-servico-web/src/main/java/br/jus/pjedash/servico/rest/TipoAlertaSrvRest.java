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
import br.jus.pjedash.entidade.TipoAlerta;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.ITipoAlertaSrvRemote;

@Path("tiposAlerta")
@TratamentoExcecao
@PermitAll
public class TipoAlertaSrvRest {

	private ITipoAlertaSrvRemote tipoAlertaService = EJBServiceLocator.locateEJBStateless(ITipoAlertaSrvRemote.class);
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Integer id) {
		TipoAlerta tipoAlerta = tipoAlertaService.recuperarPorId(id);
		if (tipoAlerta != null) {
			tipoAlertaService.excluirPorId(id);
			return Response.ok(tipoAlerta).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(tipoAlertaService.recuperarEntidades()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(tipoAlertaService.recuperarPorId(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(TipoAlerta parametro) {
		TipoAlerta tipoAlerta = tipoAlertaService.inserir(parametro);
		return Response.ok(tipoAlerta).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(TipoAlerta parametro) {
		return Response.ok(tipoAlertaService.alterar(parametro)).build();
	}


}
