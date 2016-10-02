package br.jus.pjedash.servico.rest;

import java.util.HashMap;
import java.util.Map;

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
import br.jus.pjedash.entidade.HistoricoAlerta;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.IHistoricoAlertaSrvRemote;

@Path("historicosAlerta")
@TratamentoExcecao
@PermitAll
public class HistoricoAlertaSrvRest {

	private IHistoricoAlertaSrvRemote historicoAlertaService = EJBServiceLocator.locateEJBStateless(IHistoricoAlertaSrvRemote.class);
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Integer id) {
		HistoricoAlerta historicoAlerta = historicoAlertaService.recuperarPorId(id);
		if (historicoAlerta != null) {
			historicoAlertaService.excluirPorId(id);
			return Response.ok(historicoAlerta).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(historicoAlertaService.recuperarEntidades()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(historicoAlertaService.recuperarPorId(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(HistoricoAlerta parametro) {
		HistoricoAlerta historicoAlerta = historicoAlertaService.inserir(parametro);
		return Response.ok(historicoAlerta).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(HistoricoAlerta parametro) {
		return Response.ok(historicoAlertaService.alterar(parametro)).build();
	}

	@GET
	@Path("/usuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarHistoricoPorIdUsuario(@PathParam("idUsuario") Integer idUsuario) {
		return Response.ok(historicoAlertaService.recuperarHistoricoPorIdUsuario(idUsuario)).build();
	}
	
	@GET
	@Path("/usuario/{idUsuario}/naoVisualizados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarNumeroAlertasNaoVisualizados(@PathParam("idUsuario") Integer idUsuario) {
		Long numeroAlertasNaoVisualizados = historicoAlertaService.recuperarNumeroAlertasNaoVisualizados(idUsuario);
		Map<String, Long> resultado = new HashMap<String, Long>();
		resultado.put("alertasNaoVisualizados", numeroAlertasNaoVisualizados);
		
		return Response.ok(resultado).build();
	}

	@GET
	@Path("/usuario/{idUsuario}/marcarNaoVisualizados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response marcarAlertasVisualizadoPorUsuario(@PathParam("idUsuario") Integer idUsuario) {
		historicoAlertaService.marcarAlertasVisualizadoPorUsuario(idUsuario);
		
		return Response.ok().build();
	}
	
	
}
