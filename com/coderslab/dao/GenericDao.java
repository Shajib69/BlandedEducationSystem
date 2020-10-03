package com.coderslab.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class GenericDao<T, ID extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;
    private T obj;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    //crud
    //get all data
    public List<T> getAllData(Class<T> c) {
        return (List<T>) sessionFactory.getCurrentSession().createCriteria(c).list();
    }
    
    //get data by id
    public T findData(Class<T> c, ID id) throws Exception {
        return (T) sessionFactory.getCurrentSession().get(c, id);
    }

    //save object data
    public boolean saveData(T obj) throws Exception {
        sessionFactory.getCurrentSession().save(obj);
        return true;
    }

    //update object data
    public boolean updateData(T obj) throws Exception {
        sessionFactory.getCurrentSession().update(obj);
        return true;
    }

    //delete objact data
    public boolean deleteData(T obj) throws Exception {
        sessionFactory.getCurrentSession().delete(obj);
        return true;
    }

    //merge object data
    public boolean merge(T obj) throws Exception {
        sessionFactory.getCurrentSession().merge(obj);
        return true;
    }

    //save or update object data
    public boolean saveOrUpdate(T obj) throws Exception {
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
        return true;
    }

}
