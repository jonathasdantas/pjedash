package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.HistoricoAlerta;


public class HistoricoAlertaDAO extends BaseDAO<HistoricoAlerta> {

	public HistoricoAlertaDAO() {
		super(HistoricoAlerta.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<HistoricoAlerta> recuperarHistoricoPorIdUsuario(Integer idUsuario) {
		String q = "SELECT h FROM HistoricoAlerta h WHERE h.usuarioId = :idUsuario ORDER BY h.dataOcorrencia DESC";
		Query query = getEntityManager().createQuery(q);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}
	
	public Long recuperarNumeroAlertasNaoVisualizados(Integer idUsuario) {
		String q = "SELECT  COUNT(h.id) FROM HistoricoAlerta h WHERE h.visualizado = false AND h.usuarioId = :idUsuario";
		Query query = getEntityManager().createQuery(q);
		query.setParameter("idUsuario", idUsuario);
		return (Long) query.getSingleResult();
	}
	
	public void marcarAlertasVisualizadoPorUsuario(Integer idUsuario) {
		String q = "UPDATE HistoricoAlerta h SET h.visualizado = true  WHERE h.usuarioId = :idUsuario";
		Query query = getEntityManager().createQuery(q);
		query.setParameter("idUsuario", idUsuario);
		
		query.executeUpdate();
	}
	
}