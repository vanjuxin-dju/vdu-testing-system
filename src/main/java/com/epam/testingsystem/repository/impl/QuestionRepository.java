package com.epam.testingsystem.repository.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.repository.QuestionDao;
import com.epam.testingsystem.repository.base.AbstractHibernateDao;

@Repository
@Transactional
public class QuestionRepository extends AbstractHibernateDao<Question, Integer> implements QuestionDao {

	@Override
	public void changeTest(Question question, Test test) {
		// TODO Auto-generated method stub
		final Session session = getSession();
		session.beginTransaction();
		Query qr = session
				.createQuery("update Question set idTest = " + test.getId() 
						+ " where idQuestion = " + question.getId());
		qr.executeUpdate();
		session.getTransaction().commit();
	}

}
