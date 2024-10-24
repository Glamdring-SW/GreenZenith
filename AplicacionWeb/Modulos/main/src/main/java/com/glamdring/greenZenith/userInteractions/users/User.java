package com.glamdring.greenZenith.userInteractions.users;

import java.awt.image.BufferedImage;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.externals.locations.Location;
import com.glamdring.greenZenith.handlers.UserConnector;
import com.glamdring.greenZenith.userInteractions.Attributable;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.userInteractions.plants.PlantList;
import com.glamdring.greenZenith.userInteractions.products.Cart;
import com.glamdring.greenZenith.userInteractions.products.ProductList;

public class User extends UserConnector implements Attributable, Killable {

    private int id;
    private String username;
    private String email;
    private int age;
    private BufferedImage profilePicture;
    private Location location;
    private String password;
    private boolean administratorAccess;
    private PlantList plants;
    private ProductList products;
    private Cart cart;

    public User(int id) throws InvalidUserException, GZDBResultException {
        super();
        insertMap.put("ID", id);
        resultMap = gzdbc.makeAction(GZDBActions.SELECT, GZDBTables.USER, insertMap, restrictionMap);
        this.id = id;
        this.username = (String) resultMap.get("PUsername");
        this.email = (String) resultMap.get("Email");
        this.age = (int) resultMap.get("Age");
        this.password = (String) resultMap.get("PasswordUser");
        this.administratorAccess = (boolean) resultMap.get("AdministratorAccess");

        //this.profilePicture = ;
        //this.location = ;
        //this.plants = ;
        //this.products = ;
        //this.cart = ;
    }

    public User(String email, String password) throws InvalidUserException, GZDBResultException {
        super();
        insertMap.put("Email", email);
        insertMap.put("PasswordUser", password);
        resultMap = gzdbc.makeAction(GZDBActions.SELECT, GZDBTables.USER, insertMap, restrictionMap);
        this.email = email;
        this.password = password;
        this.id = (int) resultMap.get("ID");
        this.username = (String) resultMap.get("PUsername");
        this.age = (int) resultMap.get("Age");
        this.administratorAccess = (boolean) resultMap.get("AdministratorAccess");

        //this.profilePicture = ;
        //this.location = ;
        //this.plants = ;
        //this.products = ;
        //this.cart = ;
    }

    public User(int age, String email, /*Location location,*/ String password, /*BufferedImage profilePicture,*/ String username) throws InvalidUserException, GZDBResultException {
        super();
        insertMap.put("PUsername", username);
        insertMap.put("Email", email);
        insertMap.put("Age", age);
        insertMap.put("PasswordUser", password);
        gzdbc.makeAction(GZDBActions.INSERT, GZDBTables.USER, insertMap, restrictionMap);
        resultMap = gzdbc.makeAction(GZDBActions.SELECT, GZDBTables.USER, insertMap, restrictionMap);
        this.id = (int) resultMap.get("ID");
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
        this.administratorAccess = false;

        //this.profilePicture = ;
        //this.location = ;
        //this.plants = ;
        //this.products = ;
        //this.cart = ;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public BufferedImage getPicture() {
        return profilePicture;
    }

    public Location getLocation() {
        return location;
    }

    public PlantList getPlants() {
        return plants;
    }

    public ProductList getProducts() {
        return products;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public void setName(String name) {
        this.username = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setPicture(BufferedImage picture) {
        this.profilePicture = picture;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPassword(String oldPassword, String newPassword) {
        this.password = newPassword;
    }

    @Override
    public void close() {
    }

    @Override
    public void delete() {
    }
}
