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
import br.jus.pjedash.servico.rest.cliente.MovimentoSrvRestClient;


@Path("movimentosProcesso")
@TratamentoExcecao
@PermitAll
public class MovimentoSrvRest {

	@Inject
	private MovimentoSrvRestClient movimentoSrvRestClient;

	@GET
	@Path("/{npu}/{qtdMovimento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovimentosProcesso(@PathParam("npu") String npu, @PathParam("qtdMovimento") Integer qtdMovimento ) {
		return movimentoSrvRestClient.getMovimentosProcesso(npu, qtdMovimento);
	}

}
