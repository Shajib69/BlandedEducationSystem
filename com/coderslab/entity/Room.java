package com.coderslab.entity;
// Generated Oct 9, 2017 12:23:06 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Room generated by hbm2java
 */
@Entity
@Table(name = "room",
         catalog = "bes"
)
public class Room implements java.io.Serializable {

    private Integer roomId;
    private String roomType;
    private String description;
    private Integer seatCapacity;

    public Room() {
    }

    public Room(String roomType) {
        this.roomType = roomType;
    }

    public Room(String roomType, String description, Integer seatCapacity) {
        this.roomType = roomType;
        this.description = description;
        this.seatCapacity = seatCapacity;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "roomId", unique = true, nullable = false)
    public Integer getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Column(name = "roomType", nullable = false)
    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Column(name = "description", length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "seatCapacity")
    public Integer getSeatCapacity() {
        return this.seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

}
