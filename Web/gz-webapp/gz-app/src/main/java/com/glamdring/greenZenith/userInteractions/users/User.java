package com.glamdring.greenZenith.userInteractions.users;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.application.user.constants.UserExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.GZDBSuperManager;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.handlers.constants.GZFormats;
import com.glamdring.greenZenith.handlers.files.PictureHandler;
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
 * @version 1.5.3
 * @since 0.1
 */
public class User extends GZDBSuperManager implements Attributable, Killable, Serializable {

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
     * Disposable handler for picture files.
     */
    private PictureHandler pictureHandler = new PictureHandler();

    /**
     * Retrieves the information from the database utilizing only the ID, and
     * assigns the results to each correspondant field.
     *
     * @param id The unique identifier that corresponds to a certain User
     * registry on the database.
     * @throws InvalidUserException If the ID is not valid.
     * @throws GZDBResultException If the database connection cannot be
     * resolved.
     */
    public User(int id) throws InvalidUserException, GZDBResultException {
        super();
        resetMaps();
        insertMap.put("ID", id);
        try {
            resultList = gzdbc.select(GZDBTables.USER, insertMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
        }
        this.id = id;
        this.username = (String) resultList.get(0).get("PUsername");
        this.email = (String) resultList.get(0).get("Email");
        this.age = (int) resultList.get(0).get("Age");
        this.password = (String) resultList.get(0).get("PasswordUser");
        this.administratorAccess = (boolean) resultList.get(0).get("AdministratorAccess");
        this.profilePicture = pictureHandler.convertBlobToBufferedImage((Blob) resultList.get(0).get("Picture"));
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
        super();
        if (!GZFormatter.isValid(email, GZFormats.EMAIL)) {
            throw new InvalidUserException(UserExceptions.FORMAT_EMAIL);
        }
        if (!GZFormatter.isValid(password, GZFormats.PASSWORD)) {
            throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
        }
        resetMaps();
        insertMap.put("Email", email);
        insertMap.put("PasswordUser", password);
        try {
            resultList = gzdbc.select(GZDBTables.USER, insertMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
        }
        this.email = email;
        this.password = password;
        this.id = (int) resultList.get(0).get("ID");
        this.username = (String) resultList.get(0).get("PUsername");
        this.age = (int) resultList.get(0).get("Age");
        this.administratorAccess = (boolean) resultList.get(0).get("AdministratorAccess");
        this.profilePicture = pictureHandler.convertBlobToBufferedImage((Blob) resultList.get(0).get("Picture"));
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
     * @param profilePicture A picture of the person in real life.
     * @throws InvalidUserException If the data provided does not meet the
     * format requirements.
     * @throws GZDBResultException If the database connection cannot be
     * resolved.
     */
    public User(String username, String email, String password, int age, BufferedImage profilePicture
    /*Location location,*/) throws InvalidUserException, GZDBResultException {
        super();
        if (!GZFormatter.isValidLength(username, 3, 50)) {
            throw new InvalidUserException(UserExceptions.LENGTH_USERNAME);
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
        insertMap.put("Picture", pictureHandler.convertBufferedImageToBlob(profilePicture, gzdbc.getConnection()));
        try {
            gzdbc.insert(GZDBTables.USER, insertMap);
            resultList = gzdbc.select(GZDBTables.USER, insertMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
        }
        this.id = (int) resultList.get(0).get("ID");
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
        this.administratorAccess = false;
        this.profilePicture = profilePicture;

        //this.location = ; location class needed
        //this.plants = ; plantlist class needed
        //this.products = ; productlist class needed
        //this.cart = ; cart class needed
    }

    /**
     * {@inheritDoc}. The ID of this User.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}. The custom name of this User.
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
     * {@inheritDoc}. The respective image of this User.
     */
    @Override
    public BufferedImage getPicture() {
        return profilePicture;
    }

    /**
     * Transforms the profile picture into a Base64 encodification for web
     * application display purposes.
     *
     * @return A base 64 encodification of the profile picture.
     */
    public String getBase64Picture() {
        return pictureHandler.convertBufferedImageToBase64(profilePicture);
    }

    /**
     * Transforms the profile picture into a Base64 encodification for web
     * application display purposes.
     *
     * @param externalPicture The picture from an external source outside the
     * User.
     * @return A base 64 encodification of the external picture.
     */
    public String getBase64PictureFromExternalSource(BufferedImage externalPicture) {
        return pictureHandler.convertBufferedImageToBase64(externalPicture);
    }

    /**
     * Provides the picture handler for the subsequent plant and product
     * classes.
     *
     * @return The picture handler of this user.
     */
    public PictureHandler getPictureHandler() {
        return pictureHandler;
    }

    /**
     * The location that this user lives in.
     *
     * @return A location containing multiple valuable information about the
     * user's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * All the plants that this user posseses.
     *
     * @return A list containing all the plants owned.
     * @throws InvalidUserException If the user's plant's cannot be resolved.
     */
    public PlantList getPlants() throws InvalidUserException {
        try {
            return new PlantList(this);
        } catch (InvalidPlantException e) {
            throw new InvalidUserException(UserExceptions.PLANTS, e);
        }
    }

    /**
     * All the products that this user has for sale. WORK IN PROGRESS.
     *
     * @return A list containing all the products for sale.
     */
    public ProductList getProducts() {
        return null;
    }

    /**
     * All the products that this user wants to buy. WORK IN PROGRESS.
     *
     * @return A list containing the different products.
     */
    public Cart getCart() {
        return null;
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
     * Provides the connector used to access the database.
     *
     * @return The connection to the database.
     */
    public GZDBConnector getConnector() {
        return gzdbc;
    }

    /**
     * Sets a new unique name for an already existing user.
     *
     * @param name The new name to be used.
     * @throws InvalidUserException If the new name does not meet the format
     * requirements.
     */
    public void setName(String name) throws InvalidUserException {
        if (!GZFormatter.isValidLength(name, 3, 50)) {
            throw new InvalidUserException(UserExceptions.LENGTH_USERNAME);
        }
        this.username = name;
        resetMaps();
        insertMap.put("PUsername", name);
        restrictionMap.put("ID", id);
        try {
            gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
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
        try {
            gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
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
        try {
            gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
    }

    /**
     * Sets the new profile picture of this user.
     *
     * @param picture The new picture to use for the profile.
     * @throws InvalidUserException If the picture can't be utilized.
     */
    public void setPicture(BufferedImage picture) throws InvalidUserException {
        this.profilePicture = picture;
        /*
        picture handler needed
            resetMaps();
            insertMap.put("Picture", HANDLER_HERE);
            restrictionMap.put("ID", id);
           try {
            gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
         */
    }

    /**
     * Sets the new location in real life of this user.
     *
     * @param location The new location of the user in real life.
     * @throws InvalidUserException If the location can't be utilized.
     */
    public void setLocation(Location location) throws InvalidUserException {
        this.location = location;
        /*
        location class needed
            resetMaps();
            insertMap.put("Picture", HANDLER_HERE);
            restrictionMap.put("ID", id);
              try {
            gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
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
        try {
            gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
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
     * {@inheritDoc}. The deletion of this User registry completely.
     */
    @Override
    public void delete() {
        resetMaps();
        insertMap.put("ID", id);
        try {
            gzdbc.delete(GZDBTables.USER, insertMap);
        } catch (GZDBResultException e) {

        }
    }
}
