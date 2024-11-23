package com.glamdring.greenZenith.userInteractions.users;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.product.InvalidProductException;
import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.application.user.constants.UserExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.GZDBSuperManager;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.handlers.constants.GZFormats;
import com.glamdring.greenZenith.handlers.files.PictureHandler;
import com.glamdring.greenZenith.handlers.formats.GZFormatter;
import com.glamdring.greenZenith.handlers.locations.Location;
import com.glamdring.greenZenith.userInteractions.Attributable;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.userInteractions.plants.Plant;
import com.glamdring.greenZenith.userInteractions.plants.PlantList;
import com.glamdring.greenZenith.userInteractions.products.Product;
import com.glamdring.greenZenith.userInteractions.products.ProductList;
import com.glamdring.greenZenith.userInteractions.users.constants.UserConfirmations;

/**
 * Represents a User of the application, with it's respective attributes,
 * allowing the creation, modification and deletion of it, as well as being the
 * central piece of interaction of the entire application, allowing for access
 * and control od the plants, products and cart.
 *
 * @see GZDBSuperManager
 * @author Glamdring (Σxz)
 * @version 1.7.0
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
     * A list containing all the plants of this User.
     */
    private PlantList plants;
        /**
     * A list containing all the products of this User.
     */
    private ProductList products;
    /**
     * Disposable handler for picture files.
     */
    private PictureHandler pictureHandler;

    /**
     * Retrieves the information from the database utilizing only the ID, and
     * assigns the results to each correspondant field.
     *
     * @param id The unique identifier that corresponds to a certain User
     * registry on the database.
     * @throws InvalidUserException If the ID is not valid.
     * @throws GZDBResultException If the database connection cannot be resolved
     * or the default images cannot be loaded.
     */
    public User(int id) throws InvalidUserException, GZDBResultException {
        super();
        if (id < 1) {
            throw new InvalidUserException(UserExceptions.INEXISTANT);
        }
        resetMaps();
        try {
            pictureHandler = new PictureHandler();
        } catch (IOException e) {
            throw new InvalidUserException(UserExceptions.PICTURE);
        }
        insertMap.put("ID", id);
        try {
            resultList = gzdbc.select(GZDBTables.USER, insertMap);
            if (resultList.size() != 1) {
                throw new InvalidUserException(UserExceptions.ACCOUNT_ACCESS);
            }
            LinkedHashMap<String, Object> resultMap = resultList.get(0);
            try {
                this.id = id;
                this.username = (String) resultMap.get("PUsername");
                this.email = (String) resultMap.get("Email");
                this.age = (int) resultMap.get("Age");
                this.password = (String) resultMap.get("PasswordUser");
                this.administratorAccess = (boolean) resultMap.get("AdministratorAccess");
                if (resultMap.get("Picture") != null) {
                    this.profilePicture = pictureHandler.convertBlobToBufferedImage((Blob) resultMap.get("Picture"));
                } else {
                    this.profilePicture = pictureHandler.getDEFAULT_USER();
                }
                this.plants = new PlantList(this);
            } catch (InvalidPlantException e) {
                throw new InvalidUserException(UserExceptions.PLANTS, e);
            }
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
        }
        //this.location = ; location class needed
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
     * @throws GZDBResultException If the database connection cannot be resolved
     * or the default images cannot be loaded.
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
        try {
            pictureHandler = new PictureHandler();
        } catch (IOException e) {
            throw new InvalidUserException(UserExceptions.PICTURE);
        }
        insertMap.put("Email", email);
        insertMap.put("PasswordUser", password);
        try {
            resultList = gzdbc.select(GZDBTables.USER, insertMap);
            if (resultList.size() != 1) {
                throw new InvalidUserException(UserExceptions.ACCOUNT_ACCESS);
            }
            LinkedHashMap<String, Object> resultMap = resultList.get(0);
            try {
                this.email = email;
                this.password = password;
                this.id = (int) resultMap.get("ID");
                this.username = (String) resultMap.get("PUsername");
                this.age = (int) resultMap.get("Age");
                this.administratorAccess = (boolean) resultMap.get("AdministratorAccess");
                if (resultMap.get("Picture") != null) {
                    this.profilePicture = pictureHandler.convertBlobToBufferedImage((Blob) resultMap.get("Picture"));
                } else {
                    this.profilePicture = pictureHandler.getDEFAULT_USER();
                }
                this.plants = new PlantList(this);
            } catch (InvalidPlantException e) {
                throw new InvalidUserException(UserExceptions.PLANTS, e);
            }
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
        }

        //this.location = ; location class needed
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
     * format requirements or the default images cannot be loaded.
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
        if (age < 12) {
            throw new InvalidUserException(UserExceptions.AGE_SMALLER);
        }
        if (age > 120) {
            throw new InvalidUserException(UserExceptions.AGE_BIGGER);
        }
        try {
            pictureHandler = new PictureHandler();
        } catch (IOException e) {
            throw new InvalidUserException(UserExceptions.PICTURE);
        }
        resetMaps();
        try {
            insertMap.put("PUsername", username);
            insertMap.put("Email", email);
            insertMap.put("Age", age);
            insertMap.put("PasswordUser", password);
            if (profilePicture != null) {
                insertMap.put("Picture", pictureHandler.convertBufferedImageToBlob(profilePicture, gzdbc.getConnection()));
            } else {
                insertMap.put("Picture", pictureHandler.convertBufferedImageToBlob(pictureHandler.getDEFAULT_USER(), gzdbc.getConnection()));
            }
            gzdbc.insert(GZDBTables.USER, insertMap);
            resultList = gzdbc.select(GZDBTables.USER, insertMap);
        } catch (GZDBResultException e) {
            if (e.getMessage().equals(GZDBExceptionMessages.DUPLICATE_ENTRY.getExceptionMessage())) {
                throw new InvalidUserException(UserExceptions.DUPLICATE);
            } else {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
            }
        }
        try {
            this.id = (int) resultList.get(0).get("ID");
            this.username = username;
            this.email = email;
            this.age = age;
            this.password = password;
            this.administratorAccess = false;
            if (profilePicture != null) {
                this.profilePicture = profilePicture;
            } else {
                this.profilePicture = pictureHandler.getDEFAULT_USER();
            }
            this.plants = new PlantList(this);
        } catch (InvalidPlantException e) {
            throw new InvalidUserException(UserExceptions.PLANTS, e);
        }
        //this.location = ; location class needed
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
     * CURRENTLY UNUSED.
     *
     * @return A location containing multiple valuable information about the
     * user's location.
     */
    public Location getLocation() {
        return location;
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
     * Gets all the plants of this user in a list.
     *
     * @return A list containing all the plant that this user posseses.
     */
    public PlantList getPlants() {
        return plants;
    }

       /**
     * Gets all the products of this user in a list.
     *
     * @return A list containing all the products that this user posseses.
     */
    public ProductList getProducts() {
        return products;
    }

        /**
         * Updates all the user's data in a batch, if any given parameter is null it
         * get's omitted.
         *
         * @param newName The new name of this user.
         * @param newEmail The new email associated with this user's account.
         * @param newAge The new age of this user.
         * @param newPicture The new profile picture of this user.
         * @param oldPassword The old password of this account to check for it's
         * validity.
         * @param newPassword The new password to be associated with this account.
         * @return A long message indicating the state of the batch updates, which
         * ones failed and why, which one suscceded or if there was no updates at
         * all.
         */
        public String updateUserBatch(String newName, String newEmail, int newAge, BufferedImage newPicture, String oldPassword, String newPassword) {
            int updateCount = 0;
            StringBuilder messageBuilder = new StringBuilder();
            try {
                if (newPassword != null && !newPassword.isBlank() && !newPassword.equals(this.password)) {
                    appendUpdateMessage(messageBuilder, setPassword(oldPassword, newPassword));
                    updateCount++;
                }
            } catch (InvalidUserException e) {
                if (e.getMessage().equals(UserExceptions.PASSWORD_MATCHES.getExceptionMessage())) {
                    return e.getMessage();
                }
                if (newPassword != null && !newPassword.isBlank()) {
                    appendUpdateMessage(messageBuilder, e.getMessage());
                }
            }
            if (newName != null && !newName.isBlank() && !newName.equals(this.username)) {
                try {
                    appendUpdateMessage(messageBuilder, setName(newName));
                    updateCount++;
                } catch (InvalidUserException e) {
                    appendUpdateMessage(messageBuilder, e.getMessage());
                    messageBuilder.append(e.getMessage());
                }
            }
            if (newEmail != null && !newEmail.isBlank() && !newEmail.equals(this.email)) {
                try {
                    appendUpdateMessage(messageBuilder, setEmail(newEmail));
                    updateCount++;
                } catch (InvalidUserException e) {
                    appendUpdateMessage(messageBuilder, e.getMessage());
                    messageBuilder.append(e.getMessage());
                }
            }
            if (newAge != 0 && newAge != this.age) {
                try {
                    appendUpdateMessage(messageBuilder, setAge(newAge));
                    updateCount++;
                } catch (InvalidUserException e) {
                    appendUpdateMessage(messageBuilder, e.getMessage());
                    messageBuilder.append(e.getMessage());
                }
            }
            if (newPicture != null && newPicture != this.profilePicture) {
                try {
                    appendUpdateMessage(messageBuilder, setPicture(newPicture));
                    updateCount++;
                } catch (InvalidUserException e) {
                    appendUpdateMessage(messageBuilder, e.getMessage());
                }
            }
            if (updateCount == 0) {
                return UserConfirmations.NOCHANGES.getConfirmationMessage();
            }
            return messageBuilder.toString();
        }

         /**
          * Gets a singular plant depending on the ID and confirms if it matches with
          * the name.
          *
          * @param id The ID of the plant to look for.
          * @param name The name of the plant to check it's validity.
          * @return The plant with all of it's data.
          * @throws InvalidUserException If the plant's ID and name are not
          * correspondant or cannot be resolved correctly.
          */
          public Plant getPlant(int id, String name) throws InvalidUserException {
            if (name == null || name.isBlank()) {
                throw new InvalidUserException(UserExceptions.PLANT_ID);
            }
            try {
                if (plants.getFromMap(id).getName().equals(name)) {
                    return plants.getFromMap(id);
                } else {
                    throw new InvalidUserException(UserExceptions.PLANT_ID);
                }
            } catch (InvalidPlantException e) {
                throw new InvalidUserException(UserExceptions.PLANT_ID, e);
            }
        }

        /**
         * Adds a plant to this User's plant list and maps.
         *
         * @param name The name of the new plant to be added.
         * @param description The description of the new plant to be added.
         * @param quantity The quantity of the new plant to be added.
         * @param plantingDate The date the new plant was planted.
         * @param schedule The schedule to water the new plant to be added.
         * @param plantPicture The representative in real life picture of the new
         * plant to be added.
         * @throws InvalidUserException If the plant's ID and name are not
         * correspondant or cannot be resolved correctly.
         */
        public void addPlant(String name, String description, int quantity, LocalDate plantingDate, ArrayList<LocalTime> schedule, BufferedImage plantPicture) throws InvalidUserException {
            try {
                if (plants.getFromList(id).getName().equals(name)) {
                    throw new InvalidUserException(UserExceptions.PRODUCT_DUPLICATE);
                }
                plants.add(new Plant(name, description, quantity, plantingDate, schedule, plantPicture, this));
            } catch (InvalidPlantException e) {
                throw new InvalidUserException(UserExceptions.PLANT_ID, e);
            }
        }
    
        /**
         * Updates an already existing plant with the new provided data.
         *
         * @param id The ID of the plant to look for.
         * @param oldName The original name of the plant to check for it's validity.
         * @param newName The new name for this plant.
         * @param newPlantingDate The new date this plant was planted on.
         * @param newDescription The new description of this plant.
         * @param newQuantity The new quantity of this plant.
         * @param newSchedule The new schedule of this plant.
         * @param newPlantPicture The new picture of this plant in real life.
         * @throws InvalidUserException If the plant's ID and name are not
         * correspondant or cannot be resolved correctly.
         */
        public String updatePlantBatch(int id, String oldName, String newName, String newDescription, int newQuantity, LocalDate newPlantingDate, ArrayList<LocalTime> newSchedule, BufferedImage newPlantPicture) throws InvalidUserException {
            if (oldName == null || oldName.isBlank()) {
                throw new InvalidUserException(UserExceptions.PLANT_ID);
            }
            try {
                if (plants.getFromMap(id).getName().equals(oldName)) {
                    return plants.update(id, newName, newDescription, newQuantity, newPlantingDate, newSchedule, newPlantPicture, this);
                } else {
                    throw new InvalidUserException(UserExceptions.PLANT_ID);
                }
            } catch (InvalidPlantException e) {
                throw new InvalidUserException(UserExceptions.PLANT_ID, e);
            }
        }
    
        /**
         * Deletes a certain plant that this user posseses.
         *
         * @param id The ID of the plant to look for.
         * @param name The name of the plant to check it's validity.
         * @throws InvalidUserException If the plant's ID and name are not
         * correspondant.
         */
        public void deletePlant(int id, String name) throws InvalidUserException {
            if (name == null || name.isBlank()) {
                throw new InvalidUserException(UserExceptions.PLANT_ID);
            }
            try {
                if (plants.getFromMap(id).getName().equals(name)) {
                    plants.delete(id);
                } else {
                    throw new InvalidUserException(UserExceptions.PLANT_ID);
                }
            } catch (InvalidPlantException e) {
                throw new InvalidUserException(UserExceptions.PLANT_ID, e);
            }
        }
    

         /**
          * Gets a singular plant depending on the ID and confirms if it matches with
          * the name.
          *
          * @param id The ID of the product to look for.
          * @param name The name of the product to check it's validity.
          * @return The product with all of it's data.
          * @throws InvalidUserException If the product's ID and name are not
          * correspondant or cannot be resolved correctly.
          */
          public Product getProduct(int id, String name) throws InvalidUserException {
            if (name == null || name.isBlank()) {
                throw new InvalidUserException(UserExceptions.PRODUCT_ID);
            }
            try {
                if (products.getFromMap(id).getName().equals(name)) {
                    return products.getFromMap(id);
                } else {
                    throw new InvalidUserException(UserExceptions.PRODUCT_ID);
                }
            } catch (InvalidProductException e) {
                throw new InvalidUserException(UserExceptions.PRODUCT_ID, e);
            }
        }

        /**
         * Adds a product to this User's product list and maps.
         *
         * @param title The name of the new product to be added.
         * @param description The description of the new product to be added.
         * @param price The price of the new product to be added.
         * @param quantity The quantity of the new product to be added.
         * @param productPicture The representative in real life picture of the new
         * product to be added.
         * @param plantSale The plant that this product will sell.
         * @throws InvalidUserException If the product's ID and name are not
         * correspondant or cannot be resolved correctly.
         */
        public void addProduct(String title, String description, BigDecimal price, int quantity, BufferedImage productPicture, Plant plantSale) throws InvalidUserException {
            try {
                if (products.getFromList(id).getName().equals(title)) {
                    throw new InvalidUserException(UserExceptions.PRODUCT_DUPLICATE);
                }
                products.add(new Product(title, description, price, quantity, productPicture, plantSale));
            } catch (InvalidProductException e) {
                throw new InvalidUserException(UserExceptions.PRODUCT_ID, e);
            }
        }
    
        /**
         * Updates an already existing product with the new provided data.
         *
         * @param id The ID of the product to look for.
         * @param oldTitle The original title of the product to check for it's validity.
         * @param newTitle The new name for this product.
         * @param newDescription The new description of this product.
         * @param newPrice The new price of this product.
         * @param newQuantity The new quantity of this product.
         * @param newProductPicture The new picture of this product.
         * @throws InvalidUserException If the product's ID and name are not
         * correspondant or cannot be resolved correctly.
         */
        public String updatePlantBatch(int id, String oldTitle, String newTitle, String newDescription, BigDecimal newPrice, int newQuantity, BufferedImage newPlantPicture, Plant plantSale) throws InvalidUserException {
            if (oldTitle == null || oldTitle.isBlank()) {
                throw new InvalidUserException(UserExceptions.PRODUCT_ID);
            }
            try {
                if (products.getFromMap(id).getName().equals(oldTitle)) {
                    return products.update(id, newTitle, newDescription, newPrice, newQuantity, newPlantPicture, plantSale);
                } else {
                    throw new InvalidUserException(UserExceptions.PRODUCT_ID);
                }
            } catch (InvalidProductException e) {
                throw new InvalidUserException(UserExceptions.PRODUCT_ID, e);
            }
        }
    
        /**
         * Deletes a certain product that this user posseses.
         *
         * @param id The ID of the product to look for.
         * @param name The name of the product to check it's validity.
         * @throws InvalidUserException If the product's ID and name are not
         * correspondant.
         */
        public void deleteProduct(int id, String name) throws InvalidUserException {
            if (name == null || name.isBlank()) {
                throw new InvalidUserException(UserExceptions.PRODUCT_ID);
            }
            try {
                if (products.getFromMap(id).getName().equals(name)) {
                    products.delete(id);
                } else {
                    throw new InvalidUserException(UserExceptions.PRODUCT_ID);
                }
            } catch (InvalidProductException e) {
                throw new InvalidUserException(UserExceptions.PRODUCT_ID, e);
            }
        }

        /**
         * Sets a new unique name for an already existing user.
         *
         * @param newName The new name to be used.
         * @return A message providing information on the success of the update.
         * @throws InvalidUserException If the new name does not meet the format
         * requirements or the database connection cannot be resolved.
         */
        private String setName(String newName) throws InvalidUserException {
            try {
                if (!GZFormatter.isValidLength(newName, 3, 50)) {
                    throw new InvalidUserException(UserExceptions.LENGTH_USERNAME);
                }
                resetMaps();
                insertMap.put("PUsername", newName);
                restrictionMap.put("ID", id);
                gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
                this.username = newName;
                return UserConfirmations.USERNAME_UPDATE.getConfirmationMessage();
            } catch (GZDBResultException e) {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
            }
        }
    
        /**
         * Sets a new unique email for an already existing user.
         *
         * @param newEmail The new email to be associated with the user.
         * @return A message providing information on the success of the update.
         * @throws InvalidUserException If the new email does not meet the format
         * requirements or the database connection cannot be resolved.
         */
        private String setEmail(String newEmail) throws InvalidUserException {
            try {
                if (!GZFormatter.isValid(newEmail, GZFormats.EMAIL)) {
                    throw new InvalidUserException(UserExceptions.FORMAT_EMAIL);
                }
                resetMaps();
                insertMap.put("Email", newEmail);
                restrictionMap.put("ID", id);
                gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
                this.email = newEmail;
                return UserConfirmations.EMAIL_UPDATE.getConfirmationMessage();
            } catch (GZDBResultException e) {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
            }
        }
    
        /**
         * Sets the new age that a user has in real life, this is a sensitive field
         * and might have a restriction to how many times it can be changed.
         *
         * @param newAge The new age the user posseses in real life.
         * @return A message providing information on the success of the update.
         * @throws InvalidUserException If the new age does not meet the allowed
         * range or the database connection cannot be resolved.
         */
        private String setAge(int newAge) throws InvalidUserException {
            try {
                if (newAge < 12) {
                    throw new InvalidUserException(UserExceptions.AGE_SMALLER);
                }
                if (newAge > 120) {
                    throw new InvalidUserException(UserExceptions.AGE_BIGGER);
                }
                resetMaps();
                insertMap.put("Age", newAge);
                restrictionMap.put("ID", id);
                gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
                this.age = newAge;
                return UserConfirmations.AGE_UPDATE.getConfirmationMessage();
            } catch (GZDBResultException e) {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
            }
        }
    
        /**
         * Sets the new profile picture of this user.
         *
         * @param newPicture The new picture to use for the profile.
         * @return A message providing information on the success of the update.
         * @throws InvalidUserException If the picture can't be utilized or the
         * database connection cannot be resolved.
         */
        private String setPicture(BufferedImage newPicture) throws InvalidUserException {
            try {
                boolean defaultFlag = false;
                resetMaps();
                if (newPicture != null) {
                    insertMap.put("Picture", pictureHandler.convertBufferedImageToBlob(newPicture, gzdbc.getConnection()));
                } else {
                    insertMap.put("Picture", pictureHandler.convertBufferedImageToBlob(pictureHandler.getDEFAULT_USER(), gzdbc.getConnection()));
                    defaultFlag = true;
                }
                restrictionMap.put("ID", id);
                gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
                if (!defaultFlag) {
                    this.profilePicture = newPicture;
                    return UserConfirmations.PICTURE_UPDATE.getConfirmationMessage();
                } else {
                    this.profilePicture = pictureHandler.getDEFAULT_USER();
                    return UserConfirmations.PICTURE_DEFAULT.getConfirmationMessage();
                }
            } catch (GZDBResultException e) {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
            }
        }
    
        /**
         * Sets the new location in real life of this user.
         * CURRENTLY UNUSED
         *
         * @param newLocation The new location of the user in real life.
         * @return A successful confirmation message.
         * @throws InvalidUserException If the location can't be utilized or the
         * database connection cannot be resolved.
         */
        @SuppressWarnings("unused")
        private String setLocation(Location newLocation) throws InvalidUserException {
            this.location = newLocation;
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
            return UserConfirmations.LOCATION_UPDATE.getConfirmationMessage();
        }
    
        /**
         * Sets a new unique email for an already existing user if the old password
         * is used to grant permission to make the changes.
         *
         * @param oldPassword The password used to access the account.
         * @param newPassword The new password to be used for future access.
         * @return A message providing information on the success of the update.
         * @throws InvalidUserException If the new age does not meet the format
         * requirements or the database connection cannot be resolved.
         */
        private String setPassword(String oldPassword, String newPassword) throws InvalidUserException {
            try {
                if (!oldPassword.equals(this.password)) {
                    throw new InvalidUserException(UserExceptions.PASSWORD_MATCHES);
                }
                if (!GZFormatter.isValid(newPassword, GZFormats.PASSWORD)) {
                    throw new InvalidUserException(UserExceptions.FORMAT_PASSWORD);
                }
                resetMaps();
                insertMap.put("PasswordUser", newPassword);
                restrictionMap.put("ID", id);
                gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
                this.password = newPassword;
                return UserConfirmations.PASSWORD_UPDATE.getConfirmationMessage();
            } catch (GZDBResultException e) {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
            }
        }
    
        /**
         * Determines whether the User has access to administrator features or not.
         *
         * @param newAdministratorAccess A true or false value describing the state
         * of it's administrator access.
         * @return A message providing information on the success of the update.
         * @throws InvalidUserException If the new age does not meet the format
         * requirements or the database connection cannot be resolved.
         */
        public String setAdministratorAccess(boolean newAdministratorAccess) throws InvalidUserException {
            try {
                resetMaps();
                insertMap.put("AdministratorAccess", newAdministratorAccess);
                restrictionMap.put("ID", id);
                gzdbc.update(GZDBTables.USER, insertMap, restrictionMap);
                this.administratorAccess = newAdministratorAccess;
                return UserConfirmations.ADMINISTRATORACCESS_UPDATE.getConfirmationMessage();
            } catch (GZDBResultException e) {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION, e);
            }
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
