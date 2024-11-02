package com.glamdring.greenZenith.userInteractions.users;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.application.user.constants.UserExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBSuperManager;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.handlers.constants.GZFormats;
import com.glamdring.greenZenith.handlers.formats.GZFormatter;
import com.glamdring.greenZenith.handlers.locations.Location;
import com.glamdring.greenZenith.userInteractions.Attributable;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.userInteractions.plants.PlantList;
import com.glamdring.greenZenith.userInteractions.products.Cart;
import com.glamdring.greenZenith.userInteractions.products.ProductList;

/**
 * Represents a User of the application, with it's respective attributes,
 * allowing the creation, modification and deletion of it, as well as being the
 * central piece of interaction of the entire application, allowing for access
 * and control od the plants, products and cart.
 *
 * @see GZDBSuperManager
 * @author Glamdring (Σxz)
 * @version 1.4.0
 * @since 0.1
 */
public class User extends GZDBSuperManager implements Attributable, Killable {

    /**
     * The unique identificator for each database registry.
     */
    private final int id;
    /**
     * A unique name for easier indetification of users.
     */
    private String username;
    /**
     * A unique email for accessing the application.
     */
    private String email;
    /**
     * The age that a user posseses.
     */
    private int age;
    /**
     * The image that will display when viewing someone's profile for a better
     * depiction of that person.
     */
    private BufferedImage profilePicture;
    /**
     * The location that a User has in real life.
     */
    private Location location;
    /**
     * The password used for accessing an account.
     */
    private String password;
    /**
     * Determines the access to administrator features.
     */
    private boolean administratorAccess;
    /**
     * A list containing all the plants owned by a user.
     */
    private PlantList plants;
    /**
     * A list containing all the product on sale by a user.
     */
    private ProductList products;
    /**
     * A list containing all the products that a User wants to buy.
     */
    private Cart cart;

    /**
     * Retrieves the information from the database utilizing only the ID, and
     * assigns the results to each correspondant field.
     *
     * @param id The id that corresponds to a certain User registry on the
     * database.
     * @throws InvalidUserException If the ID is not valid.
     * @throws GZDBResultException If the database connection cannot be
     * resolved.
     */
    public User(int id) throws InvalidUserException, GZDBResultException {
        super(GZDBTables.USER);
        resetMaps();
        insertMap.put("ID", id);
        resultMap = doGZDBCAction(GZDBActions.SELECT);
        this.id = id;
        this.username = (String) resultMap.get("PUsername");
        this.email = (String) resultMap.get("Email");
        this.age = (int) resultMap.get("Age");
        this.password = (String) resultMap.get("PasswordUser");
        this.administratorAccess = (boolean) resultMap.get("AdministratorAccess");

        //this.profilePicture = ; picture handler needed
        //this.location = ; location class needed
        //this.plants = ; plantlist class needed
        //this.products = ; productlist class needed
        //this.cart = ; cart class needed
    }

