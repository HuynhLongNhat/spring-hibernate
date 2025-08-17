package entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class T002Entity {
    private BigDecimal customerId;     // CUSTOMER_ID (NUMERIC 8)
    private String customerName;       // CUSTOMER_NAME (VARCHAR 50)
    private String sex;                // SEX (VARCHAR 1)
    private String birthday;           // BIRTHDAY (VARCHAR 10)
    private String email;              // EMAIL (VARCHAR 40)
    private String address;            // ADDRESS (VARCHAR 256)
    private Timestamp deleteYmd;          // DELETE_YMD (VARCHAR 8)
    private Timestamp insertYmd;       // INSERT_YMD (TIMESTAMP)
    private BigDecimal insertPsnCd;    // INSERT_PSN_CD (NUMERIC 5)
    private Timestamp updateYmd;       // UPDATE_YMD (TIMESTAMP)
    private BigDecimal updatePsnCd;    // UPDATE_PSN_CD (NUMERIC 5)

    // Getters and Setters
    public BigDecimal getCustomerId() {
        return customerId;
    }
    public void setCustomerId(BigDecimal customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Timestamp getDeleteYmd() {
        return deleteYmd;
    }
    public void setDeleteYmd(Timestamp deleteYmd) {
        this.deleteYmd = deleteYmd;
    }
    public Timestamp getInsertYmd() {
        return insertYmd;
    }
    public void setInsertYmd(Timestamp insertYmd) {
        this.insertYmd = insertYmd;
    }
    public BigDecimal getInsertPsnCd() {
        return insertPsnCd;
    }
    public void setInsertPsnCd(BigDecimal insertPsnCd) {
        this.insertPsnCd = insertPsnCd;
    }
    public Timestamp getUpdateYmd() {
        return updateYmd;
    }
    public void setUpdateYmd(Timestamp updateYmd) {
        this.updateYmd = updateYmd;
    }
    public BigDecimal getUpdatePsnCd() {
        return updatePsnCd;
    }
    public void setUpdatePsnCd(BigDecimal updatePsnCd) {
        this.updatePsnCd = updatePsnCd;
    }
}
