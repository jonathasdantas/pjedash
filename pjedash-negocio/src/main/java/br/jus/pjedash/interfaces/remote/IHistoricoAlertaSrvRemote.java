package br.jus.pjedash.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.HistoricoAlerta;

@Remote
public interface IHistoricoAlertaSrvRemote extends IBaseSrvRemote<HistoricoAlerta, Integer> {

	public List<HistoricoAlerta> recuperarHistoricoPorIdUsuario(Integer idUsuario);
	
	public Long recuperarNumeroAlertasNaoVisualizados(Integer idUsuario);
	
	public void marcarAlertasVisualizadoPorUsuario(Integer idUsuario);
	
}
