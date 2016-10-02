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
import br.jus.pjedash.entidade.ProcessoTag;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.IProcessoTagSrvRemote;

@Path("processosTag")
@TratamentoExcecao
@PermitAll
public class ProcessoTagSrvRest {

	private IProcessoTagSrvRemote processoTagService = EJBServiceLocator.locateEJBStateless(IProcessoTagSrvRemote.class);
	

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Integer id) {
		ProcessoTag processoTag = processoTagService.recuperarPorId(id);
		if (processoTag != null) {
			processoTagService.excluirPorId(id);
			return Response.ok(processoTag).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(processoTagService.recuperarEntidades()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(processoTagService.recuperarPorId(id)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(ProcessoTag parametro) {
		ProcessoTag processoTag = processoTagService.inserir(parametro);
		return Response.ok(processoTag).build();
	}

	@PUT
	@Path("/alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(ProcessoTag parametro) {
		return Response.ok(processoTagService.alterar(parametro)).build();
	}

}