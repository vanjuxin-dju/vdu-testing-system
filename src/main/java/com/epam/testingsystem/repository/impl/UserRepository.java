package com.epam.testingsystem.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.UserDao;
import com.epam.testingsystem.repository.base.AbstractHibernateDao;

@Repository
@Transactional
public class UserRepository extends AbstractHibernateDao<User, Integer> implements UserDao {

	@Override
	public User findByCredentials(String login, String password) {
		Criteria cr = getSession()
				.createCriteria(User.class, "users")
				.add(Restrictions.eq("nickName", login))
				.add(Restrictions.eq("password", password));
		return (User) cr.uniqueResult();
	}

}
