package dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dto.T001Dto;
import entities.T001Entity;

public class T001Dao extends HibernateDaoSupport {

    public T001Entity getUserLogin(T001Dto inputDto) {
        if (inputDto == null) return null;

        // HQL dựa trên entity + field name
        String hql = "FROM T001Entity u "
                   + "WHERE u.deleteYmd IS NULL "
                   + "AND u.userId = :userId "
                   + "AND u.password = :password";

        return (T001Entity) getHibernateTemplate().execute(session ->
            session.createQuery(hql)
                   .setString("userId", inputDto.getUserId())
                   .setString("password", inputDto.getPassword())
                   .uniqueResult()
        );
    }
}
