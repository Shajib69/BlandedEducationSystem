package com.coderslab.dao;

import com.coderslab.entity.Authorities;
import com.coderslab.entity.Student;
import com.coderslab.entity.Teacher;
import com.coderslab.entity.Users;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsersDao extends GenericDao<Users, Serializable> {

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
    
    

    public List<Users> getAllData(Users users) {
        return sessionFactory.getCurrentSession().createQuery("from Users").list();
    }

    public boolean saveUserWithAuthority(Users users, Authorities authorities) throws Exception {
        sessionFactory.getCurrentSession().save(users);
        sessionFactory.getCurrentSession().save(authorities);
        if(users.getUserType().equals("ROLE_STUDENT")){
            Student student  = new Student();
            student.setUsername(users.getUsername());
            sessionFactory.getCurrentSession().save(student);
        }else if(users.getUserType().equals("ROLE_TEACHER")){
            Teacher teacher = new Teacher();
            teacher.setUsername(users.getUsername());
            sessionFactory.getCurrentSession().save(teacher);
        }
        return true;
    }

    public boolean updateUserStatus(Users users) throws Exception {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update users set enabled = :enabled where userId = :userId");
        query.setBoolean("enabled", users.isEnabled());
        query.setInteger("userId", users.getUserId());
        query.executeUpdate();
        return true;
    }

    public Users findData(int id) throws Exception {
        return (Users) sessionFactory.getCurrentSession().get(Users.class, id);
    }

    @Override
    public boolean deleteData(Users obj) throws Exception {
        return super.deleteData(obj); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteUserById(int id) throws Exception {
        String sql = "delete from users where userId = :userId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setInteger("userId", id);
        query.executeUpdate();
        return true;
    }

    public boolean updateData(Users obj, Authorities authorities) throws Exception {
        String hql = "update users set userName = :userName, authority = :authority where userId = :userId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setString("userName", obj.getUsername());
        query.setString("authority", obj.getAuthority());
        query.setInteger("userId", obj.getUserId());
        query.executeUpdate();
        sessionFactory.getCurrentSession().update(authorities);
        return true;
    }

    @Override
    public boolean saveData(Users obj) throws Exception {
        return super.saveData(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SessionFactory getSessionFactory() {
        return super.getSessionFactory(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Users getUserByUserName(String username){
//        String sql = "select * from users where username = '"+username+"' ";
//        return jdbcTemplate.queryForObject(sql, new UsersMapper());
        return  (Users) sessionFactory.getCurrentSession().createQuery("from Users u where u.username = :username").setParameter("username", username).uniqueResult();
    }
    
    public static class UsersMapper implements RowMapper<Users>{

        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
            Users u = new Users();
            u.setUserId(rs.getInt("userId"));
            u.setAuthority(rs.getString("authority"));
            u.setDob(rs.getDate("dob"));
            u.setEmail(rs.getString("email"));
            u.setEnabled(rs.getBoolean("enabled"));
            u.setFirstName(rs.getString("firstName"));
            u.setGender(rs.getBoolean("gender"));
            u.setLastName(rs.getString("lastName"));
            u.setMobile(rs.getString("mobile"));
            u.setPassword(rs.getString("password"));
            u.setPhoto(rs.getString("photo"));
            u.setRegisterDate(rs.getDate("registerDate"));
            u.setUserType(rs.getString("userType"));
            u.setUsername(rs.getString("username"));
            return u;
        }
        
    }
    
    public List<Users> getAllStudentUsers(){
        String sql = "from Users u where u.userType = 'ROLE_STUDENT'";
        return sessionFactory.getCurrentSession().createQuery(sql).list();
    }
    
    public List<Users> getAllTeacherUsers(){
        String sql = "from Users u where u.userType = 'ROLE_TEACHER'";
        return sessionFactory.getCurrentSession().createQuery(sql).list();
    }

}
