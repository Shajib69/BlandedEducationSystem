
package com.coderslab.dao;

import com.coderslab.entity.Comments;
import com.coderslab.entity.Subcomments;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDao extends GenericDao<Comments, Serializable>{
    
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
    
    public List<Comments> getAllCommentsByTopicsId(int id){
        String sql = "select * from comments where topicsId = " + id;
        return jdbcTemplate.query(sql, new CommetnsMapper());
    }
    
  
    
    
    
    public static class CommetnsMapper implements RowMapper<Comments>{

        public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comments c = new Comments();
            c.setCommentsId(rs.getInt("commentsId"));
            c.setUsername(rs.getString("username"));
            c.setComment(rs.getString("comment"));
            return c;
        }
        
    }
    
    
    
    
    
}
