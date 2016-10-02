package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.Secao;
import br.jus.pjedash.interfaces.remote.ISecaoSrvRemote;
import br.jus.pjedash.repositorio.SecaoDAO;

@Stateless
public class SecaoSrv implements ISecaoSrvRemote {

	@Inject
	private SecaoDAO secaoDAO;
	
	public Secao recuperarPorId(Integer id) {
		return secaoDAO.recuperarEntidadePorId(id);
	}
	
	public Secao inserir(Secao secao) {
		return secaoDAO.inserir(secao);
	}

	public Secao alterar(Secao secao) {
		return secaoDAO.alterar(secao);
	}

	public void excluirPorId(Integer id) {
		secaoDAO.excluirPorId(id);
	}

	public List<Secao> recuperarEntidades() {
		return secaoDAO.recuperarSecoesOrdenadas();
	}

}
