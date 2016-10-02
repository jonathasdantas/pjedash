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
import br.jus.pjedash.servico.rest.cliente.IndiceAtendimentoDemandaJNSrvRestClient;


@Path("indicadores")
@TratamentoExcecao
@PermitAll
public class IndiceAtendimentoDemandaJNSrvRest {
	
	@Inject
	private IndiceAtendimentoDemandaJNSrvRestClient indiceAtendimentoDemandaJNSrvRestClient;
	
	
	@GET
	@Path("/indiceAtendimentoDemandaJN/{idOrgaoJulgador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		return indiceAtendimentoDemandaJNSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador);
	}
	
	@GET
	@Path("/indiceAtendimentoDemandaJN/{idOrgaoJulgador}/{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("ano") Integer ano) {
		return indiceAtendimentoDemandaJNSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano);
	}
	

	@GET
	@Path("/indiceAtendimentoDemandaJN/{idOrgaoJulgador}/{ano}/{semestre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre) {
		return indiceAtendimentoDemandaJNSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano, semestre);
	}


}
