package dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.hibernate.SessionFactory;

import entities.T002Entity;

/**
 * Data Access Object (DAO) for {@link T002Entity}.
 * <p>
 * Provides database operations for retrieving, inserting, updating, 
 * and generating IDs for customers using Hibernate.
 * </p>
 *
 * <p><b>Note:</b> Transactions should be managed at the Service layer,
 * not directly inside this DAO.
 * </p>
 */
public class T003Dao {

    private SessionFactory sessionFactory;

    /**
     * Injects the Hibernate {@link SessionFactory}.
     * This will be configured by Spring.
     *
     * @param sessionFactory Hibernate session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param customerId the ID of the customer
     * @return the corresponding {@link T002Entity}, or {@code null} if
     *         not found or already marked as deleted
     * @throws SQLException if any database error occurs
     */
    public T002Entity getCustomerById(BigDecimal customerId) throws SQLException {
        T002Entity e = (T002Entity) sessionFactory
                .getCurrentSession()
                .get(T002Entity.class, customerId);

        return (e == null || e.getDeleteYmd() != null) ? null : e;
    }

    /**
     * Inserts a new customer into the database.
     *
     * @param customer the entity to persist
     * @throws SQLException if any database error occurs
     */
    public void insertCustomer(T002Entity customer) throws SQLException {
        sessionFactory.getCurrentSession().save(customer);
    }

    /**
     * Updates an existing customer in the database.
     *
     * @param customer the entity with updated values
     * @throws SQLException if any database error occurs
     */
    public void updateCustomer(T002Entity customer) throws SQLException {
        sessionFactory.getCurrentSession().update(customer);
    }

    /**
     * Retrieves the next available customer ID from the sequence.
     *
     * @return the next customer ID as {@link BigDecimal}
     * @throws SQLException if the sequence does not return a valid value
     */
    public BigDecimal getNextCustomerId() throws SQLException {
        Object seqObj = sessionFactory
                .getCurrentSession()
                .createSQLQuery("SELECT NEXT VALUE FOR SEQ_CUSTOMER_ID")
                .uniqueResult();

        if (seqObj == null) {
            throw new SQLException("SEQ_CUSTOMER_ID did not return a value");
        }
        if (seqObj instanceof BigDecimal) {
            return (BigDecimal) seqObj;
        } else if (seqObj instanceof Number) {
            return BigDecimal.valueOf(((Number) seqObj).longValue());
        } else {
            return new BigDecimal(seqObj.toString());
        }
    }
}
