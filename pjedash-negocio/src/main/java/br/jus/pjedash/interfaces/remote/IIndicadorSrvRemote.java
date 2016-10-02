package br.jus.pjedash.interfaces.remote;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.Indicador;

@Remote
public interface IIndicadorSrvRemote extends IBaseSrvRemote<Indicador, Integer> {
	
}