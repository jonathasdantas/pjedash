package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.Alerta;
import br.jus.pjedash.interfaces.remote.IAlertaSrvRemote;
import br.jus.pjedash.repositorio.AlertaDAO;

@Stateless
public class AlertaSrv implements IAlertaSrvRemote {

	@Inject
	private AlertaDAO alertaDAO;
	
	public Alerta recuperarPorId(Integer id) {
		return alertaDAO.recuperarEntidadePorId(id);
	}
	
	public Alerta inserir(Alerta alerta) {
		return alertaDAO.inserir(alerta);
	}

	public Alerta alterar(Alerta alerta) {
		return alertaDAO.alterar(alerta);
	}

	public void excluirPorId(Integer id) {
		alertaDAO.excluirPorId(id);
	}

	public List<Alerta> recuperarEntidades() {
		return alertaDAO.recuperarEntidades();
	}

	public List<Alerta> recuperarAlertasPorIdUsuario(Integer idUsuario) {
		List<Alerta> alertas = alertaDAO.recuperarAlertasPorIdUsuario(idUsuario); 
		return alertas;
	}
	
	
}
