package com.epam.testingsystem.repository.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractHibernateDao<T, PK extends Serializable> implements GenericDao<T, PK> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getGenericEntityClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PK create(T obj) {
		return (PK) getSession().save(obj);
	}

	@Override
	public T update(T o) {
		getSession().update(o);
		return o;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Criteria cr = getSession().createCriteria(this.getGenericEntityClass());
		return (List<T>) cr.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(Criterion criterion) {
		Criteria crit = getSession().createCriteria(this.getGenericEntityClass());
		crit.add(criterion);
		return (List<T>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(PK id) {
		return (T) getSession().get(this.getGenericEntityClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(PK id) {
		T persistentObject = (T) getSession().load(this.getGenericEntityClass(), id);
		try {
			getSession().delete(persistentObject);
		} catch (NonUniqueObjectException e) {
			// in a case of detached object
			T instance = (T) getSession().merge(persistentObject);
			getSession().delete(instance);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(T persistentObject) {
		try {
			getSession().delete(persistentObject);
		} catch (NonUniqueObjectException e) {
			// in a case of detached object
			T instance = (T) getSession().merge(persistentObject);
			getSession().delete(instance);
		}
	}
}