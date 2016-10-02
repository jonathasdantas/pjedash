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
import br.jus.pjedash.servico.rest.cliente.IndicadorCasosNovosBaixadosSentencasJNSrvRestClient;

@Path("indicadores")
@TratamentoExcecao
@PermitAll
public class IndicadorCasosNovosBaixadosSentencasJNSrvRest {
	
	@Inject
	private IndicadorCasosNovosBaixadosSentencasJNSrvRestClient indicadorCasosNovosBaixadosSentencasJNSrvRestClient;

	@GET
	@Path("/novosBaixadosSentencasJN/{idOrgaoJulgador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro (@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		return indicadorCasosNovosBaixadosSentencasJNSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador);
	}
	@GET
	@Path("/novosBaixadosSentencasJN/{idOrgaoJulgador}/{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro (@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador,@PathParam("ano") Integer ano) {
		return indicadorCasosNovosBaixadosSentencasJNSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano);
	}
		
	@GET
	@Path("/novosBaixadosSentencasJN/{idOrgaoJulgador}/{ano}/{semestre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro (@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre) {
		return indicadorCasosNovosBaixadosSentencasJNSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano, semestre);
	}
}
