package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.TipoAlerta;
import br.jus.pjedash.interfaces.remote.ITipoAlertaSrvRemote;
import br.jus.pjedash.repositorio.TipoAlertaDAO;

@Stateless
public class TipoAlertaSrv implements ITipoAlertaSrvRemote {

	@Inject
	private TipoAlertaDAO tipoAlertaDAO;


	public List<TipoAlerta> recuperarEntidades() {
		return tipoAlertaDAO.recuperarEntidades();
	}

	public TipoAlerta recuperarPorId(Integer id) {
		return tipoAlertaDAO.recuperarEntidadePorId(id);
	}

	public TipoAlerta inserir(TipoAlerta tipoAlerta) {
		return tipoAlertaDAO.inserir(tipoAlerta);
	}

	public TipoAlerta alterar(TipoAlerta tipoAlerta) {
		return tipoAlertaDAO.alterar(tipoAlerta);
	}

	public void excluirPorId(Integer id) {
		tipoAlertaDAO.excluirPorId(id);
	}

}