
package com.coderslab.dao;

import com.coderslab.entity.Event;
import java.io.Serializable;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventDao extends GenericDao<Event, Serializable>{

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
    
    
    public boolean saveEventNotesFile(Event event) throws Exception{
        String sql = "update event set notesFile = :notesFile where eventId = :eventId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("notesFile", event.getNotesFile());
        query.setInteger("eventId", event.getEventId());
        query.executeUpdate();
        return true;
    }
    
    
    public boolean saveEventPPTFile(Event event) throws Exception{
        String sql = "update event set presentationFile = :presentationFile where eventId = :eventId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("presentationFile", event.getPresentationFile());
        query.setInteger("eventId", event.getEventId());
        query.executeUpdate();
        return true;
    }
    
    
    public boolean updateEventVideo(Event event) throws Exception{
        String sql = "update event set videoUrl = :videoUrl where eventId = :eventId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("videoUrl", event.getVideoUrl());
        query.setInteger("eventId", event.getEventId());
        query.executeUpdate();
        return true;
    }
    
    public boolean updateEventStatus(Event event) throws Exception{
        String sql = "update event set status = :status where eventId = :eventId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setBoolean("status", event.isStatus());
        query.setInteger("eventId", event.getEventId());
        query.executeUpdate();
        return true;
    }
    
    
}
