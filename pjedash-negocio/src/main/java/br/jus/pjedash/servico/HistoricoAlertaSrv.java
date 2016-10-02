package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.HistoricoAlerta;
import br.jus.pjedash.interfaces.remote.IHistoricoAlertaSrvRemote;
import br.jus.pjedash.repositorio.HistoricoAlertaDAO;

@Stateless
public class HistoricoAlertaSrv implements IHistoricoAlertaSrvRemote {

	@Inject
	private HistoricoAlertaDAO historicoAlertaDAO;


	public List<HistoricoAlerta> recuperarEntidades() {
		return historicoAlertaDAO.recuperarEntidades();
	}

	public HistoricoAlerta recuperarPorId(Integer id) {
		return historicoAlertaDAO.recuperarEntidadePorId(id);
	}
	
	public HistoricoAlerta inserir(HistoricoAlerta historicoAlerta) {
		return historicoAlertaDAO.inserir(historicoAlerta);
	}

	public HistoricoAlerta alterar(HistoricoAlerta historicoAlerta) {
		return historicoAlertaDAO.alterar(historicoAlerta);
	}

	public void excluirPorId(Integer id) {
		historicoAlertaDAO.excluirPorId(id);
	}

	public List<HistoricoAlerta> recuperarHistoricoPorIdUsuario(Integer idUsuario) {
		return historicoAlertaDAO.recuperarHistoricoPorIdUsuario(idUsuario);
	}

	public Long recuperarNumeroAlertasNaoVisualizados(Integer idUsuario) {
		return historicoAlertaDAO.recuperarNumeroAlertasNaoVisualizados(idUsuario);
	}

	public void marcarAlertasVisualizadoPorUsuario(Integer idUsuario) {
		historicoAlertaDAO.marcarAlertasVisualizadoPorUsuario(idUsuario);
	}
	
}
