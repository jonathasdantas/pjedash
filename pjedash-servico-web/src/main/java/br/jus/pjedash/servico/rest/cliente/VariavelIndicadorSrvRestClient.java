package br.jus.pjedash.servico.rest.cliente;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import br.jus.pjedash.servico.util.Constants;
import br.jus.pjedash.servico.util.RestClientUtil;

public class VariavelIndicadorSrvRestClient {


	public Response recuperarVariaveis(String tipoIndicador, Integer idOrgaoJulgador, Integer ano, Integer mes ) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("tipoIndicador", tipoIndicador);
		parametros.put("idOrgaoJulgador", idOrgaoJulgador);
		parametros.put("ano", ano);
		parametros.put("mes", mes);

		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_VARIAVEIS_TIPO_INDICADOR, parametros);
	}

	public Response recuperarVariavelPorSigla(String sigla, Integer idOrgaoJulgador, Integer ano, Integer mes) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("sigla", sigla);
		parametros.put("idOrgaoJulgador", idOrgaoJulgador);
		parametros.put("ano", ano);
		parametros.put("mes", mes);

		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_VARIAVEIS_SIGLA, parametros);
	}

}
