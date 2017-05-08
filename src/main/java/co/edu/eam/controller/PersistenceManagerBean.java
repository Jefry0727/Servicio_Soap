package co.edu.eam.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import co.edu.eam.ejb.PersistenceManagerLocal;


/**
 * 
 * @author Jefry Londoño <jjmb2789@gmail.com>
 * @11/04/2017
 * @version
 */
@Stateless(mappedName = "PersistenceManagerBean", name = "PersistenceManagerBean")
public class PersistenceManagerBean implements PersistenceManagerLocal {

	@PersistenceContext(unitName = "servicio_soap")
	private EntityManager em;

	public Object getDelegate() {
		return this.em.getDelegate();
	}

	@Override
	public void persist(Object o) {
		em.persist(o);
	}

	@Override
	public Object merge(Object o) {
		return em.merge(o);
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	public void remove(@SuppressWarnings("rawtypes") Class clazz, Integer id) {
		Object obj = em.getReference(clazz, id);
		em.remove(obj);
	}

	/**
	 * se busca una nota y se obtiene toda la informacion de esta
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object find(Class clazz,long id) {		
		return em.find(clazz, id);
	}

	@Override
	public void update(Object obj) {
		em.merge(obj);
	}

	/**
	 * Se crea instancia de x consulta.
	 */
	@Override
	public Query createQuery(String query) {
		Query queryInstance = (Query) em.createQuery(query);
		return queryInstance;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> findByString(Class clazz, String query, String nombre) {
		Query q = em.createQuery(query);
		q.setParameter("nombre", nombre);
		return q.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> findByLong(Class clazz, String query, long id) {
		Query q =em.createNamedQuery(query, clazz);
		q.setParameter("id", id);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findByLBigDecimal(Class clazz, String query, BigDecimal id) throws Exception {
		
		Integer numero = (Integer.parseInt(id.toString()));
		Query q =em.createNamedQuery(query, clazz);
		q.setParameter("numero", id);
		return q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findByEstado(Class clazz, String query, long id, String estado) {
		Query q =em.createNamedQuery(query, clazz);
		q.setParameter("id", id);
		q.setParameter("estado", estado);
		return q.getSingleResult();
	}





}
