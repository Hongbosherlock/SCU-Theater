package com.web.entity;

/**
 * Created by Hongb on 2018/11/22.
    电影放映厅
 */

public class Room {
    private Long id;
    private Integer roomName; //厅的编号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomName() {
        return roomName;
    }

    public void setRoomName(Integer roomName) {
        this.roomName = roomName;
    }
}
