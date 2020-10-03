package com.coderslab.dao;

import com.coderslab.entity.Ratings;
import java.io.Serializable;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RatingsDao extends GenericDao<Ratings, Serializable> {

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

    public List<Ratings> findRatingsByBlogPostId(int postId) throws Exception{
        String hql = "FROM Ratings r where r.postId = '" + postId + "'";
        return sessionFactory.openSession().createQuery(hql).list();
    }

}
