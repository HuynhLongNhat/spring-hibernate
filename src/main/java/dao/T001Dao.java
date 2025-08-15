package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import dto.T001Dto;
import entities.T001Entity;

/**
 * DAO class for login operations using Hibernate.
 */
public class T001Dao {

	 private SessionFactory sessionFactory;
	    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

	    public T001Entity getUserLogin(T001Dto t001Dto) {
	        Session session = sessionFactory.getCurrentSession();
	        return (T001Entity) session.createQuery(
	            "from T001Entity where userId = :userId and password = :password and deleteYmd is null")
	            .setParameter("userId", t001Dto.getUserId())
	            .setParameter("password", t001Dto.getPassword())
	            .uniqueResult();
	    }
}
