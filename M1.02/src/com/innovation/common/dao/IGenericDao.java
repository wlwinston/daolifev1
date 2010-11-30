/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import com.innovation.common.util.PaginationSupport;

public interface IGenericDao<T, ID extends Serializable> {
	public T load(ID id) throws DataAccessException;

	public T get(ID id)throws DataAccessException;

	public boolean contains(T t) throws DataAccessException;

	public void refresh(T t, LockMode lockMode) throws DataAccessException;

	public void refresh(T t) throws DataAccessException;

	public Serializable save(T t) throws DataAccessException;

	public void saveOrUpdate(T t) throws DataAccessException;

	public void saveOrUpdateAll(Collection<T> entities)
			throws DataAccessException;

	public void update(T t, LockMode lockMode) throws DataAccessException;

	public void update(T t) throws DataAccessException;
	
	public void update(String sqlString) throws DataAccessException, SQLException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;

	public void delete(T t, LockMode lockMode) throws DataAccessException;

	public void delete(T t) throws DataAccessException;

	public void deleteAll(Collection<T> entities) throws DataAccessException;

	public List<T> find(String queryString, Object value)
			throws DataAccessException;

	public List<T> find(String queryString, Object[] values)
			throws DataAccessException;

	public List<T> find(String queryString) throws DataAccessException;
	
	public List findWithoutT(String queryString) throws DataAccessException;

	public List<T> list()throws DataAccessException;
	
	public List<T> findByNamedQuery(String queryName)throws DataAccessException ;
    
    	public List<T> findByNamedQuery(String queryName, Object value)throws DataAccessException ;
    
        public List<T> findByNamedQuery(String queryName, Object[] values)throws DataAccessException ;
    

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);

	public PaginationSupport findPageByQuery(final String hql,
			final String countHql, final int pageSize, final int startIndex);

}