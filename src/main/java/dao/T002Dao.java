package dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dto.T002Dto;
import dto.T002SCO;
import entities.T002Entity;

public class T002Dao extends HibernateDaoSupport {

    @SuppressWarnings("unchecked")
    public Map<String, Object> searchCustomers(T002SCO sco, int offset, int limit) {
        return (Map<String, Object>) getHibernateTemplate().execute(session -> {
            // Criteria dựa trên entity
            Criteria dataCrit = session.createCriteria(T002Entity.class)
                    .add(Restrictions.isNull("deleteYmd")); // field trong entity

            // Thêm điều kiện tìm kiếm
            if (sco.getCustomerName() != null && !sco.getCustomerName().trim().isEmpty()) {
                dataCrit.add(Restrictions.like("customerName",
                        "%" + sco.getCustomerName().trim() + "%"));
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

            // Lấy tổng số bản ghi
            Number totalCount = (Number) dataCrit.setProjection(Projections.rowCount())
                    .uniqueResult();

            // Reset projection + phân trang
            dataCrit.setProjection(null)
                    .setResultTransformer(Criteria.ROOT_ENTITY)
                    .addOrder(Order.asc("customerId"))
                    .setFirstResult(offset)
                    .setMaxResults(limit);

            List<T002Entity> entities = dataCrit.list();

            // Convert sang DTO
            List<T002Dto> customers = entities.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            Map<String, Object> result = new HashMap<>();
            result.put("customers", customers);
            result.put("totalCount", totalCount.intValue());
            return result;
        });
    }

    public void deleteCustomer(List<BigDecimal> customerIds) {
        getHibernateTemplate().execute(session -> {
            // HQL update entity field
            String hql = "UPDATE T002Entity c "
                       + "SET c.deleteYmd = :today "
                       + "WHERE c.customerId IN (:ids)";

            Query q = session.createQuery(hql)
                    .setString("today", currentYmd())
                    .setParameterList("ids", customerIds);

            return q.executeUpdate();
        });
    }

    private T002Dto convertToDto(T002Entity entity) {
        T002Dto dto = new T002Dto();
        dto.setCustomerID(entity.getCustomerId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setSex(convertSexValue(entity.getSex()));
        dto.setBirthday(entity.getBirthday());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    private String convertSexValue(String sexCode) {
        if ("0".equals(sexCode)) return "Male";
        if ("1".equals(sexCode)) return "Female";
        return sexCode;
    }

    private String currentYmd() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}
