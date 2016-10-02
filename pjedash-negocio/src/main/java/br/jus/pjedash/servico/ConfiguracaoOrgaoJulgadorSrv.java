package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.ConfiguracaoOrgaoJulgador;
import br.jus.pjedash.interfaces.remote.IConfiguracaoOrgaoJulgadorSrvRemote;
import br.jus.pjedash.repositorio.ConfiguracaoOrgaoJulgadorDAO;

@Stateless
public class ConfiguracaoOrgaoJulgadorSrv implements IConfiguracaoOrgaoJulgadorSrvRemote {

	@Inject
	private ConfiguracaoOrgaoJulgadorDAO configuracaoOrgaoJulgadorDAO;
	
	public ConfiguracaoOrgaoJulgador recuperarPorId(Integer id) {
		return configuracaoOrgaoJulgadorDAO.recuperarEntidadePorId(id);
	}
	
	public ConfiguracaoOrgaoJulgador inserir(ConfiguracaoOrgaoJulgador configuracaoOrgaoJulgador) {
		return configuracaoOrgaoJulgadorDAO.inserir(configuracaoOrgaoJulgador);
	}

	public ConfiguracaoOrgaoJulgador alterar(ConfiguracaoOrgaoJulgador configuracaoOrgaoJulgador) {
		return configuracaoOrgaoJulgadorDAO.alterar(configuracaoOrgaoJulgador);
	}

	public void excluirPorId(Integer id) {
		configuracaoOrgaoJulgadorDAO.excluirPorId(id);
	}

	public List<ConfiguracaoOrgaoJulgador> recuperarEntidades() {
		return configuracaoOrgaoJulgadorDAO.recuperarEntidades();
	}

	public ConfiguracaoOrgaoJulgador recuperarConfiguracaoOrgaoJulgador(Integer idConfiguracao,
			Integer idOrgaoJulgador) {
		return configuracaoOrgaoJulgadorDAO.recuperarConfiguracaoOrgaoJulgador(idConfiguracao, idOrgaoJulgador);
	}

	public List<ConfiguracaoOrgaoJulgador> recuperarConfiguracoesOrgaoJulgador(Integer idOrgaoJulgador) {
		return configuracaoOrgaoJulgadorDAO.recuperarConfiguracoesOrgaoJulgador(idOrgaoJulgador);
	}

}
