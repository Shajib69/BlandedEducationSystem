
package com.coderslab.dao;

import com.coderslab.entity.LessonExamResult;
import java.io.Serializable;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LessonExamResultDao extends GenericDao<LessonExamResult, Serializable>{
    @Autowired
    private SessionFactory sessionFactory;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    
    public List<LessonExamResult> getAllLessonExamResultByUsername(String username){
        String hql = "from LessonExamResult l where l.username = :username";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter("username", username).list();
    }
    
    
}
