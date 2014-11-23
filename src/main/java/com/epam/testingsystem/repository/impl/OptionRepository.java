package com.epam.testingsystem.repository.impl;

import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.OptionDao;
import com.epam.testingsystem.repository.base.AbstractHibernateDao;

@Repository
@Transactional
public class OptionRepository extends AbstractHibernateDao<Option, Integer> implements OptionDao {

	@Override
	public void selectOption(Option option, User user) {
		final Session session = getSession();
		session.beginTransaction();
		Set<Option> opts = user.getOptions();
		opts.add(option);
		user.setOptions(opts);
		session.saveOrUpdate(user);
		session.getTransaction().commit();
	}

}
