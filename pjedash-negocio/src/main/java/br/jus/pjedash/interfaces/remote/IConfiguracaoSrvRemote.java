package br.jus.pjedash.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.Configuracao;

@Remote
public interface IConfiguracaoSrvRemote extends IBaseSrvRemote<Configuracao, Integer> {

	public List<Configuracao> recuperarConfiguracoesPorSecao(Integer idSecao);

}