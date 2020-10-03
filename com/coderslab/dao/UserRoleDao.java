
package com.coderslab.dao;

import com.coderslab.entity.UserRole;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRoleDao extends GenericDao<UserRole, Serializable>{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean saveData(UserRole obj) throws Exception {
        return super.saveData(obj); 
    }
    
    public List<UserRole> getAllUserRole() {
        return sessionFactory.getCurrentSession().createQuery("from UserRole").list();
    }
    
    public boolean deleteRoleById(int id)  throws Exception {
        String sql = "delete from user_role where roleId = :roleId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setInteger("roleId", id);
        query.executeUpdate();
        return true;
    }
    
}
