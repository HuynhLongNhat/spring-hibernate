package service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import dao.T003Dao;
import dto.T002Dto;
import entities.T002Entity;

public class T003Service {

    private T003Dao t003Dao;

    public void setT003Dao(T003Dao t003Dao) {
        this.t003Dao = t003Dao;
    }

    public T002Dto getCustomerById(BigDecimal customerId) throws SQLException {
        T002Entity entity = t003Dao.getCustomerById(customerId);
        return entity != null ? convertToDto(entity) : null;
    }

    public void insertCustomer(T002Dto customerDto, BigDecimal psnCd) throws SQLException {
        T002Entity entity = new T002Entity();
        entity.setCustomerId(t003Dao.getNextCustomerId());
        mapDtoToEntity(customerDto, entity, psnCd);
        t003Dao.insertCustomer(entity);
    }

    public void updateCustomer(T002Dto customerDto, BigDecimal psnCd) throws SQLException {
        T002Entity entity = t003Dao.getCustomerById(customerDto.getCustomerID());
        if (entity == null) {
            throw new SQLException("Customer not found: " + customerDto.getCustomerID());
        }
        mapDtoToEntity(customerDto, entity, psnCd);
        t003Dao.updateCustomer(entity);
    }

    private T002Dto convertToDto(T002Entity entity) {
        T002Dto dto = new T002Dto();
        dto.setCustomerID(entity.getCustomerId()); // BigDecimal luôn
        dto.setCustomerName(entity.getCustomerName());
        dto.setSex(entity.getSex());
        dto.setBirthday(entity.getBirthday());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        return dto;
    }

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
