package br.jus.pjedash.servico.rest.cliente;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import br.jus.pjedash.servico.util.Constants;
import br.jus.pjedash.servico.util.RestClientUtil;

public class MovimentoSrvRestClient {

	public Response getMovimentosProcesso(String npu, Integer qtdMovimento) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("npu", npu);
		parametros.put("qtdMovimento", qtdMovimento);

		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_MOVIMENTOS, parametros);
	}

}
