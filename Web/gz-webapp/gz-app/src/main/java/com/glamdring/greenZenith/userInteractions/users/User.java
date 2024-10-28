package com.glamdring.greenZenith.userInteractions.users;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.application.user.constants.UserExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.externals.locations.Location;
import com.glamdring.greenZenith.handlers.GZDBSuperManager;
import com.glamdring.greenZenith.handlers.GZFormatter;
import com.glamdring.greenZenith.userInteractions.Attributable;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.handlers.PlantList;
import com.glamdring.greenZenith.userInteractions.products.Cart;
import com.glamdring.greenZenith.handlers.ProductList;
import com.glamdring.greenZenith.handlers.constants.GZFormats;

public class User extends GZDBSuperManager implements Attributable, Killable {

    private final int id;
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
        super(GZDBTables.USER);
        resetMaps();
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
        super(GZDBTables.USER);
        if (!GZFormatter.isValid(email, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
        }
        if (!GZFormatter.isValid(password, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
        }
        resetMaps();
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

    public User(String username, String email, String password, int age
    /*Location location, BufferedImage profilePicture,*/) throws InvalidUserException, GZDBResultException {
        super(GZDBTables.USER);
        if (!GZFormatter.isValid(username, GZFormats.USERNAME)) {
            throw new InvalidUserException(UserExceptions.FORMAT_USERNAME);
        }
        if (!GZFormatter.isValid(email, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_EMAIL);
        }
        if (!GZFormatter.isValid(password, GZFormats.PASSWORD)) {
            throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
        }
        resetMaps();
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

        //this.profilePicture = ; picture handler needed
        //this.location = ; location class needed
        //this.plants = ; plantlist class needed
        //this.products = ; productlist class needed
        //this.cart = ; cart class needed
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

    public boolean isAdministratorAccess() {
        return administratorAccess;
    }

    public void setName(String name) throws InvalidUserException, GZDBResultException {
        if (!GZFormatter.isValid(name, GZFormats.USERNAME)) {
            throw new InvalidUserException(UserExceptions.FORMAT_USERNAME);
        }
        this.username = name;
        resetMaps();
        insertMap.put("PUsername", name);
        restrictionMap.put("ID", id);
        gzdbc.makeAction(GZDBActions.UPDATE, GZDBTables.USER, insertMap, restrictionMap);

    }

    public void setEmail(String email) throws InvalidUserException, GZDBResultException {
        if (!GZFormatter.isValid(email, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_EMAIL);
        }
        this.email = email;
        resetMaps();
        insertMap.put("Email", email);
        restrictionMap.put("ID", id);
        gzdbc.makeAction(GZDBActions.UPDATE, GZDBTables.USER, insertMap, restrictionMap);
    }

    public void setAge(int age) throws InvalidUserException, GZDBResultException {
        if (age > 12 && age < 120) {
            this.age = age;
            resetMaps();
            insertMap.put("Age", age);
            restrictionMap.put("ID", id);
            gzdbc.makeAction(GZDBActions.UPDATE, GZDBTables.USER, insertMap, restrictionMap);
        } else {
            throw new InvalidUserException(UserExceptions.AGE);
        }
    }

    public void setPicture(BufferedImage picture) throws InvalidUserException, GZDBResultException {
        this.profilePicture = picture;
        /*
        picture handler needed
            resetMaps();
            insertMap.put("Picture", HANDLER_HERE);
            restrictionMap.put("ID", id);
            gzdbc.makeAction(GZDBActions.UPDATE, GZDBTables.USERPICTURE, insertMap, restrictionMap);
         */
    }

    public void setLocation(Location location) throws InvalidUserException, GZDBResultException {
        this.location = location;
        /*
        location class needed
            resetMaps();
            insertMap.put("Picture", HANDLER_HERE);
            restrictionMap.put("ID", id);
            gzdbc.makeAction(GZDBActions.UPDATE, GZDBTables.USERPICTURE, insertMap, restrictionMap);
         */
    }

    public void setPassword(String oldPassword, String newPassword) throws GZDBResultException, InvalidUserException {
        if (!GZFormatter.isValid(newPassword, GZFormats.PASSWORD)) {
            throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
        }
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
            resetMaps();
            insertMap.put("PasswordUser", newPassword);
            restrictionMap.put("ID", id);
            gzdbc.makeAction(GZDBActions.UPDATE, GZDBTables.USER, insertMap, restrictionMap);
        } else {
            throw new InvalidUserException(UserExceptions.PASSWORD);
        }
    }

    public void setAdministratorAccess(boolean administratorAccess) {
        this.administratorAccess = administratorAccess;
    }

    @Override
    public void close() {
    }

    @Override
    public void delete() {
    }

    private void resetMaps() {
        insertMap = new LinkedHashMap<>();
        restrictionMap = new LinkedHashMap<>();
    }
}
