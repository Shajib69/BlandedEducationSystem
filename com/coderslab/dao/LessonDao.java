package com.coderslab.dao;

import com.coderslab.entity.Lesson;
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
public class LessonDao extends GenericDao<Lesson, Serializable> {

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
    
    public Lesson getLessonByLessonId(int id){
        String sql = "select * from lesson where lessonId = " + id;
        return jdbcTemplate.queryForObject(sql, new LessonMapper());
    }

    public boolean saveLessonPPTFile(Lesson lesson) throws Exception {
        String sql = "update lesson set presentationFile = :presentationFile where lessonId = :lessonId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("presentationFile", lesson.getPresentationFile());
        query.setInteger("lessonId", lesson.getLessonId());
        query.executeUpdate();
        return true;
    }

    public boolean saveLessonNotesFile(Lesson lesson) throws Exception {
        String sql = "update lesson set notesFile = :notesFile where lessonId = :lessonId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("notesFile", lesson.getNotesFile());
        query.setInteger("lessonId", lesson.getLessonId());
        query.executeUpdate();
        return true;
    }

    public List<Lesson> getLessonByCourseId(int id) throws Exception {
        String sql = "from Lesson where courseId = " + id;
        return sessionFactory.getCurrentSession().createQuery(sql).list();
    }

    public List<Lesson> getLessonByCourseIdByJDBC(int id) throws Exception {
        String sql = "select lessonId, lessonTitle from lesson where courseId = " + id;
        return jdbcTemplate.query(sql, new LessonMapper());
    }

    public List<Lesson> getAllLessonName() {
        String sql = "select lessonId, lessonTitle from lesson";
        return jdbcTemplate.query(sql, new LessonMapper());
    }

    public static class LessonMapper implements RowMapper<Lesson> {

        public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
            Lesson l = new Lesson();
            l.setLessonId(rs.getInt("lessonId"));
            l.setLessonTitle(rs.getString("lessonTitle"));
            return l;
        }

    }

    public boolean updateLessonStatus(Lesson lesson) throws Exception {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update lesson set status = :status where lessonId = :lessonId");
        query.setBoolean("status", lesson.isStatus());
        query.setInteger("lessonId", lesson.getLessonId());
        query.executeUpdate();
        return true;
    }

    public boolean updateLesson(Lesson l) throws Exception {
        String sql = "update lesson set courseId = :courseId, lessonTitle = :lessonTitle, description = :description where lessonId = :lessonId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setInteger("courseId", l.getCourse().getCourseId());
        query.setString("lessonTitle", l.getLessonTitle());
        query.setString("description", l.getDescription());
        query.setInteger("lessonId", l.getLessonId());
        query.executeUpdate();
        return true;
    }

    public List<Lesson> getAllLessonWithCourse() {
        return sessionFactory.getCurrentSession().getNamedQuery("Lesson.findAllDataWithCourse").list();
    }

    public Lesson findLessonWithCourseByLessonId(int id) {
        return (Lesson) sessionFactory.getCurrentSession().getNamedQuery("Lesson.findLessonWithCourseByLessonId").setParameter("lessonId", id).uniqueResult();
    }

}
