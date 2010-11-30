/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	 * ���ʵ��״̬��ѡ�񱣴���߸���
	 */
	public void saveOrUpdate(T t) throws DataAccessException {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	/**
	 * ��ȡʵ�����������׳��쳣
	 */
	public T load(ID id) throws DataAccessException {
		T load = (T) getHibernateTemplate().load(getEntityClass(), id);
		return load;
	}

	/**
	 * ���ID��ȡʵ��
	 */
	public T get(ID id) throws DataAccessException {
		T load = (T) getHibernateTemplate().get(getEntityClass(), id);
		return load;
	}

	/**
	 * ���ʵ���Ƿ�����ڻ�����
	 */
	public boolean contains(T t) throws DataAccessException {
		return getHibernateTemplate().contains(t);
	}

	/**
	 * ����ɾ��ָ����ʵ��
	 */
	public void delete(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().delete(t, lockMode);
	}

	/**
	 * ɾ��ָ����ʵ��
	 */
	public void delete(T t) throws DataAccessException {
		getHibernateTemplate().delete(t);
	}

	public void deleteAll(Collection<T> entities) throws DataAccessException {
		getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 
	 * ʾ��.find("from bean.User u where u.name=?", "test"); ��ģ���ѯ��.find("from
	 * bean.User u where u.name like ?", "%test%");
	 * ����name����ֵΪtest�Ķ���ģ���ѯ������name����ֵ��test�Ķ���
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param value
	 *            �������
	 */
	public List<T> find(String queryString, Object value)
			throws DataAccessException {
		List<T> find = (List<T>) getHibernateTemplate()
				.find(queryString, value);
		return find;
	}

	/**
	 * 
	 * ʾ��String hql= "from bean.User u where u.name=? and u.password=?" ;
	 * .find(hql, new String[]{"test", "123"}); �����û���Ϊtest��������Ϊ123������User����
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param value
	 *            �������
	 */
	public List<T> find(String queryString, Object[] values)
			throws DataAccessException {
		List<T> find = (List<T>) getHibernateTemplate().find(queryString,
				values);
		return find;
	}

	/**
	 * 
	 * ʾ��.find("from bean.User"); ��������User����
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param value
	 *            �������
	 */
	public List<T> find(String queryString) throws DataAccessException {
		return (List<T>) getHibernateTemplate().find(queryString);
	}
	
	
	/**
	 * 
	 * ʾ��.find("from bean.User"); ��������User����
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param value
	 *            �������
	 */
	public List findWithoutT(String queryString) throws DataAccessException {
		return (List) getHibernateTemplate().find(queryString);
	}

	/**
	 * ˢ��ʵ��
	 */
	public void refresh(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().refresh(t, lockMode);
	}

	/**
	 * ˢ��ʵ��
	 */
	public void refresh(T t) throws DataAccessException {
		getHibernateTemplate().refresh(t);
	}

	/**
	 * ����ʵ��
	 */
	public Serializable save(T t) throws DataAccessException {
		return getHibernateTemplate().save(t);
	}

	/**
	 * ������޸�����ʵ��
	 */
	public void saveOrUpdateAll(Collection<T> entities)
			throws DataAccessException {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * �޸�ʵ�����
	 */
	public void update(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().update(t, lockMode);
	}

	/**
	 * �޸�ʵ��
	 */
	public void update(T t) throws DataAccessException {
		getHibernateTemplate().update(t);
	}

	/**
	 * �޸�ʵ��ʹ��sql����
	 * @throws SQLException 
	 * @throws SystemException 
	 * @throws HeuristicRollbackException 
	 * @throws HeuristicMixedException 
	 * @throws RollbackException 
	 * @throws IllegalStateException 
	 * @throws SecurityException 
	 */
	public void update(String sqlString) throws DataAccessException, SQLException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		// getHibernateTemplate().update(sqlString);
		SessionFactory sessionFactory = getHibernateTemplate()
				.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Connection conn = session.connection();
		PreparedStatement stmt;
			stmt = conn
					.prepareStatement(sqlString);
		stmt.executeUpdate();
		tx.commit();
		conn.close();
		session.close();
		
	}
	
	
	/**
	 * ��ȡȫ��ʵ��
	 */
	public List<T> list() throws DataAccessException {
		return getHibernateTemplate().loadAll(getEntityClass());

	}

	/**
	 * ʹ�������HSQL���������
	 */
	public List<T> findByNamedQuery(String queryName)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * ʹ�ô���������HSQL���������
	 */
	public List<T> findByNamedQuery(String queryName, Object value)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, value);
	}

	/**
	 * ʹ�ô���������HSQL���������
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

	public PaginationSupport findPageByQuery(final String hql,
			final String countHql, final int pageSize, final int startIndex) {
		return (PaginationSupport) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Object o = session.createQuery(countHql).iterate()
								.next();

						int totalCount = ((Long) o).intValue();
						Query query = session.createQuery(hql);
						query.setFirstResult(startIndex);
						query.setMaxResults(pageSize);
						List items = query.list();
						PaginationSupport ps = new PaginationSupport(items,
								totalCount, pageSize, startIndex);
						return ps;

					}
				}, true);
	}

}
