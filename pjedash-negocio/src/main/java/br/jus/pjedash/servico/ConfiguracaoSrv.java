package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.Configuracao;
import br.jus.pjedash.interfaces.remote.IConfiguracaoSrvRemote;
import br.jus.pjedash.repositorio.ConfiguracaoDAO;

@Stateless
public class ConfiguracaoSrv implements IConfiguracaoSrvRemote {

	@Inject
	private ConfiguracaoDAO configuracaoDAO;
	
	public Configuracao recuperarPorId(Integer id) {
		return configuracaoDAO.recuperarEntidadePorId(id);
	}
	
	public Configuracao inserir(Configuracao configuracao) {
		return configuracaoDAO.inserir(configuracao);
	}

	public Configuracao alterar(Configuracao configuracao) {
		return configuracaoDAO.alterar(configuracao);
	}

	public void excluirPorId(Integer id) {
		configuracaoDAO.excluirPorId(id);
	}

	public List<Configuracao> recuperarEntidades() {
		return configuracaoDAO.recuperarEntidades();
	}

	public List<Configuracao> recuperarConfiguracoesPorSecao(Integer idSecao) {
		return configuracaoDAO.recuperarConfiguracoesPorSecao(idSecao);
	}

}
