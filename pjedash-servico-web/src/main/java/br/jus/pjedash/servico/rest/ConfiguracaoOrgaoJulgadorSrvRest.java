package br.jus.pjedash.servico.rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.jus.pjedash.comum.EJBServiceLocator;
import br.jus.pjedash.entidade.Configuracao;
import br.jus.pjedash.entidade.ConfiguracaoOrgaoJulgador;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.IConfiguracaoOrgaoJulgadorSrvRemote;
import br.jus.pjedash.interfaces.remote.IConfiguracaoSrvRemote;

@Path("configuracoesOrgao")
@TratamentoExcecao
@PermitAll
public class ConfiguracaoOrgaoJulgadorSrvRest {

	private IConfiguracaoOrgaoJulgadorSrvRemote configuracaoOrgaoService = EJBServiceLocator.locateEJBStateless(IConfiguracaoOrgaoJulgadorSrvRemote.class);
	private IConfiguracaoSrvRemote configuracaoService = EJBServiceLocator.locateEJBStateless(IConfiguracaoSrvRemote.class);


	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(configuracaoOrgaoService.recuperarPorId(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(ConfiguracaoOrgaoJulgador parametro) {
		Configuracao configuracao = configuracaoService.recuperarPorId(parametro.getConfiguracao().getId());
		parametro.setConfiguracao(configuracao);
		ConfiguracaoOrgaoJulgador configuracaoOrgaoJulgador = configuracaoOrgaoService.inserir(parametro);
		return Response.ok(configuracaoOrgaoJulgador).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(ConfiguracaoOrgaoJulgador parametro, @PathParam("id") Integer idAnotacao) {
		return Response.ok(configuracaoOrgaoService.alterar(parametro)).build();
	}

	@GET
	@Path("/orgao/{idOrgaoJulgador}/configuracao/{idConfiguracao}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarConfiguracaoOrgaoJulgador(@PathParam("idConfiguracao") Integer idConfiguracao, @PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		return Response.ok(configuracaoOrgaoService.recuperarConfiguracaoOrgaoJulgador(idConfiguracao, idOrgaoJulgador)).build();
	}
	
	@GET
	@Path("/orgao/{idOrgaoJulgador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarConfiguracoesOrgaoJulgador(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		return Response.ok(configuracaoOrgaoService.recuperarConfiguracoesOrgaoJulgador(idOrgaoJulgador)).build();
	}

}
