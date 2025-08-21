package entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Entity class representing the T001 table.
 * Stores user information such as personal code, userId, password,
 * username, and audit fields for insert, update, and delete.
 * 
 * @author You
 * @since 2025
 */
public class T001Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Personal code (NUMERIC(4)) */
    private Integer psnCd;

    /** User ID (VARCHAR(8)) */
    private String userId;

    /** User password (VARCHAR(8)) */
    private String password;

    /** Username (VARCHAR(40)) */
    private String username;

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
     * Gets the personal code.
     * 
     * @return personal code
     */
    public Integer getPsnCd() {
        return psnCd;
    }

    /**
     * Sets the personal code.
     * 
     * @param psnCd personal code
     */
    public void setPsnCd(Integer psnCd) {
        this.psnCd = psnCd;
    }

    /**
     * Gets the user ID.
     * 
     * @return user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * 
     * @param userId user ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the password.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the username.
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
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
