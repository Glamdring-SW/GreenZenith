package com.example.greenzenith;

public class Plant {

    private String name;
    private String description;
    private int hour;
    private int minutes;
    private String planting;
    private String days;
    private String user;

    public Plant(String name, String description, int hour, int minutes, String planting, String days, String user) {
        this.name = name;
        this.description = description;
        this.hour = hour;
        this.minutes = minutes;
        this.planting = planting;
        this.days = days;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getPlanting() {
        return planting;
    }

    public void setPlanting(String planting) {
        this.planting = planting;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
