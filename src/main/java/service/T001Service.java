package service;

import dao.T001Dao;
import dto.T001Dto;
import entities.T001Entity;

/**
 * Service class for handling user authentication operations.
 * <p>
 * This service acts as a bridge between the Controller (Action)
 * and the Data Access Object (DAO) layer. It contains business logic
 * related to user login and data conversion between entities and DTOs.
 * </p>
 */
public class T001Service {

    private T001Dao t001Dao;

    /**
     * Injects the {@link T001Dao} dependency.
     * This will be configured by Spring.
     *
     * @param t001Dao DAO for user-related operations
     */
    public void setT001Dao(T001Dao t001Dao) {
        this.t001Dao = t001Dao;
    }

    /**
     * Authenticates a user based on provided credentials.
     * <p>
     * This method queries the database via {@link T001Dao} and,
     * if the user exists and credentials match, maps the result
     * from {@link T001Entity} to {@link T001Dto}.
     * </p>
     *
     * @param inputDto a DTO containing login credentials (userId and password)
     * @return a populated {@link T001Dto} if login is successful,
     *         otherwise {@code null}
     */
    public T001Dto getUserLogin(T001Dto inputDto) {
        T001Entity entity = t001Dao.getUserLogin(inputDto);
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
