package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dto.T002Dto;
import dto.T002SCO;
import entities.T002Entity;

/**
 * Data Access Object (DAO) for {@link T002Entity}.
 * <p>
 * Provides methods to search, delete, and convert customer data 
 * using Hibernate ORM.
 * </p>
 *
 * <p><b>Note:</b> Transactions should be handled at the Service layer,
 * not directly in the DAO.
 * </p>
 */
public class T002Dao {

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
     * Searches for customers based on search criteria and applies pagination.
     *
     * @param sco    search condition object containing filters
     * @param offset starting index for pagination
     * @param limit  maximum number of records to retrieve
     * @return a map containing:
     *         <ul>
     *             <li>"customers" → list of {@link T002Dto}</li>
     *             <li>"totalCount" → total number of matching records</li>
     *         </ul>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> searchCustomers(T002SCO sco, int offset, int limit) {
        Criteria dataCrit = sessionFactory.getCurrentSession()
                .createCriteria(T002Entity.class)
                .add(Restrictions.isNull("deleteYmd"));

        // Add search filters
        if (sco.getCustomerName() != null && !sco.getCustomerName().trim().isEmpty()) {
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

        // Reset projection, apply pagination & sorting
        dataCrit.setProjection(null)
                .setResultTransformer(Criteria.ROOT_ENTITY)
                .addOrder(Order.asc("customerId"))
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<T002Entity> entities = dataCrit.list();

        // Convert entity list to DTO list
        List<T002Dto> customers = entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("customers", customers);
        result.put("totalCount", totalCount.intValue());
        return result;
    }

    /**
     * Marks customers as deleted by updating their deleteYmd field.
     *
     * @param customerIds list of customer IDs to be marked as deleted
     */
    public void deleteCustomer(List<Integer> listIds) {
        String hql = "UPDATE T002Entity c "
                   + "SET c.deleteYmd = :today "
                   + "WHERE c.customerId IN (:ids)";

        Query q = sessionFactory.getCurrentSession().createQuery(hql);
        String currentYmd =new  SimpleDateFormat("yyyyMMdd").format(new Date());
        q.setString("today", currentYmd);
        q.setParameterList("ids", listIds);

        q.executeUpdate();
    }

    /**
     * Converts a {@link T002Entity} into a {@link T002Dto}.
     *
     * @param entity the customer entity
     * @return the corresponding DTO
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
     * Converts sex code into a readable value.
     *
     * @param sexCode the stored code ("0" for Male, "1" for Female)
     * @return "Male", "Female", or the original code if unknown
     */
    private String convertSexValue(String sexCode) {
        if ("0".equals(sexCode)) return "Male";
        if ("1".equals(sexCode)) return "Female";
        return sexCode;
    }

  
}
