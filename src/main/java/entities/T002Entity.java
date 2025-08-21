package entities;

import java.sql.Timestamp;

/**
 * Entity class representing the T002 table.
 * Stores customer information such as ID, name, sex, birthday,
 * email, address, and audit fields for insert, update, and delete.
 * 
 * This class follows JavaBean conventions with getters and setters
 * for all private fields.
 * 
 * @author You
 * @since 2025
 */
public class T002Entity {

    /** Customer ID (NUMERIC(8)) */
    private Integer customerId;

    /** Customer name (VARCHAR(50)) */
    private String customerName;

    /** Sex (VARCHAR(1)) */
    private String sex;

    /** Birthday (VARCHAR(10)) */
    private String birthday;

    /** Email (VARCHAR(40)) */
    private String email;

    /** Address (VARCHAR(256)) */
    private String address;

    /** Deletion timestamp (TIMESTAMP) */
    private Timestamp deleteYmd;

    /** Insertion timestamp (TIMESTAMP) */
    private Timestamp insertYmd;

    /** Insertion personal code (NUMERIC(5)) */
    private Integer insertPsnCd;

    /** Update timestamp (TIMESTAMP) */
    private Timestamp updateYmd;

    /** Update personal code (NUMERIC(5)) */
    private Integer updatePsnCd;

    // ================= Getters & Setters =================

    /**
     * Gets the customer ID.
     * 
     * @return customer ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     * 
     * @param customerId customer ID
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the customer name.
     * 
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     * 
     * @param customerName customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the sex.
     * 
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex.
     * 
     * @param sex sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets the birthday.
     * 
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday.
     * 
     * @param birthday birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets the email.
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the address.
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * 
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the deletion timestamp.
     * 
     * @return deletion timestamp
     */
    public Timestamp getDeleteYmd() {
        return deleteYmd;
    }

    /**
     * Sets the deletion timestamp.
     * 
     * @param deleteYmd deletion timestamp
     */
    public void setDeleteYmd(Timestamp deleteYmd) {
        this.deleteYmd = deleteYmd;
    }

    /**
     * Gets the insertion timestamp.
     * 
     * @return insertion timestamp
     */
    public Timestamp getInsertYmd() {
        return insertYmd;
    }

    /**
     * Sets the insertion timestamp.
     * 
     * @param insertYmd insertion timestamp
     */
    public void setInsertYmd(Timestamp insertYmd) {
        this.insertYmd = insertYmd;
    }

    /**
     * Gets the insertion personal code.
     * 
     * @return insertion personal code
     */
    public Integer getInsertPsnCd() {
        return insertPsnCd;
    }

    /**
     * Sets the insertion personal code.
     * 
     * @param insertPsnCd insertion personal code
     */
    public void setInsertPsnCd(Integer insertPsnCd) {
        this.insertPsnCd = insertPsnCd;
    }

    /**
     * Gets the update timestamp.
     * 
     * @return update timestamp
     */
    public Timestamp getUpdateYmd() {
        return updateYmd;
    }

    /**
     * Sets the update timestamp.
     * 
     * @param updateYmd update timestamp
     */
    public void setUpdateYmd(Timestamp updateYmd) {
        this.updateYmd = updateYmd;
    }

    /**
     * Gets the update personal code.
     * 
     * @return update personal code
     */
    public Integer getUpdatePsnCd() {
        return updatePsnCd;
    }

    /**
     * Sets the update personal code.
     * 
     * @param updatePsnCd update personal code
     */
    public void setUpdatePsnCd(Integer updatePsnCd) {
        this.updatePsnCd = updatePsnCd;
    }
}
