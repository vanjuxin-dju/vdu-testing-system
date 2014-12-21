package com.epam.testingsystem.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.repository.QuestionDao;
import com.epam.testingsystem.repository.base.AbstractHibernateDao;

@Repository
@Transactional
public class QuestionRepository extends AbstractHibernateDao<Question, Integer> implements QuestionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findQuestionsByTest(Test test) {
		Criteria qr = getSession()
				.createCriteria(Question.class, "question")
				.add(Restrictions.eq("test", test))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return qr.list();
	}
	
	@Override
	public void changeTest(Question question, Test test) {
		final Session session = getSession();
		session.beginTransaction();
		Query qr = session
				.createQuery("update Question set idTest = " + test.getId() 
						+ " where idQuestion = " + question.getId());
		qr.executeUpdate();
		session.getTransaction().commit();
	}

}
