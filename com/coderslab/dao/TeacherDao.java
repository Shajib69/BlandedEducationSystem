
package com.coderslab.dao;

import com.coderslab.entity.Teacher;
import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TeacherDao extends GenericDao<Teacher, Serializable>{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public Teacher getTeacherByUsername(String username){
        String sql = "from Teacher t where t.username = :username";
        return (Teacher) sessionFactory.getCurrentSession().createQuery(sql).setParameter("username", username).uniqueResult();
    }
    
    public boolean deleteTeacherByUsername(String username) throws Exception{
        String sql = "delete from teacher where username = :username";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("username", username);
        query.executeUpdate();
        return true;
    }
    
    
}
