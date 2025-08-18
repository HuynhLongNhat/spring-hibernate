package dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import dto.T001Dto;
import entities.T001Entity;

/**
 * Data Access Object (DAO) class for {@link T001Entity}.
 * <p>
 * This class is responsible for retrieving user information 
 * from the database using Hibernate.
 * </p>
 *
 * <p><b>Note:</b> Transactions are handled at the Service layer. 
 * The DAO layer should only contain persistence logic (CRUD).
 * </p>
 */
public class T001Dao {

    private SessionFactory sessionFactory;

    /**
     * Setter method for injecting the Hibernate {@link SessionFactory}.
     * This will be configured and managed by Spring.
     *
     * @param sessionFactory the Hibernate SessionFactory instance
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Retrieves a user from the database based on login credentials.
     * <p>
     * The method checks if:
     * <ul>
     *   <li>The userId matches</li>
     *   <li>The password matches</li>
     *   <li>The user has not been marked as deleted (deleteYmd is null)</li>
     * </ul>
     * </p>
     *
     * @param inputDto a DTO containing the login credentials (userId, password)
     * @return the matching {@link T001Entity} if found, otherwise {@code null}
     */
    public T001Entity getUserLogin(T001Dto inputDto) {
        if (inputDto == null) return null;

        String hql = "FROM T001Entity u "
                   + "WHERE u.deleteYmd IS NULL "
                   + "AND u.userId = :userId "
                   + "AND u.password = :password";

        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", inputDto.getUserId());
        query.setParameter("password", inputDto.getPassword());

        return (T001Entity) query.uniqueResult();
    }
}
