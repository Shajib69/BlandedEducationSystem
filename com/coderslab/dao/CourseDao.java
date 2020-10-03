package com.coderslab.dao;

import com.coderslab.entity.Course;
import com.coderslab.entity.Lesson;
import com.coderslab.entity.Topics;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CourseDao extends GenericDao<Course, Serializable> {

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
    
    

    public boolean updateCourseStatus(Course course) throws Exception {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update course set status = :status where courseId = :courseId");
        query.setBoolean("status", course.isStatus());
        query.setInteger("courseId", course.getCourseId());
        query.executeUpdate();
        return true;
    }

    public boolean updteCourse(Course course) throws Exception {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update course set courseCode = :courseCode, courseName = :courseName, description = :description where courseId = :courseId");
        query.setString("courseCode", course.getCourseCode());
        query.setString("courseName", course.getCourseName());
        query.setString("description", course.getDescription());
        query.setInteger("courseId", course.getCourseId());
        query.executeUpdate();
        return true;
    }

    public boolean saveCourseBookFile(Course course) throws Exception {
        String sql = "update course set courseBook = :courseBook where courseId = :courseId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("courseBook", course.getCourseBook());
        query.setInteger("courseId", course.getCourseId());
        query.executeUpdate();
        return true;
    }

    public List<Course> getAllCourseName(){
        String sql = "select * from course";
        return jdbcTemplate.query(sql, new CourseMapper());
    }
    
    public List<Course> getAllCourseWithLessonAndTopics() {
//        String sql = "select courseId, courseName from course";
        String sql = "select distinct c.courseId, c.courseName, c.courseBook, c.introVideo, l.lessonId, l.presentationFile, l.notesFile, l.lessonTitle, t.topicsId, t.topicsTitle, t.videoUrl from course as c, lesson as l, topics as t where c.courseId = l.courseId and l.lessonId = t.lessonId";
        return jdbcTemplate.query(sql, new CourseExtractor());
    }
    
    public List<Course> getCourseWithDetailsByCourseId(int id){
        String sql = "select distinct c.courseId, c.courseName, c.courseBook, c.introVideo, l.lessonId, l.lessonTitle, l.presentationFile, l.notesFile, t.topicsId, t.topicsTitle, t.videoUrl from course as c, lesson as l, topics as t where c.courseId = l.courseId and l.lessonId = t.lessonId and c.courseId = " + id;
        return jdbcTemplate.query(sql, new CourseExtractor());
    }

    public static class CourseExtractor implements ResultSetExtractor<List<Course>> {

        public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, Course> map = new HashMap<Integer, Course>();
            Course course = null;
            Lesson lesson = null;
            while (rs.next()) {
                Integer courseId = rs.getInt("courseId");
                course = map.get(courseId);
                if (course == null) {
                    course = new Course();
                    course.setCourseId(courseId);
                    course.setCourseName(rs.getString("courseName"));
                    course.setCourseBook(rs.getString("courseBook"));
                    course.setIntroVideo(rs.getString("introVideo"));
                    course.setLessons(new HashSet<Lesson>());
                    map.put(courseId, course);
                }
                Integer lessonId = rs.getInt("lessonId");
                if (lessonId > 0) {
                    if (course.getLessons().size() > 0) {
                        boolean stat = true;
                        for (Lesson l : course.getLessons()) {
                            if (l.getLessonId() == lessonId) {
                                stat = false;
                                break;
                            }
                        }
                        if(stat){
                            lesson = new Lesson();
                            lesson.setLessonId(lessonId);
                            lesson.setLessonTitle(rs.getString("lessonTitle"));
                            lesson.setNotesFile(rs.getString("notesFile"));
                            lesson.setPresentationFile(rs.getString("presentationFile"));
                            lesson.setTopicses(new HashSet<Topics>());
                            course.getLessons().add(lesson);
                        }
                    } else {
                        lesson = new Lesson();
                        lesson.setLessonId(lessonId);
                        lesson.setLessonTitle(rs.getString("lessonTitle"));
                        lesson.setNotesFile(rs.getString("notesFile"));
                        lesson.setPresentationFile(rs.getString("presentationFile"));
                        lesson.setTopicses(new HashSet<Topics>());
                        course.getLessons().add(lesson);
                    }
                }
                Integer topicsId = rs.getInt("topicsId");
                if (topicsId > 0) {
                    Topics topics = new Topics();
                    topics.setTopicsId(topicsId);
                    topics.setTopicsTitle(rs.getString("topicsTitle"));
                    topics.setVideoUrl(rs.getString("videoUrl"));
                    lesson.getTopicses().add(topics);
                }

            }
            return new ArrayList<Course>(map.values());
        }
    }

    public List<Course> getAllCourseWithLesson() {
        return sessionFactory.getCurrentSession().getNamedQuery("Course.getAllWithLesson").list();
    }

    public static class CourseMapper implements RowMapper<Course> {

        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course c = new Course();
            c.setCourseId(rs.getInt("courseId"));
            c.setCourseName(rs.getString("courseName"));
            return c;
        }

    }
    
    public boolean updteCourseVideo(Course course) throws Exception{
        String sql = "update course set introVideo = :introVideo where courseId = :courseId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setString("introVideo", course.getIntroVideo());
        query.setInteger("courseId", course.getCourseId());
        query.executeUpdate();
        return true;
    }
    
    public List<Course> getAllCourseInfo(){
        String sql = "select * from course";
        return jdbcTemplate.query(sql, new CourseMapperTwo());
    }

    
    public static class CourseMapperTwo implements RowMapper<Course>{

        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course c = new Course();
            c.setCourseId(rs.getInt("courseId"));
            c.setCourseName(rs.getString("courseName"));
            c.setCourseBook(rs.getString("courseBook"));
            c.setCourseCode(rs.getString("courseCode"));
            c.setCourseDuration(rs.getInt("courseDuration"));
            c.setDescription(rs.getString("description"));
            c.setIntroVideo(rs.getString("introVideo"));
            c.setStatus(rs.getBoolean("status"));
            return c;
        }
        
    }
    
}
