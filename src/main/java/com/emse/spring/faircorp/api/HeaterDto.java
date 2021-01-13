package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.WindowStatus;

/**
 * The DTO of a Heater
 */
public class HeaterDto {
    private Long id;
    private String name;
    private HeaterStatus heaterStatus;
    private String roomName;
    private Long roomId;
    private Long power;

    public HeaterDto() {
    }

    public HeaterDto(Heater heater){
        this.id = heater.getId();
        this.name = heater.getName();
        this.heaterStatus = heater.getHeaterStatus();
        this.roomId = heater.getRoom().getId();
        this.roomName = heater.getRoom().getName();
        this.power = heater.getPower();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getPower() {
        return power;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setPower(Long power) {
        this.power = power;
    }
}
