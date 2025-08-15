package service;



import dao.T001Dao;
import dto.T001Dto;
import entities.T001Entity;

public class T001Service {

	 private T001Dao t001Dao;
	    public void setT001Dao(T001Dao t001Dao) { this.t001Dao = t001Dao; }

	    public T001Dto getUserLogin(T001Dto inputDto) {
	        T001Entity entity = t001Dao.getUserLogin(inputDto);
	        if (entity == null) return null;

	        T001Dto dto = new T001Dto();
	        dto.setUserId(entity.getUserId());
	        dto.setUserName(entity.getUsername());
	        dto.setPsnCd(entity.getPsnCd());
	        return dto;
	    }
}
