package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.T002Dao;
import dto.T002SCO;

/**
 * Service class for managing customer-related operations.
 * <p>
 * This class acts as an intermediary between the Controller (Action)
 * and the {@link dao.T002Dao} data access layer. It handles business logic
 * for searching and deleting customers while providing exception wrapping
 * for consistent error handling.
 * </p>
 */
public class T002Service {

    private T002Dao t002Dao;

    /**
     * Injects the {@link T002Dao} dependency.
     * This will be configured by Spring.
     *
     * @param t002Dao DAO responsible for customer operations
     */
    public void setT002Dao(T002Dao t002Dao) {
        this.t002Dao = t002Dao;
    }

    /**
     * Searches for customers based on given search criteria and pagination.
     *
     * @param sco    search condition object containing filters (e.g., name, sex, birthday range)
     * @param offset the starting index for pagination
     * @param limit  the maximum number of results to retrieve
     * @return a map containing:
     *         <ul>
     *           <li>"customers" → a list of {@code T002Dto}</li>
     *           <li>"totalCount" → total number of matching records</li>
     *         </ul>
     * @throws SQLException if any error occurs during database query
     */
    public Map<String, Object> searchCustomers(T002SCO sco, int offset, int limit) throws SQLException {
        try {
            return t002Dao.searchCustomers(sco, offset, limit);
        } catch (Exception e) {
            throw new SQLException("Search customers failed", e);
        }
    }

    /**
     * Performs a logical delete (soft delete) of customers
     * by setting their delete timestamp.
     *
     * @param customerIds list of customer IDs to be deleted
     * @throws SQLException if the delete operation fails
     */
    public void deleteCustomers(List<Integer> listIds) throws SQLException {
        try {
            t002Dao.deleteCustomer(listIds);
        } catch (Exception e) {
            throw new SQLException("Delete customers failed", e);
        }
    }
}
