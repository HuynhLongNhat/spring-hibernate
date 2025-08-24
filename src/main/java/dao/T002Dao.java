package dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import common.Constants;
import dto.T002Dto;
import dto.T002SCO;
import entities.T002Entity;
import utils.Helper;

/**
 * DAO for {@link T002Entity}.
 * <p>
 * Handles customer search, soft-delete, and entity-to-DTO conversion.
 * </p>
 */
public class T002Dao {

    /** Hibernate session factory, injected by Spring. */
    private SessionFactory sessionFactory;

    /**
     * Setter injection for {@link SessionFactory}.
     *
     * @param sessionFactory Hibernate SessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Searches customers with given conditions and pagination.
     *
     * @param sco    search condition object
     * @param offset starting row index
     * @param limit  max rows per page
     * @return map containing customer list and total count
     */
    public Map<String, Object> searchCustomers(T002SCO sco, int offset, int limit) {
        Criteria dataCrit = sessionFactory.getCurrentSession()
                .createCriteria(T002Entity.class)
                .add(Restrictions.isNull("deleteYmd")); // only non-deleted

        // Apply filters
        if (!Helper.isEmpty(sco.getCustomerName())) {
            dataCrit.add(Restrictions.like("customerName", "%" + sco.getCustomerName().trim() + "%"));
        }
        if (sco.getSex() != null && !sco.getSex().trim().isEmpty()) {
            dataCrit.add(Restrictions.eq("sex", sco.getSex().trim()));
        }
        if (sco.getBirthdayFrom() != null && !sco.getBirthdayFrom().trim().isEmpty()) {
            dataCrit.add(Restrictions.ge("birthday", sco.getBirthdayFrom().trim()));
        }
        if (sco.getBirthdayTo() != null && !sco.getBirthdayTo().trim().isEmpty()) {
            dataCrit.add(Restrictions.le("birthday", sco.getBirthdayTo().trim()));
        }

        // Count total records
        Number totalCount = (Number) dataCrit.setProjection(Projections.rowCount())
                .uniqueResult();

        // Reset projection, add sorting & pagination
        dataCrit.setProjection(null)
                .setResultTransformer(Criteria.ROOT_ENTITY)
                .addOrder(Order.asc("customerId"))
                .setFirstResult(offset)
                .setMaxResults(limit);

        // Execute query
        List<?> rawList = dataCrit.list();
        List<T002Entity> entities = rawList.stream()
                .filter(T002Entity.class::isInstance)
                .map(T002Entity.class::cast)
                .collect(Collectors.toList());

        // Convert to DTO list
        List<T002Dto> customers = entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // Build result
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.PRAM_CUSTOMERS, customers);
        result.put(Constants.PARAM_TOTAL_COUNT, totalCount.intValue());
        return result;
    }

    /**
     * Soft-deletes customers by setting deleteYmd.
     *
     * @param listIds customer IDs to delete
     */
    public void deleteCustomer(List<Integer> listIds) {
        Timestamp currentDay = new Timestamp(System.currentTimeMillis());

        Criteria crit = sessionFactory.getCurrentSession()
                .createCriteria(T002Entity.class)
                .add(Restrictions.in("customerId", listIds));

        List<?> rawList = crit.list();
        List<T002Entity> entities = rawList.stream()
                .filter(T002Entity.class::isInstance)
                .map(T002Entity.class::cast)
                .collect(Collectors.toList());

        // Mark as deleted
        for (T002Entity entity : entities) {
            entity.setDeleteYmd(currentDay);
            sessionFactory.getCurrentSession().update(entity);
        }
    }

    /**
     * Converts {@link T002Entity} to {@link T002Dto}.
     */
    private T002Dto convertToDto(T002Entity entity) {
        T002Dto dto = new T002Dto();
        dto.setCustomerID(entity.getCustomerId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setSex(convertSexValue(entity.getSex()));
        dto.setBirthday(entity.getBirthday());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    /**
     * Converts sex code to readable string.
     * "0" -> Male, "1" -> Female
     */
    private String convertSexValue(String sexCode) {
        if ("0".equals(sexCode)) return "Male";
        if ("1".equals(sexCode)) return "Female";
        return sexCode;
    }
}