    /**
     * Retrieves the information from the database utilizing the correspondant
     * email and password of a User, and assigns the results to each
     * correspondant field.
     *
     * @param email The unique email that corresponds to the account.
     * @param password The password taht is used to access the account.
     * @throws InvalidUserException If the email or passwords are not valid.
     * @throws GZDBResultException If the database connection cannot be
     * resolved.
     */
    public User(String email, String password) throws InvalidUserException, GZDBResultException {
        super(GZDBTables.USER);
        if (!GZFormatter.isValid(email, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_EMAIL);
        }
        if (!GZFormatter.isValid(password, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
        }
        resetMaps();
        insertMap.put("Email", email);
        insertMap.put("PasswordUser", password);
        resultMap = doGZDBCAction(GZDBActions.SELECT);
        this.email = email;
        this.password = password;
        this.id = (int) resultMap.get("ID");
        this.username = (String) resultMap.get("PUsername");
        this.age = (int) resultMap.get("Age");
        this.administratorAccess = (boolean) resultMap.get("AdministratorAccess");

        //this.profilePicture = ; picture handler needed
        //this.location = ; location class needed
        //this.plants = ; plantlist class needed
        //this.products = ; productlist class needed
        //this.cart = ; cart class needed
    }

    /**
     * Creates a new user with the determined input parameters, it has to follow
     * the established format.
     *
     * @param username The desired unique name of a user.
     * @param email The selected unique email associated with the account.
     * @param password The password requiered to access the account.
     * @param age The age in real life of the owner of the account.
     * @throws InvalidUserException If the data provided does not meet the
     * format requirements.
     * @throws GZDBResultException If the database connection cannot be
     * resolved.
     */
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
        if (age < 12 || age > 120) {
            throw new InvalidUserException(UserExceptions.AGE);
        }
        resetMaps();
        insertMap.put("PUsername", username);
        insertMap.put("Email", email);
        insertMap.put("Age", age);
        insertMap.put("PasswordUser", password);
        doGZDBCAction(GZDBActions.INSERT);
        resultMap = doGZDBCAction(GZDBActions.SELECT);
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

    /**
     * {@inheritDoc}. The ID of a User.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}. The custom name of a User.
     */
    @Override
    public String getName() {
        return username;
    }

    /**
     * An electronic mailing direction is required for identifying an account,
     * this method retreieves that information.
     *
     * @return The email of this User.
     */
    public String getEmail() {
        return email;
    }

    /**
     * The amount of years that a User currently has in real life.
     *
     * @return The age that a User posseses.
     */
    public int getAge() {
        return age;
    }

    /**
     * {@inheritDoc}. The respective image of a User.
     */
    @Override
    public BufferedImage getPicture() {
        return profilePicture;
    }

    /**
     * WORK IN PROGRESS
     *
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     * WORK IN PROGRESS
     *
     * @return WIP
     */
    public PlantList getPlants() {
        return plants;
    }

    /**
     * WORK IN PROGRESS
     *
     * @return WIP
     */
    public ProductList getProducts() {
        return products;
    }

    /**
     * WORK IN PROGRESS
     *
     * @return WIP
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Looks up if the User posseses access to administrator features.
     *
     * @return The state of access to the administrator features.
     */
    public boolean isAdministratorAccess() {
        return administratorAccess;
    }

    /**
     * Sets a new unique name for an already existing user.
     *
     * @param name The new name to be used.
     * @throws InvalidUserException If the new name does not meet the format
     * requirements.
     */
    public void setName(String name) throws InvalidUserException {
        if (!GZFormatter.isValid(name, GZFormats.USERNAME)) {
            throw new InvalidUserException(UserExceptions.FORMAT_USERNAME);
        }
        this.username = name;
        resetMaps();
        insertMap.put("PUsername", name);
        restrictionMap.put("ID", id);
        doGZDBCAction(GZDBActions.UPDATE);

    }

    /**
     * Sets a new unique email for an already existing user.
     *
     * @param email The new email to be associated with the user.
     * @throws InvalidUserException If the new email does not meet the format
     * requirements.
     */
    public void setEmail(String email) throws InvalidUserException {
        if (!GZFormatter.isValid(email, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_EMAIL);
        }
        this.email = email;
        resetMaps();
        insertMap.put("Email", email);
        restrictionMap.put("ID", id);
        doGZDBCAction(GZDBActions.UPDATE);
    }

    /**
     * Sets the new age that a user has in real life, this is a sensitive field
     * and might have a restriction to how many times it can be changed.
     *
     * @param age The new age the user posseses in real life.
     * @throws InvalidUserException If the new age does not meet the allowed
     * range.
     */
    public void setAge(int age) throws InvalidUserException {
        if (age < 12 || age > 120) {
            throw new InvalidUserException(UserExceptions.AGE);
        }
        this.age = age;
        resetMaps();
        insertMap.put("Age", age);
        restrictionMap.put("ID", id);
        doGZDBCAction(GZDBActions.UPDATE);
    }

    public void setPicture(BufferedImage picture) throws InvalidUserException {
        this.profilePicture = picture;
        /*
        picture handler needed
            resetMaps();
            insertMap.put("Picture", HANDLER_HERE);
            restrictionMap.put("ID", id);
           doGZDBCAction(GZDBActions.UPDATE);
         */
    }

    public void setLocation(Location location) throws InvalidUserException {
        this.location = location;
        /*
        location class needed
            resetMaps();
            insertMap.put("Picture", HANDLER_HERE);
            restrictionMap.put("ID", id);
            doGZDBCAction(GZDBActions.UPDATE);
         */
    }

    /**
     * Sets a new unique email for an already existing user if the old password
     * is used to grant permission to make the changes.
     *
     * @param oldPassword The password used to access the account.
     * @param newPassword The new password to be used for future access.
     * @throws InvalidUserException If the new age does not meet the format
     * requirements.
     */
    public void setPassword(String oldPassword, String newPassword) throws InvalidUserException {
        if (!GZFormatter.isValid(newPassword, GZFormats.PASSWORD)) {
            throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
        }
        if (!oldPassword.equals(this.password)) {
            throw new InvalidUserException(UserExceptions.PASSWORD);
        }
        this.password = newPassword;
        resetMaps();
        insertMap.put("PasswordUser", newPassword);
        restrictionMap.put("ID", id);
        doGZDBCAction(GZDBActions.UPDATE);
    }

    /**
     * Determines whether the User has access to administrator features or not.
     *
     * @param administratorAccess A true or false value describing the state of
     * it's administrator access.
     */
    public void setAdministratorAccess(boolean administratorAccess) {
        this.administratorAccess = administratorAccess;
    }

    /**
     * Resets the insertion and restriction maps to be used as new, so any
     * garbage data is not utilized on new usages.
     */
    private void resetMaps() {
        insertMap = new LinkedHashMap<>();
        restrictionMap = new LinkedHashMap<>();
    }

    /**
     * Performs the
     * {@link com.glamdring.greenZenith.externals.database.GZDBConnector#makeAction(GZDBActions, GZDBTables, LinkedHashMap<String, Object>,
     * LinkedHashMap<String, Object>)} method on the User table, which is our
     * default.
     *
     * @param action The action to be performed on the User table.
     * @return A set containing the information retrieved by a SQL Query.
     * @throws InvalidUserException If the connnection with our connector
     * couldn't get resolved.
     */
    private LinkedHashMap<String, Object> doGZDBCAction(GZDBActions action) throws InvalidUserException {
        try {
            return gzdbc.makeAction(action, GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
    }

    /**
     * Performs the
     * {@link com.glamdring.greenZenith.externals.database.GZDBConnector#makeAction(GZDBActions, GZDBTables, LinkedHashMap<String, Object>,
     * LinkedHashMap<String, Object>)} method on the a defined table.
     *
     * @param action The action to be performed on the User table.
     * @param table The table to perform the action on.
     * @return A set containing the information retrieved by a SQL Query.
     * @throws InvalidUserException If the connnection with our connector
     * couldn't get resolved.
     */
    private LinkedHashMap<String, Object> doGZDBCAction(GZDBActions action, GZDBTables table) throws InvalidUserException {
        try {
            return gzdbc.makeAction(action, table, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
    }

    /**
     * {@inheritDoc}. The deletion of a User registry completely.
     */
    @Override
    public void delete() {
        resetMaps();
        insertMap.put("ID", id);
        try {
            gzdbc.makeAction(GZDBActions.DELETE, GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {

        }
    }
}
