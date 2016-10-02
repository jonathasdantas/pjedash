package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.ProcessoTag;


public class ProcessoTagDAO extends BaseDAO<ProcessoTag> {

	public ProcessoTagDAO() {
		super(ProcessoTag.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> recuperarProcessosTagPorTagUsuario(Integer idTag, Integer idUsuario) {
		String sql = "SELECT id_processo_tag FROM processo_tag WHERE id_tag = :idTag AND id_usuario = :idUsuario";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("idTag", idTag);
		query.setParameter("idUsuario", idUsuario);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> recuperarProcessosPorTagOrgaoJulgador(Integer idTag, Integer idOrgaoJulgador) {
		String sql = "SELECT pt.npu_processo FROM processo_tag pt INNER JOIN tag t ON t.id_tag = pt.id_tag " +
					 " WHERE t.id_tag = :idTag AND t.id_orgaoJulgador = :idOrgaoJulgador";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("idTag", idTag);
		query.setParameter("idOrgaoJulgador", idOrgaoJulgador);

		return query.getResultList();
	}

	public Integer recuperarProcessosTagPorTagProcessoUsuario(Integer idTag, Integer idUsuario,
			String npuProcesso) {
		String sql = "SELECT id_processo_tag FROM processo_tag WHERE id_tag = :idTag AND id_usuario = :idUsuario AND npu_processo = :npuProcesso";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("idTag", idTag);
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("npuProcesso", npuProcesso);
		
		return (Integer) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
    public List<Object[]> recuperarAssociacoesTagsProcessosPorOrgao(Integer idOrgaoJulgador) {
	    String sql = "SELECT pt.id_tag, COUNT(pt.id_processo_tag) as hits FROM processo_tag pt INNER JOIN tag t ON t.id_tag = pt.id_tag  " +
	                 " WHERE t.id_orgaojulgador = :idOrgaoJulgador GROUP BY pt.id_tag";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter("idOrgaoJulgador", idOrgaoJulgador);
        
        return query.getResultList();
	}
	
}