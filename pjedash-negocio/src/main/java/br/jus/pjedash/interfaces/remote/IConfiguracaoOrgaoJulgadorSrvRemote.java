package br.jus.pjedash.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.ConfiguracaoOrgaoJulgador;

@Remote
public interface IConfiguracaoOrgaoJulgadorSrvRemote extends IBaseSrvRemote<ConfiguracaoOrgaoJulgador, Integer> {

	public ConfiguracaoOrgaoJulgador recuperarConfiguracaoOrgaoJulgador(Integer idConfiguracao, Integer idOrgaoJulgador);

	public List<ConfiguracaoOrgaoJulgador> recuperarConfiguracoesOrgaoJulgador(Integer idOrgaoJulgador);

}