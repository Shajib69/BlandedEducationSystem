
package com.coderslab.dao;

import com.coderslab.entity.Student;
import java.io.Serializable;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentDao extends GenericDao<Student, Serializable>{
    
    @Autowired
    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Student getStudentByUserName(String username) throws Exception{
        String sql = "from Student s where s.username = :username";
        return (Student) sessionFactory.getCurrentSession().createQuery(sql).setParameter("username", username).uniqueResult();
    }
    
    public boolean deleteStudentByUsername(String username) throws Exception{
        String sql = "delete from student where username = :username";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("username", username);
        query.executeUpdate();
        return true;
    }
    
}
