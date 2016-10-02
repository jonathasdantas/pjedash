package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.Tag;


public class TagDAO extends BaseDAO<Tag> {

	public TagDAO() {
		super(Tag.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> recuperarTagsPorIdUsuario(Integer idUsuario) {
		String q = "SELECT DISTINCT t FROM Tag t JOIN t.processosTag pt WHERE pt.usuarioId = :idUsuario";
		Query query = getEntityManager().createQuery(q);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> recuperarTagsPorProcessoUsuario(String npuProcesso, Integer idUsuario) {
		String q = "SELECT DISTINCT t FROM Tag t JOIN t.processosTag pt WHERE pt.npuProcesso = :npuProcesso AND pt.usuarioId = :idUsuario";
		Query query = getEntityManager().createQuery(q);
		query.setParameter("npuProcesso", npuProcesso);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}
	
}