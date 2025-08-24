package dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entities.T001Entity;
import form.T001Form;

/**
 * DAO class for {@link T001Entity}.
 * <p>
 * Handles database operations related to user authentication.
 * Uses Hibernate for persistence.
 * </p>
 */
public class T001Dao {

    /** Hibernate SessionFactory, injected by Spring. */
    private SessionFactory sessionFactory;

    /**
     * Setter for {@link SessionFactory}.
     *
     * @param sessionFactory Hibernate SessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Retrieves a user by userId and password (login check).
     * Only active users (deleteYmd is null) are considered.
     *
     * @param loginForm login input containing userId and password
     * @return matched {@link T001Entity}, or null if not found
     */
    public T001Entity getUserLogin(T001Form loginForm) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(T001Entity.class);

        // add conditions: not deleted, userId matches, password matches
        criteria.add(Restrictions.isNull("deleteYmd"))
                .add(Restrictions.eq("userId", loginForm.getUserId()))
                .add(Restrictions.eq("password", loginForm.getPassword()));

        return (T001Entity) criteria.uniqueResult();
    }
}
