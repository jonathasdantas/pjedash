package br.jus.pjedash.servico.rest.cliente;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.jus.pjedash.servico.util.Constants;
import br.jus.pjedash.servico.util.RestClientUtil;

public class ProcessoSrvRestClient {
	
	public Response getProcessosConclusos(Integer orgao, Integer qtdDias) throws WebApplicationException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("orgaoJulgador", orgao);
		parametros.put("qtdDias", qtdDias);

		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_PROCESSOS_CONCLUSOS, parametros);
	}

	public Response getProcessosPendentes (@PathParam("orgaoJulgador") Integer orgao, @PathParam("qtdDias") Integer qtdDias){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("orgaoJulgador", orgao);
		parametros.put("qtdDias", qtdDias);
		
		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_PROCESSOS_PENDENTES, parametros);
	}	

	public Response getPartesProcesso (@PathParam("npu") String npu){
		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_PROCESSOS_PARTES, "npu", npu);
	}	

	public Response getDadosProcesso (@PathParam("npu") String npu){
		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_PROCESSOS_DADOS, "npu", npu);
	}	

}
