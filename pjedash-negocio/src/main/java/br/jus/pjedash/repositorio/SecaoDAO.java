package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.Secao;


public class SecaoDAO extends BaseDAO<Secao> {

	public SecaoDAO() {
		super(Secao.class);
	}

	@SuppressWarnings("unchecked")
	public List<Secao> recuperarSecoesOrdenadas() {
		String sql = "SELECT s FROM Secao s ORDER BY s.ordem";
		Query query = getEntityManager().createQuery(sql);

		return query.getResultList();
	}

}