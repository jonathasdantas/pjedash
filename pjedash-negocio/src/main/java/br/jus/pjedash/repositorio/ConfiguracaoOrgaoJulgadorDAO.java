package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.ConfiguracaoOrgaoJulgador;


public class ConfiguracaoOrgaoJulgadorDAO extends BaseDAO<ConfiguracaoOrgaoJulgador> {

	public ConfiguracaoOrgaoJulgadorDAO() {
		super(ConfiguracaoOrgaoJulgador.class);
	}

	@SuppressWarnings("rawtypes")
	public ConfiguracaoOrgaoJulgador recuperarConfiguracaoOrgaoJulgador(Integer idConfiguracao, Integer idOrgaoJulgador) {
		ConfiguracaoOrgaoJulgador retorno = null;
		
		String sql = "SELECT co FROM ConfiguracaoOrgaoJulgador co JOIN co.configuracao c WHERE c.id = :idConfiguracao AND " +
					 " co.idOrgaoJulgador = :idOrgaoJulgador";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idConfiguracao", idConfiguracao);
		query.setParameter("idOrgaoJulgador", idOrgaoJulgador);

		List retornoList = query.getResultList();
		
		if (!retornoList.isEmpty()) {
			retorno = (ConfiguracaoOrgaoJulgador) retornoList.get(0);
		}
		
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<ConfiguracaoOrgaoJulgador> recuperarConfiguracoesOrgaoJulgador(Integer idOrgaoJulgador) {
		String sql = "SELECT co FROM ConfiguracaoOrgaoJulgador co WHERE co.idOrgaoJulgador = :idOrgaoJulgador";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idOrgaoJulgador", idOrgaoJulgador);

		return query.getResultList();
	}

}