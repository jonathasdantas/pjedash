package br.jus.pjedash.servico.rest;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.servico.rest.cliente.IndiceAtendimentoDemandaSrvRestClient;

@Path("indicadores")
@TratamentoExcecao
@PermitAll
public class IndiceAtendimentoDemandaSrvRest {
	
	@Inject
	private IndiceAtendimentoDemandaSrvRestClient indiceAtendimentoDemandaSrvRestClient;

	@GET
	@Path("/indiceAtendimentoDemanda/{idOrgaoJulgador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		return indiceAtendimentoDemandaSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador);
	}
	
	@GET
	@Path("/indiceAtendimentoDemanda/{idOrgaoJulgador}/{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("ano") Integer ano) {
		return indiceAtendimentoDemandaSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano);
	}	
	
	@GET
	@Path("/indiceAtendimentoDemanda/{idOrgaoJulgador}/{ano}/{mes}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("ano") Integer ano, @PathParam("mes") Integer mes ) {
		return indiceAtendimentoDemandaSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano, mes);
	}	
	
}
