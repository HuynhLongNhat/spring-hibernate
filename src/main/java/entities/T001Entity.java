package entities;

import java.util.Date;

public class T001Entity {
	private Integer psnCd;
	private String userId;
	private String password;
	private String username;
	private Date deleteYmd;
	private Date insertYmd;
	private Integer insertPsnCd;
	private Date updateYmd;
	private Integer updatePsnCd;

	// Getters và Setters
	public Integer getPsnCd() {
		return psnCd;
	}

	public void setPsnCd(Integer psnCd) {
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

	public Date getDeleteYmd() {
		return deleteYmd;
	}

	public void setDeleteYmd(Date deleteYmd) {
		this.deleteYmd = deleteYmd;
	}

	public Date getInsertYmd() {
		return insertYmd;
	}

	public void setInsertYmd(Date insertYmd) {
		this.insertYmd = insertYmd;
	}

	public Integer getInsertPsnCd() {
		return insertPsnCd;
	}

	public void setInsertPsnCd(Integer insertPsnCd) {
		this.insertPsnCd = insertPsnCd;
	}

	public Date getUpdateYmd() {
		return updateYmd;
	}

	public void setUpdateYmd(Date updateYmd) {
		this.updateYmd = updateYmd;
	}

	public Integer getUpdatePsnCd() {
		return updatePsnCd;
	}

	public void setUpdatePsnCd(Integer updatePsnCd) {
		this.updatePsnCd = updatePsnCd;
	}
}
