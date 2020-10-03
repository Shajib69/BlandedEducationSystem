package com.coderslab.entity;
// Generated Oct 22, 2017 6:39:32 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Authorities generated by hbm2java
 */
@Entity
@Table(name = "authorities",
        catalog = "bes"
)
public class Authorities implements java.io.Serializable {

    private String username;
    private String authority;

    public Authorities() {
    }

    public Authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Id

    @Column(name = "username", unique = true, nullable = false, length = 60)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "authority", nullable = false, length = 40)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authorities{" + "username=" + username + ", authority=" + authority + '}';
    }

}