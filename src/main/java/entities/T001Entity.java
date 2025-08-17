package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class T001Entity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal psnCd;         // NUMERIC(4)
    private String userId;            // VARCHAR(8)
    private String password;          // VARCHAR(8)
    private String username;          // VARCHAR(40)
    private Timestamp deleteYmd;      // TIMESTAMP
    private Timestamp insertYmd;      // TIMESTAMP
    private BigDecimal insertPsnCd;   // NUMERIC(5)
    private Timestamp updateYmd;      // TIMESTAMP
    private BigDecimal updatePsnCd;   // NUMERIC(5)

    // Getters và Setters
    public BigDecimal getPsnCd() {
        return psnCd;
    }
    public void setPsnCd(BigDecimal psnCd) {
        this.psnCd = psnCd;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
