package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.Configuracao;


public class ConfiguracaoDAO extends BaseDAO<Configuracao> {

	public ConfiguracaoDAO() {
		super(Configuracao.class);
	}

	@SuppressWarnings("unchecked")
	public List<Configuracao> recuperarConfiguracoesPorSecao(Integer idSecao) {
		String sql = "SELECT c FROM Configuracao c JOIN c.secao s WHERE s.id = :idSecao";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idSecao", idSecao);

		return query.getResultList();
	}

}