package service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.T002Dao;
import dto.T002SCO;

public class T002Service {

    private T002Dao t002Dao;

    public void setT002Dao(T002Dao t002Dao) {
        this.t002Dao = t002Dao;
    }

    public Map<String, Object> searchCustomers(T002SCO sco, int offset, int limit) throws SQLException {
        try {
            return t002Dao.searchCustomers(sco, offset, limit);
        } catch (Exception e) {
            throw new SQLException("Search customers failed", e);
        }
    }

    public void deleteCustomers(List<BigDecimal> customerIds) throws SQLException {
        try {
            t002Dao.deleteCustomer(customerIds);
        } catch (Exception e) {
            throw new SQLException("Delete customers failed", e);
        }
    }
}