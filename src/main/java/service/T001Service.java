package service;

import dao.T001Dao;
import dto.T001Dto;
import entities.T001Entity;
import form.T001Form;

/**
 * Service for user login handling.
 */
public class T001Service {

    /**
     * DAO for user operations.
     */
    private T001Dao t001Dao;

    /**
     * Sets the user DAO (injected by Spring).
     *
     * @param t001Dao user DAO
     */
    public void setT001Dao(T001Dao t001Dao) {
        this.t001Dao = t001Dao;
    }

    /**
     * Gets user info for login.
     *
     * @param loginForm login form
     * @return user DTO if found, otherwise null
     */
    public T001Dto getUserLogin(T001Form loginForm) {
        T001Entity entity = t001Dao.getUserLogin(loginForm);
        if (entity == null) {
            return null;
        }

        T001Dto resultDto = new T001Dto();
        resultDto.setUserId(entity.getUserId());
        resultDto.setUserName(entity.getUsername());
        resultDto.setPsnCd(entity.getPsnCd());

        return resultDto;
    }
}
