package dao;

import java.sql.SQLException;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import entities.T002Entity;

/**
 * DAO for {@link T002Entity}.
 * <p>
 * Handles basic CRUD and sequence generation using Hibernate.
 * </p>
 */
public class T003Dao {

    /** Hibernate session factory (injected by Spring). */
    private SessionFactory sessionFactory;

    /**
     * Setter for injecting {@link SessionFactory}.
     *
     * @param sessionFactory Hibernate SessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets a customer by ID (ignores logically deleted rows).
     *
     * @param customerId customer ID
     * @return matching entity or {@code null} if not found/deleted
     * @throws SQLException on database error
     */
    public T002Entity getCustomerById(int customerId) throws SQLException {
        Criteria crit = sessionFactory.getCurrentSession()
                .createCriteria(T002Entity.class)
                .add(Restrictions.eq("customerId", customerId))
                .add(Restrictions.isNull("deleteYmd"));

        Object result = crit.uniqueResult();
        return (result instanceof T002Entity) ? (T002Entity) result : null;
    }

    /**
     * Persists a new customer.
     *
     * @param customer entity to insert
     * @throws SQLException on database error
     */
    public void insertCustomer(T002Entity customer) throws SQLException {
        sessionFactory.getCurrentSession().save(customer);
    }

    /**
     * Updates an existing customer.
     *
     * @param customer entity with updated values
     * @throws SQLException on database error
     */
    public void updateCustomer(T002Entity customer) throws SQLException {
        sessionFactory.getCurrentSession().update(customer);
    }

    /**
     * Gets the next customer ID from sequence {@code SEQ_CUSTOMER_ID}.
     *
     * @return next sequence value as int
     * @throws SQLException if value is null or not parsable
     */
    public int getNextCustomerId() throws SQLException {
        Object seqObj = sessionFactory
                .getCurrentSession()
                .createSQLQuery("SELECT NEXT VALUE FOR SEQ_CUSTOMER_ID")
                .uniqueResult();

        if (seqObj == null) {
            throw new SQLException("SEQ_CUSTOMER_ID returned null");
        }

        if (seqObj instanceof Number) {
            return ((Number) seqObj).intValue();
        }
        try {
            return Integer.parseInt(seqObj.toString());
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid sequence value: " + seqObj, e);
        }
    }
}
