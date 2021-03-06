package com.epam.testingsystem.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.repository.TestDao;
import com.epam.testingsystem.repository.base.AbstractHibernateDao;

@Repository
@Transactional
public class TestRepository extends AbstractHibernateDao<Test, Integer> implements TestDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findTestsBySubject(Subject subject) {
		Criteria qr = getSession()
				.createCriteria(Test.class, "test")
				.add(Restrictions.eq("subject", subject));
		return qr.list();
	}


	@Override
	public void changeSubject(Test test, Subject subject) {
		final Session session = getSession();
		session.beginTransaction();
		Query qr = session
				.createQuery("update Test set idSubject = " + subject.getId() 
						+ " where idTest = " + test.getId());
		qr.executeUpdate();
		session.getTransaction().commit();
	}

}
