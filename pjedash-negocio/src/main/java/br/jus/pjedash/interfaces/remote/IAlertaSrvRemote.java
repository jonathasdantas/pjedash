package br.jus.pjedash.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.Alerta;

@Remote
public interface IAlertaSrvRemote extends IBaseSrvRemote<Alerta, Integer> {
	public List<Alerta> recuperarAlertasPorIdUsuario(Integer idUsuario);
}