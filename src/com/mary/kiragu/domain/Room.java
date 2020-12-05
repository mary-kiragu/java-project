/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mary.kiragu.domain;

/**
 *
 * @author Lenovo
 */
public class Room {

    private int roomId;
    private String roomName;
    
    private int rent;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    

 

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", roomName=" + roomName + ", rent=" + rent + '}';
    }

}
