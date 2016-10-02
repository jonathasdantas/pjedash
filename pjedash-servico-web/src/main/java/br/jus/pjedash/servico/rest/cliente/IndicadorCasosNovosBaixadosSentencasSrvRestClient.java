package br.jus.pjedash.servico.rest.cliente;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import br.jus.pjedash.servico.util.Constants;
import br.jus.pjedash.servico.util.RestClientUtil;

public class IndicadorCasosNovosBaixadosSentencasSrvRestClient {

	public Response recuperarIndicadoresPorFiltro(Integer idOrgaoJulgador) {
		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_ICNB_ORGAO, "idOrgaoJulgador", idOrgaoJulgador);
	}

	public Response recuperarIndicadoresPorFiltro(Integer idOrgaoJulgador, Integer ano) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idOrgaoJulgador", idOrgaoJulgador);
		parametros.put("ano", ano);

		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_ICNB_ORGAO_ANO, parametros);
	}

	public Response recuperarIndicadoresPorFiltro(Integer idOrgaoJulgador, Integer ano, Integer mes) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idOrgaoJulgador", idOrgaoJulgador);
		parametros.put("ano", ano);
		parametros.put("mes", mes);

		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_ICNB_ORGAO_ANO_MES, parametros);
	}
}
