package dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entities.T002Entity;

public class T003Dao extends HibernateDaoSupport {

    public T002Entity getCustomerById(BigDecimal customerId) throws SQLException {
        return (T002Entity) getHibernateTemplate().execute(session -> {
            T002Entity e = (T002Entity) session.get(T002Entity.class, customerId);
            return (e == null || e.getDeleteYmd() != null) ? null : e;
        });
    }

    public void insertCustomer(T002Entity customer) throws SQLException {
        getHibernateTemplate().execute(session -> {
            session.save(customer);
            return null;
        });
    }

    public void updateCustomer(T002Entity customer) throws SQLException {
        getHibernateTemplate().execute(session -> {
            session.update(customer);
            return null;
        });
    }

    public BigDecimal getNextCustomerId() throws SQLException {
        return (BigDecimal) getHibernateTemplate().execute(session -> {
            Object seqObj = session.createSQLQuery("SELECT NEXT VALUE FOR SEQ_CUSTOMER_ID")
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
        });
    }
}
