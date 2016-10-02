package br.jus.pjedash.interfaces.remote;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.Secao;

@Remote
public interface ISecaoSrvRemote extends IBaseSrvRemote<Secao, Integer> {
	
}