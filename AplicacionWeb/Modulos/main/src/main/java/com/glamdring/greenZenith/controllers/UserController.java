package com.glamdring.greenZenith.controllers;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.userInteractions.users.User;

public class UserController {

    private User user;

    public UserController(){}

    public boolean createUser(String username, String email, String password, int age){
        boolean flag = true;
        try {
            this.user = new User(username, email, password, age);
        } catch (InvalidUserException | GZDBResultException e) {
            e.printStackTrace();
            return flag = false;
        }
        return flag;
    }
    
    public boolean summonUser(String email, String password){
        boolean flag = true;
        try {
            this.user = new User(email, password);
        } catch (InvalidUserException | GZDBResultException e) {
            e.printStackTrace();
            return flag = false;
        }
        return flag;
    }


    public int getUserId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getName();
    }

    public String getUserEmail() {
        return user.getEmail();
    }

    public int getUserAge() {
        return user.getAge();
    }

    public void setUserName(String name) {
        this.user.setName(name);
    }

    public void setUserEmail(String email) {
        this.user.setEmail(email);
    }

    public void setUserAge(int age) {
        this.user.setAge(age);
    }

}
