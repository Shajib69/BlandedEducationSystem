package com.coderslab.entity;
// Generated Oct 9, 2017 12:23:06 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OfferSubject generated by hbm2java
 */
@Entity
@Table(name = "offer_subject",
         catalog = "bes"
)
public class OfferSubject implements java.io.Serializable {

    private Integer osId;
    private int courseId;
    private int batchId;
    private int teacherId;
    private Date startDate;
    private Date endDate;
    private int hours;

    public OfferSubject() {
    }

    public OfferSubject(int courseId, int batchId, int teacherId, Date startDate, Date endDate, int hours) {
        this.courseId = courseId;
        this.batchId = batchId;
        this.teacherId = teacherId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hours = hours;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "osId", unique = true, nullable = false)
    public Integer getOsId() {
        return this.osId;
    }

    public void setOsId(Integer osId) {
        this.osId = osId;
    }

    @Column(name = "courseId", nullable = false)
    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Column(name = "batchId", nullable = false)
    public int getBatchId() {
        return this.batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    @Column(name = "teacherId", nullable = false)
    public int getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate", nullable = false, length = 10)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "endDate", nullable = false, length = 10)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "hours", nullable = false)
    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

}