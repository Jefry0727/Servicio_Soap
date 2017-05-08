package co.edu.eam.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author Jefry Londoño <jjmb2789@gmail.com>
 * @11/04/2017
 * @version
 */
public interface PersistenceManagerLocal {

	public void update(Object obj);

	public void persist(Object p1);
	
	@SuppressWarnings("rawtypes")
	public void remove(Class clazz, Integer id);

	public Query createQuery(String p1);

	public EntityManager getEntityManager();

	Object merge(Object obj);

	@SuppressWarnings("rawtypes")
	Object find(Class clazz, long id);
	
	
	@SuppressWarnings("rawtypes")
	List<Object> findByString(Class clazz, String query, String nombre);
	
	/**
	 * Busca por id de la foranea a la que hace referencia
	 * @param clazz
	 * @param query
	 * @param id
	 * @return
	 */
	List<Object> findByLong(Class clazz, String query,long id);

	Object findByLBigDecimal(Class clazz, String query,BigDecimal id) throws Exception;
	
	Object findByEstado(Class clazz, String query,long id, String estado);
}
