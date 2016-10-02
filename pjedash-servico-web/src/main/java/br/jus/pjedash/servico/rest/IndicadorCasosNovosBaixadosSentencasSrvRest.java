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
import br.jus.pjedash.servico.rest.cliente.IndicadorCasosNovosBaixadosSentencasSrvRestClient;


@Path("indicadores")
@TratamentoExcecao
@PermitAll
public class IndicadorCasosNovosBaixadosSentencasSrvRest {

	@Inject
	private IndicadorCasosNovosBaixadosSentencasSrvRestClient indicadorCasosNovosBaixadosSentencasSrvRestClient;
	
	@GET
	@Path("/novosBaixadosSentencas/{idOrgaoJulgador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro (@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		return indicadorCasosNovosBaixadosSentencasSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador);
	}	
	
	@GET
	@Path("/novosBaixadosSentencas/{idOrgaoJulgador}/{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro (@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("ano") Integer ano) {
		return indicadorCasosNovosBaixadosSentencasSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano);
	}	
	
	@GET
	@Path("/novosBaixadosSentencas/{idOrgaoJulgador}/{ano}/{mes}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarIndicadoresPorFiltro (@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador, @PathParam("ano") Integer ano, @PathParam("mes") Integer mes) {
		return indicadorCasosNovosBaixadosSentencasSrvRestClient.recuperarIndicadoresPorFiltro(idOrgaoJulgador, ano, mes);
	}
	
}
