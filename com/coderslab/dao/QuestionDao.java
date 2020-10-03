
package com.coderslab.dao;

import com.coderslab.entity.Question;
import java.io.Serializable;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDao extends GenericDao<Question, Serializable>{
    
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
    
    
    public List<Question> getAllQuestionByLessonId(int id){
        String hql = "from Question q where q.lessonId = :lessonId and q.status = :status";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter("lessonId", id).setParameter("status", true).list();
    }
    
    
}
