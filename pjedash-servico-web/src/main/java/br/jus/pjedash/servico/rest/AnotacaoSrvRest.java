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
import br.jus.pjedash.entidade.Anotacao;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.IAnotacaoSrvRemote;

@Path("anotacoes")
@TratamentoExcecao
@PermitAll
public class AnotacaoSrvRest {

	private static final int ULTIMAS_ANOTACOES_ALTERADAS_LIMIT = 3;

	private IAnotacaoSrvRemote anotacaoService = EJBServiceLocator.locateEJBStateless(IAnotacaoSrvRemote.class);
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Integer id) {
		Anotacao anotacao = anotacaoService.recuperarPorId(id);
		if (anotacao != null) {
			anotacaoService.excluirPorId(id);
			return Response.ok(anotacao).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(anotacaoService.recuperarEntidades()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(anotacaoService.recuperarPorId(id)).build();
	}
	
	@GET
	@Path("/processo/{npuProcesso}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarAnotacoesPorNpuProcesso(@PathParam("npuProcesso") String npuProcesso) {
		return Response.ok(anotacaoService.recuperarAnotacoesPorNpuProcesso(npuProcesso)).build();
	}

	@GET
	@Path("/orgaoJulgador/{idOrgaoJulgador}/{texto}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarAnotacoesOrgaoPorTexto(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("texto") String texto) {
		return Response.ok(anotacaoService.buscarAnotacoesOrgaoPorTexto(idOrgaoJulgador, texto)).build();
	}

	@GET
	@Path("/orgaoJulgador/{idOrgaoJulgador}/ultimas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarUltimasAnotacoesAlteradas(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		return Response.ok(anotacaoService.recuperarUltimasAnotacoesAlteradas(ULTIMAS_ANOTACOES_ALTERADAS_LIMIT, idOrgaoJulgador)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(Anotacao parametro) {
		Anotacao anotacao = anotacaoService.inserir(parametro);
		return Response.ok(anotacao).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(Anotacao parametro, @PathParam("id") Integer idAnotacao) {
		return Response.ok(anotacaoService.alterar(parametro)).build();
	}


}
