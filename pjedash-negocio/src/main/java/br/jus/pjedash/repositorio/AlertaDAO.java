package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.Alerta;


public class AlertaDAO extends BaseDAO<Alerta> {

	public AlertaDAO() {
		super(Alerta.class);
	}

	@SuppressWarnings("unchecked")
	public List<Alerta> recuperarAlertasPorIdUsuario(Integer idUsuario) {
		String q = "SELECT a FROM Alerta a WHERE a.usuarioId = :idUsuario";
		Query query = getEntityManager().createQuery(q);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}
	
}