package com.epam.testingsystem.repository.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.SubjectDao;
import com.epam.testingsystem.repository.base.AbstractHibernateDao;

@Repository
@Transactional
public class SubjectRepository extends AbstractHibernateDao<Subject, Integer> implements SubjectDao {

	@Override
	public void addAdmin(User user, Subject subject) {
		final Session session = getSession();
		session.beginTransaction();
		Query qr = session
				.createQuery("update Subject set idUser = " + user.getId() 
						+ " where idSubject = " + subject.getId());
		qr.executeUpdate();
		session.getTransaction().commit();
	}

}
