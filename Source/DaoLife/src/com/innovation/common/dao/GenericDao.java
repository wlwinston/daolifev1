/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.innovation.common.util.PaginationSupport;

@SuppressWarnings("unchecked")
public class GenericDao<T, ID extends Serializable> extends HibernateDaoSupport
		implements IGenericDao<T, ID> {
	private Log logger = LogFactory.getLog(getClass());

	protected Class<T> entityClass;

	public GenericDao() {

	}

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
			logger.debug("T class = " + entityClass.getName());
		}
		return entityClass;
	}

	/**
	 * 根据实例状态，选择保存或者更新
	 */
	public void saveOrUpdate(T t) throws DataAccessException {
		this.getHibernateTemplate().saveOrUpdate(t);
	}
	/**
	 * 获取实例，如果不存在则抛出异常
	 */
	public T load(ID id) throws DataAccessException {
		T load = (T) getHibernateTemplate().load(getEntityClass(), id);
		return load;
	}
	/**
	 * 根据ID获取实例
	 */
	public T get(ID id) throws DataAccessException {
		T load = (T) getHibernateTemplate().get(getEntityClass(), id);
		return load;
	}
	/**
	 * 检查实例是否存在于缓存中
	 */
	public boolean contains(T t) throws DataAccessException {
		return getHibernateTemplate().contains(t);
	}
	/**
	 * 加锁并删除指定的实体
	 */
	public void delete(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().delete(t, lockMode);
	}
	/**
	 * 删除指定的实体
	 */
	public void delete(T t) throws DataAccessException {
		getHibernateTemplate().delete(t);
	}

	public void deleteAll(Collection<T> entities) throws DataAccessException {
		getHibernateTemplate().deleteAll(entities);
	}
	
	/**
	 *
	 *示例：.find("from bean.User u where u.name=?", "test"); 或模糊查询：.find("from bean.User u where u.name like ?", "%test%");
	 *返回name属性值为test的对象（模糊查询，返回name属性值包含test的对象）
	 *
	 *@param queryString 查询语句
	 *@param value 条件对象 
	 */
	public List<T> find(String queryString, Object value)
			throws DataAccessException {
		List<T> find = (List<T>) getHibernateTemplate()
				.find(queryString, value);
		return find;
	}
	
	/**
	 *
	 *示例：String hql= "from bean.User u where u.name=? and u.password=?" ;  .find(hql, new String[]{"test", "123"});
	 *     返回用户名为test并且密码为123的所有User对象
	 *
	 *@param queryString 查询语句
	 *@param value 条件对象 
	 */
	public List<T> find(String queryString, Object[] values)
			throws DataAccessException {
		List<T> find = (List<T>) getHibernateTemplate().find(queryString,
				values);
		return find;
	}

	/**
	 *
	 *示例：.find("from bean.User");
	 *     返回所有User对象
	 *
	 *@param queryString 查询语句
	 *@param value 条件对象 
	 */
	public List<T> find(String queryString) throws DataAccessException {
		return (List<T>) getHibernateTemplate().find(queryString);
	}
	/**
	 * 刷新实例
	 */
	public void refresh(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().refresh(t, lockMode);
	}
	/**
	 * 刷新实例
	 */
	public void refresh(T t) throws DataAccessException {
		getHibernateTemplate().refresh(t);
	}
	/**
	 * 保存实例
	 */
	public Serializable save(T t) throws DataAccessException {
		return getHibernateTemplate().save(t);
	}
	/**
	 * 保存或修改所有实例
	 */
	public void saveOrUpdateAll(Collection<T> entities)
			throws DataAccessException {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}
	/**
	 * 修改实例并加锁
	 */
	public void update(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().update(t, lockMode);
	}
	/**
	 * 修改实例
	 */
	public void update(T t) throws DataAccessException {
		getHibernateTemplate().update(t);
	}

    /**
     * 获取全部实体
     */
	public List<T> list() throws DataAccessException {
		return getHibernateTemplate().loadAll(getEntityClass());

	}
	/**
	 * 使用命名的HSQL语句检索数据
	 */
	public List<T> findByNamedQuery(String queryName)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}
	/**
	 * 使用带参数的命名HSQL语句检索数据
	 */
	public List<T> findByNamedQuery(String queryName, Object value)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, value);
	}
	/**
	 * 使用带参数的命名HSQL语句检索数据
	 */
	public List<T> findByNamedQuery(String queryName, Object[] values)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex) {
		return (PaginationSupport) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						int totalCount = ((Integer) criteria.setProjection(
								Projections.rowCount()).uniqueResult())
								.intValue();
						criteria.setProjection(null);
						List items = criteria.setFirstResult(startIndex)
								.setMaxResults(pageSize).list();
						PaginationSupport ps = new PaginationSupport(items,
								totalCount, pageSize, startIndex);
						return ps;
					}
				}, true);
	}

	public  PaginationSupport findPageByQuery( final  String hql, final String countHql,final int pageSize,final int startIndex){ 
	     return (PaginationSupport)getHibernateTemplate().execute( 
	     new  HibernateCallback() { 
	       public  Object doInHibernate(Session session)  throws  HibernateException, SQLException { 
	    	   Object o = session.createQuery(countHql).iterate().next();
	    	   
	    	   int totalCount=((Long) o).intValue(); 
	    	   Query query  =  session.createQuery(hql);
	             query.setFirstResult(startIndex); 
	             query.setMaxResults(pageSize); 
	             List items  = query.list();
	          PaginationSupport ps = new PaginationSupport(items,
	       totalCount, pageSize, startIndex);
	          return ps;
	             
	             } 
	       },true); 
	  }

}
