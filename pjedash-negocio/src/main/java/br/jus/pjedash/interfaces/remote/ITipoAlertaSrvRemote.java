package br.jus.pjedash.interfaces.remote;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.TipoAlerta;

@Remote
public interface ITipoAlertaSrvRemote extends IBaseSrvRemote<TipoAlerta, Integer> {


}
