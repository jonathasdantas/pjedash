package br.jus.pjedash.comum;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Esta classe contém os métodos básicos de persistência e acesso das entidades no bando de dados do sistema.
 * Os métodos são genéricos e permitem apenas a utilização das classes que representam as entidades de negócio. 
 * 
 * @author Equipe Arquitetura PJe 2.0
 */
@SuppressWarnings("rawtypes")
public class BaseDAO<E extends BaseEntity> {
	
	private Class<E> entityClass;

	@PersistenceContext(unitName = "pjedash")
	private EntityManager entityManager;
	
	public BaseDAO(Class<E> entityClass) {
		this.entityClass = entityClass;		
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	protected Class<E> getEntityClass() {
		return entityClass;
	}
	
	/**
	 * Consulta todos os registros da entidade informada, sem filtros.
	 * 
	 * @param	entityClass A classe da entidade a ser consultada.
	 * @return	Uma lista contendo todos os registros da entidade informada.
	 */
	public List<E> recuperarEntidades() {

		CriteriaQuery<E> criteria = this.getEntityManager().getCriteriaBuilder().createQuery(entityClass);
		
		return this.getEntityManager().createQuery(criteria.select(criteria.from(entityClass))).getResultList();
	}

	public E recuperarEntidade(E entidade) {
		return this.recuperarEntidadePorId(entidade.getId());
	}
	
	/**
	 * Consulta uma entidade por seu ID.
	 * 
	 * @param	id O identificador único (PK) da entidade.
	 * @param 	entityClass A classe da entidade a ser consultada.
	 * @return	A entidade identificada pelo ID informado.
	 */
	public E recuperarEntidadePorId(Object id) {
		return this.getEntityManager().find(entityClass, id);
	}
	
	protected Query createQuery(String jpql) {
		return this.entityManager.createQuery(jpql);
	}
	
	protected <T> TypedQuery<T> createQuery(CriteriaQuery<T> cq) {
		return this.entityManager.createQuery(cq);
	}
	
	protected Query createQuery(StringBuilder jpql) {
		return this.createQuery(jpql.toString());
	}
	
	protected TypedQuery<E> createTypedQuery(StringBuilder jpql) {
		return this.createTypedQuery(jpql.toString());
	}
	
	protected TypedQuery<E> createTypedQuery(String jpql) {
		return this.entityManager.createQuery(jpql, this.entityClass);
	}
	
	protected <T> TypedQuery<T> createTypedQuery(StringBuilder jpql, Class<T> entidadeClass) {
		return createTypedQuery(jpql.toString(), entidadeClass);
	}
	
	protected <T> TypedQuery<T> createTypedQuery(String jpql, Class<T> entidadeClass) {
		return this.entityManager.createQuery(jpql, entidadeClass);
	}
	
	protected CriteriaBuilder getCriteriaBuilder() {
		return this.entityManager.getCriteriaBuilder();
	}
	
	protected CriteriaQuery<E> getCriteriaQueryRecuperarEntidades() {
		
		CriteriaQuery<E> cq = createCriteriaQuery();
		
		Root<E> e = cq.from(this.entityClass);
		
		e.alias("e");
		
		return cq;
		
	}

	protected CriteriaQuery<E> createCriteriaQuery() {
		return getCriteriaBuilder().createQuery(this.entityClass);
	}
	

	/**
	 * Realiza a inserção da entidade informada no banco de dados.
	 * 
	 * @param	entidade A entidade a ser inserida no banco de dados.
	 */
	public E inserir(E entidade) {
		
		this.getEntityManager().persist(entidade);
		
		return entidade;
	}

	/**
	 * Realiza a alteração da entidade informada no banco de dados.
	 * 
	 * @param	entidade A entidade a ser alterada no banco de dados.
	 * @return 
	 * @return	A própria entidade alterada.
	 */
	public E alterar(E entidade) {
		return this.getEntityManager().merge(entidade);
	}

	/**
	 * Realiza a deleção da entidade informada no banco de dados.
	 * 
	 * @param entidade A entidade a ser deletada no banco de dados.
	 */
	public void excluir(E entidade) {

		if (this.getEntityManager().contains(entidade)) {
			this.getEntityManager().remove(entidade);
		} 
		else {
			this.excluirPorId(entidade.getId());
		}
	}

	/**
	 * Realiza a exclusão da entidade informada no banco de dados pelo seu id.
	 * @param id
	 */
	public void excluirPorId(Object id) {
		this.getEntityManager().remove(this.recuperarEntidadePorId(id));
	}

	/**
	 * Realiza a sincronizacao do EntityManager com o banco de dados.
	 */
	public void flush() {
		this.getEntityManager().flush();
	}
	
	/**
	 * Realiza a sincronização do EntityManager com o banco de dados e desconecta todas as entidades.
	 */
	public void flushAndClear() {
		this.flush();
		this.getEntityManager().clear();
	}
}