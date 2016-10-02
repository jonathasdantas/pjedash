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
import br.jus.pjedash.servico.rest.cliente.VariavelIndicadorSrvRestClient;

@Path("indicadores")
@TratamentoExcecao
@PermitAll
public class VariavelIndicadorSrvRest {

	@Inject
	private VariavelIndicadorSrvRestClient variavelIndicadorSrvRestClient;

	@GET
	@Path("/variavel/{tipoIndicador}/{idOrgaoJulgador}/{ano}/{mes}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarVariaveis (@PathParam("tipoIndicador") String tipoIndicador, @PathParam("idOrgaoJulgador") Integer idOrgaoJulgador,
			@PathParam("ano") Integer ano, @PathParam("mes") Integer mes ) {
		
		return variavelIndicadorSrvRestClient.recuperarVariaveis(tipoIndicador, idOrgaoJulgador, ano, mes); 
	}

	@GET
	@Path("/variavel/sigla/{sigla}/{idOrgaoJulgador}/{ano}/{mes}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarVariavelPorSigla (@PathParam("sigla") String sigla, @PathParam("idOrgaoJulgador") Integer idOrgaoJulgador,
			@PathParam("ano") Integer ano, @PathParam("mes") Integer mes) {
		
		return variavelIndicadorSrvRestClient.recuperarVariavelPorSigla(sigla, idOrgaoJulgador, ano, mes);
	}

}