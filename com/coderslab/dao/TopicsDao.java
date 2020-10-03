package com.coderslab.dao;

import com.coderslab.entity.Topics;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TopicsDao extends GenericDao<Topics, Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Topics> getAllwithLesson() {
        return sessionFactory.getCurrentSession().getNamedQuery("Topics.getAllwithLesson").list();
    }

    public Topics getAllWithLessonByTpicsId(int id) {
        return (Topics) sessionFactory.getCurrentSession().getNamedQuery("Topics.getAllWithLessonByTpicsId").setParameter("topicsId", id).uniqueResult();
    }

    public boolean updateTopics(Topics topics) throws Exception {
        String sql = "update topics set topicsTitle = :topicsTitle, description = :description, duration = :duration where topicsId = :topicsId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("topicsTitle", topics.getTopicsTitle());
        query.setString("description", topics.getDescription());
        query.setInteger("duration", topics.getDuration());
        query.setInteger("topicsId", topics.getTopicsId());
        query.executeUpdate();
        return true;
    }

    public boolean updateTopicsStatus(Topics topics) throws Exception {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update topics set status = :status where topicsId = :topicsId");
        query.setBoolean("status", topics.getStatus());
        query.setInteger("topicsId", topics.getTopicsId());
        query.executeUpdate();
        return true;
    }

    public boolean deleteTopicsById(int id) throws Exception {
        String sql = "delete from topics where topicsId = :topicsId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setInteger("topicsId", id);
        query.executeUpdate();
        return true;
    }
    
    public boolean updateTopicsVideoUrl(Topics topics) throws Exception{
        String sql = "update topics set videoUrl = :videoUrl where topicsId = :topicsId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("videoUrl", topics.getVideoUrl());
        query.setInteger("topicsId", topics.getTopicsId());
        query.executeUpdate();
        return true;
    }

}
