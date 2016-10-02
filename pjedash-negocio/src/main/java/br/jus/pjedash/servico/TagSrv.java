package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.Tag;
import br.jus.pjedash.interfaces.remote.ITagSrvRemote;
import br.jus.pjedash.repositorio.TagDAO;

@Stateless
public class TagSrv implements ITagSrvRemote {

	@Inject
	private TagDAO tagDAO;


	public Tag recuperarPorId(Integer id) {
		return tagDAO.recuperarEntidadePorId(id);
	}
	
	public Tag inserir(Tag tag) {
		return tagDAO.inserir(tag);
	}

	public Tag alterar(Tag tag) {
		return tagDAO.alterar(tag);
	}

	public void excluirPorId(Integer id) {
		tagDAO.excluirPorId(id);
	}

	public List<Tag> recuperarEntidades() {
		return tagDAO.recuperarEntidades();
	}

	public List<Tag> recuperarTagsPorIdUsuario(Integer idUsuario) {
		return tagDAO.recuperarTagsPorIdUsuario(idUsuario);
	}

	public List<Tag> recuperarTagsPorProcessoUsuario(String npuProcesso, Integer idUsuario) {
		return tagDAO.recuperarTagsPorProcessoUsuario(npuProcesso, idUsuario);
	}
	
}
