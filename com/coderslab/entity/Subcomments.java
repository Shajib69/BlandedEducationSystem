package com.coderslab.entity;
// Generated Oct 28, 2017 11:00:09 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Subcomments generated by hbm2java
 */
@Entity
@Table(name = "subcomments",
         catalog = "bes"
)
public class Subcomments implements java.io.Serializable {

    private Integer subCommentsId;
    private int commentsId;
    private String subComments;
    private String username;

    public Subcomments() {
    }

    public Subcomments(int commentsId, String subComments, String username) {
        this.commentsId = commentsId;
        this.subComments = subComments;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "subCommentsId", unique = true, nullable = false)
    public Integer getSubCommentsId() {
        return this.subCommentsId;
    }

    public void setSubCommentsId(Integer subCommentsId) {
        this.subCommentsId = subCommentsId;
    }

    @Column(name = "commentsId", nullable = false)
    public int getCommentsId() {
        return this.commentsId;
    }

    public void setCommentsId(int commentsId) {
        this.commentsId = commentsId;
    }

    @Column(name = "subComments", nullable = false, length = 65535)
    public String getSubComments() {
        return this.subComments;
    }

    public void setSubComments(String subComments) {
        this.subComments = subComments;
    }

    @Column(name = "username", nullable = false, length = 60)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Subcomments{" + "subCommentsId=" + subCommentsId + ", commentsId=" + commentsId + ", subComments=" + subComments + ", username=" + username + '}';
    }

}
