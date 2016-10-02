package br.jus.pjedash.repositorio;

import java.util.List;

import javax.persistence.Query;

import br.jus.pjedash.comum.BaseDAO;
import br.jus.pjedash.entidade.Anotacao;


public class AnotacaoDAO extends BaseDAO<Anotacao> {

	public AnotacaoDAO() {
		super(Anotacao.class);
	}

	@SuppressWarnings("unchecked")
	public List<Anotacao> recuperarAnotacoesPorNpuProcesso(String npuProcesso) {
		String sql = "SELECT a FROM Anotacao a WHERE a.npuProcesso = :npuProcesso";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("npuProcesso", npuProcesso);
		return query.getResultList();
	}

    @SuppressWarnings("unchecked")
    public List<Anotacao> buscarAnotacoesOrgaoPorTexto(Integer idOrgaoJulgador, String texto) {
        String sql = "SELECT a FROM Anotacao a WHERE a.orgaoJulgadorId = :idOrgaoJulgador AND UPPER(a.texto) LIKE :texto";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("idOrgaoJulgador", idOrgaoJulgador);
        query.setParameter("texto", "%" + texto.toUpperCase() + "%");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Anotacao> recuperarUltimasAnotacoesAlteradas(Integer quantidadeAnotacoes, Integer idOrgaoJulgador) {
        String sql = "SELECT a FROM Anotacao a WHERE a.orgaoJulgadorId = :idOrgaoJulgador ORDER BY a.dataUltimaAlteracao DESC";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("idOrgaoJulgador", idOrgaoJulgador);
        query.setMaxResults(quantidadeAnotacoes);
        return query.getResultList();
    }
	
}