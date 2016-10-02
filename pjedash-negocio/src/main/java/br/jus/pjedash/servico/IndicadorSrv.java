package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.Indicador;
import br.jus.pjedash.interfaces.remote.IIndicadorSrvRemote;
import br.jus.pjedash.repositorio.IndicadorDAO;

@Stateless
public class IndicadorSrv implements IIndicadorSrvRemote {

	@Inject
	private IndicadorDAO indicadorDAO;
	
	public Indicador recuperarPorId(Integer id) {
		return indicadorDAO.recuperarEntidadePorId(id);
	}
	
	public Indicador inserir(Indicador indicador) {
		return indicadorDAO.inserir(indicador);
	}

	public Indicador alterar(Indicador indicador) {
		return indicadorDAO.alterar(indicador);
	}

	public void excluirPorId(Integer id) {
		indicadorDAO.excluirPorId(id);
	}

	public List<Indicador> recuperarEntidades() {
		return indicadorDAO.recuperarEntidades();
	}

}
