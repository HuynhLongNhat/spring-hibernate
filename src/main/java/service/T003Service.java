package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import common.Constants;
import dao.T003Dao;
import entities.T002Entity;
import form.T003Form;

/**
 * Service for single customer operations (T003 screen).
 */
public class T003Service {

    /**
     * DAO for customer operations.
     */
    private T003Dao t003Dao;

    /**
     * Sets the customer DAO (injected by Spring).
     *
     * @param t003Dao customer DAO
     */
    public void setT003Dao(T003Dao t003Dao) {
        this.t003Dao = t003Dao;
    }

    /**
     * Gets a customer by ID and fills the form.
     *
     * @param form    customer form
     * @param request HTTP request
     */
    public void getCustomerById(T003Form form, HttpServletRequest request) {
        int customerId = form.getCustomerId();

        if (customerId != 0) {
            try {
                T002Entity entity = t003Dao.getCustomerById(customerId);
                if (entity != null) {
                    form.setCustomerId(entity.getCustomerId());
                    form.setCustomerName(entity.getCustomerName());
                    form.setSex(entity.getSex());
                    form.setBirthday(entity.getBirthday());
                    form.setEmail(entity.getEmail());
                    form.setAddress(entity.getAddress());
                    form.setMode(Constants.MODE_EDIT);
                } else {
                    form.setMode(Constants.MODE_ADD);
                }
            } catch (Exception e) {
                form.setMode(Constants.MODE_ADD);
            }
        } else {
            form.setMode(Constants.MODE_ADD);
        }
    }

    /**
     * Saves a customer (insert or update).
     *
     * @param editForm customer form
     * @param request  HTTP request
     * @param psnCd    user code (audit)
     * @return true if success, false otherwise
     */
    public boolean saveCustomer(T003Form editForm, HttpServletRequest request, Integer psnCd) {
        try {
            T002Entity entity;

            if (Constants.MODE_ADD.equals(editForm.getMode())) {
                // Insert
                entity = new T002Entity();
                entity.setCustomerId(t003Dao.getNextCustomerId());
                mapFormToEntity(editForm, entity, psnCd, true);
                t003Dao.insertCustomer(entity);
            } else {
                // Update
                entity = t003Dao.getCustomerById(editForm.getCustomerId());
                if (entity == null) {
                    throw new SQLException("Customer not found: " + editForm.getCustomerId());
                }
                mapFormToEntity(editForm, entity, psnCd, false);
                t003Dao.updateCustomer(entity);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Maps form data to entity and sets audit fields.
     *
     * @param form     customer form
     * @param entity   entity object
     * @param psnCd    user code (audit)
     * @param isInsert true if insert, false if update
     */
    private void mapFormToEntity(T003Form form, T002Entity entity, int psnCd, boolean isInsert) {
        entity.setCustomerName(form.getCustomerName());
        entity.setSex(form.getSex());
        entity.setBirthday(form.getBirthday());
        entity.setEmail(form.getEmail());
        entity.setAddress(form.getAddress());
        entity.setDeleteYmd(null);

        Timestamp now = new Timestamp(new Date().getTime());
        if (isInsert) {
            entity.setInsertYmd(now);
            entity.setInsertPsnCd(psnCd);
        }
        entity.setUpdateYmd(now);
        entity.setUpdatePsnCd(psnCd);
    }
}
