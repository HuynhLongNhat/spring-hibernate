package service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import dao.T003Dao;
import dto.T002Dto;
import entities.T002Entity;

/**
 * Service class responsible for managing single customer operations
 * such as retrieval, insertion, and update.
 * <p>
 * This class acts as a bridge between the controller layer (Action)
 * and the {@link dao.T003Dao} data access layer. It handles mapping
 * between {@link dto.T002Dto} and {@link entities.T002Entity},
 * and manages audit fields (insert/update timestamps, user codes).
 * </p>
 */
public class T003Service {

    private T003Dao t003Dao;

    /**
     * Injects the {@link T003Dao} dependency.
     * This will be configured by Spring.
     *
     * @param t003Dao DAO for customer persistence operations
     */
    public void setT003Dao(T003Dao t003Dao) {
        this.t003Dao = t003Dao;
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param customerId the unique identifier of the customer
     * @return a {@link T002Dto} representing the customer,
     *         or {@code null} if not found or deleted
     * @throws SQLException if an error occurs while accessing the database
     */
    public T002Dto getCustomerById(BigDecimal customerId) throws SQLException {
        T002Entity entity = t003Dao.getCustomerById(customerId);
        return entity != null ? convertToDto(entity) : null;
    }

    /**
     * Inserts a new customer into the database.
     * A new customer ID will be generated automatically.
     *
     * @param customerDto the customer data to be inserted
     * @param psnCd       the person code of the user performing the insert
     * @throws SQLException if an error occurs while saving the customer
     */
    public void insertCustomer(T002Dto customerDto, BigDecimal psnCd) throws SQLException {
        T002Entity entity = new T002Entity();
        entity.setCustomerId(t003Dao.getNextCustomerId());
        mapDtoToEntity(customerDto, entity, psnCd);
        t003Dao.insertCustomer(entity);
    }

    /**
     * Updates an existing customer in the database.
     *
     * @param customerDto the customer data with updated values
     * @param psnCd       the person code of the user performing the update
     * @throws SQLException if the customer does not exist or the update fails
     */
    public void updateCustomer(T002Dto customerDto, BigDecimal psnCd) throws SQLException {
        T002Entity entity = t003Dao.getCustomerById(customerDto.getCustomerID());
        if (entity == null) {
            throw new SQLException("Customer not found: " + customerDto.getCustomerID());
        }
        mapDtoToEntity(customerDto, entity, psnCd);
        t003Dao.updateCustomer(entity);
    }

    /**
     * Converts a {@link T002Entity} object into a {@link T002Dto}.
     *
     * @param entity the entity object
     * @return the corresponding DTO
     */
    private T002Dto convertToDto(T002Entity entity) {
        T002Dto dto = new T002Dto();
        dto.setCustomerID(entity.getCustomerId()); 
        dto.setCustomerName(entity.getCustomerName());
        dto.setSex(entity.getSex());
        dto.setBirthday(entity.getBirthday());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    /**
     * Maps a {@link T002Dto} into a {@link T002Entity} while
     * maintaining audit fields (insert/update timestamps and user codes).
     *
     * @param dto    the source DTO
     * @param entity the target entity to populate
     * @param psnCd  the person code of the user performing the operation
     */
    private void mapDtoToEntity(T002Dto dto, T002Entity entity, BigDecimal psnCd) {
        entity.setCustomerName(dto.getCustomerName());
        entity.setSex(dto.getSex());
        entity.setBirthday(dto.getBirthday());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setDeleteYmd(null);

        Timestamp now = new Timestamp(new Date().getTime());
        if (entity.getInsertYmd() == null) {
            entity.setInsertYmd(now);
            entity.setInsertPsnCd(psnCd);
        }
        entity.setUpdateYmd(now);
        entity.setUpdatePsnCd(psnCd);
    }
}
