package com.glamdring.greenZenith.userInteractions.products;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.product.InvalidProductException;
import com.glamdring.greenZenith.exceptions.application.product.constants.ProductExceptions;
import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.handlers.formats.GZFormatter;
import com.glamdring.greenZenith.userInteractions.Attributable;
import com.glamdring.greenZenith.userInteractions.Interactable;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.userInteractions.plants.Plant;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Represents a product owned by a User, with it's respective attributes and
 * parent plant for sale, allowing the creation, modification and deletion of
 * it.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.1
 */
public class Product implements Killable, Attributable, Interactable, Serializable {

    /**
     * The unique identifier for this product.
     */
    private final int id;

    /**
     * The title of this product for sale, has to be concise and useful.
     */
    private String title;

    /**
     * The description of this product, gives a better understanding of what
     * type of plant they are buying.
     */
    private String description;

    /**
     * The price this product is selling for.
     */
    private BigDecimal price;

    /**
     * The quantity of this product that is being sold.
     */
    private int quantity;

    /**
     * A representative image of this product in real life.
     */
    private BufferedImage productPicture;

    /**
     * The plant this product is offering for sale.
     */
    private final Plant plantSale;

    /**
     * The owner of this product.
     */
    private final User seller;

    /**
     * Retrieves information form the database utilizing only the ID and a
     * connection to the database, and assigns the results to each correspondant
     * field.
     *
     * @param id The unique identifier that corresponds to a certain product
     * registry on the database.
     * @param gzdbc A connection to the database.
     * @throws InvalidProductException If the seller of this product does nor
     * provide a connection or cannot be resolved, or if the plant being sold
     * cannot be resolved or the ID cannot be resolved.
     */
    public Product(int id, GZDBConnector gzdbc) throws InvalidProductException {
        if (id < 1) {
            throw new InvalidProductException(ProductExceptions.SELLER);
        }

        this.id = id;
        try {
            LinkedHashMap<String, Object> productMap = new LinkedHashMap<>();
            productMap.put("ID", id);
            LinkedHashMap<String, Object> resultMap = gzdbc.select(GZDBTables.PRODUCT, productMap).get(0);

            this.title = (String) resultMap.get("Title");
            this.description = (String) resultMap.get("Description");
            this.price = (BigDecimal) resultMap.get("Price");
            this.quantity = (int) resultMap.get("Quantity");
            this.plantSale = new Plant((int) resultMap.get("Plant_ID"), gzdbc);
            this.seller = new User(plantSale.getOwner().getId());
        } catch (GZDBResultException | InvalidUserException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        } catch (InvalidPlantException e) {
            throw new InvalidProductException(ProductExceptions.PLANTSALE, e);
        }

    }

    /**
     * Retrieves information form the database utilizing only the ID and the
     * plant that this product refers to from the database, and assigns the
     * results to each correspondant field.
     *
     * @param id The unique identifier that corresponds to a certain product
     * registry on the database.
     * @param plantSale The plant that is being sold.
     * @throws InvalidProductException If the seller of this product does not
     * provide a connection or the ID cannot be resolved.
     */
    public Product(int id, Plant plantSale) throws InvalidProductException {
        if (id < 1) {
            throw new InvalidProductException(ProductExceptions.SELLER);
        }

        this.id = id;
        this.plantSale = plantSale;
        this.seller = plantSale.getOwner();

        try {
            LinkedHashMap<String, Object> productMap = new LinkedHashMap<>();
            productMap.put("ID", id);
            LinkedHashMap<String, Object> resultMap = seller.gzdbc.select(GZDBTables.PRODUCT, productMap).get(0);

            this.title = (String) resultMap.get("Title");
            this.description = (String) resultMap.get("Description");
            this.price = (BigDecimal) resultMap.get("Price");
            this.quantity = (int) resultMap.get("Quantity");
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * Creates a new Product object with the assigned parameters, which then is
     * stored into the database.
     *
     * @param title The name identifier of this product at sale.
     * @param description The description of this product.
     * @param price The price users have to pay in order to buy this product.
     * @param quantity The amount of plants at sale.
     * @param plantSale The plant that is being sold.
     * @throws InvalidProductException If the title or description exceed the
     * allowed length, if the price is below or equal to zero, if the quantity
     * is bigger than the plant's quantity.
     */
    public Product(String title, String description, BigDecimal price, int quantity,
            /* BufferedImage productPicture, */ Plant plantSale) throws InvalidProductException {
        if (!GZFormatter.isValidMaxLength(title, 30)) {
            throw new InvalidProductException(ProductExceptions.LENGTH_TITLE);
        }
        if (!GZFormatter.isValidMaxLength(description, 250)) {
            throw new InvalidProductException(ProductExceptions.LENGTH_TITLE);
        }
        if (quantity > plantSale.getQuantity()) {
            throw new InvalidProductException(ProductExceptions.QUANTITY);
        }
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            throw new InvalidProductException(ProductExceptions.PRICE);
        }
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.plantSale = plantSale;
        this.seller = plantSale.getOwner();

        LinkedHashMap<String, Object> productMap = new LinkedHashMap<>();
        productMap.put("Title", title);
        productMap.put("Description", description);
        productMap.put("Price", price);
        productMap.put("Quantity", quantity);
        productMap.put("Plant_ID", seller.getId());

        try {
            seller.gzdbc.insert(GZDBTables.PRODUCT, productMap);
            this.id = (int) seller.gzdbc.select(GZDBTables.PRODUCT, productMap).get(0).get("ID");

        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * {@inheritDoc} The ID of this plant.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc} The custom title of this product.
     */
    @Override
    public String getName() {
        return title;
    }

    /**
     * {@inheritDoc} The custom description of this product.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * The price of this product.
     *
     * @return A price of the respective currency.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * {@inheritDoc} The quantity of this product that is for sale.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * {@inheritDoc}. The respective image of this product.
     */
    @Override
    public BufferedImage getPicture() {
        return productPicture;
    }

    /**
     * The plant that is being sold.
     *
     * @return The parent plant of this product.
     */
    public Plant getPlantSale() {
        return plantSale;
    }

    /**
     * The user that is selling this product.
     *
     * @return The parent User of this product.
     */
    public User getSeller() {
        return seller;
    }

    /**
     * Sets the title that this product will display.
     *
     * @param title The new title to be displayed.
     * @throws InvalidProductException If the title exceeds the allowed length.
     */
    public void setTitle(String title) throws InvalidProductException {
        if (!GZFormatter.isValidMaxLength(title, 30)) {
            throw new InvalidProductException(ProductExceptions.LENGTH_TITLE);
        }
        this.title = title;
        seller.resetMaps();
        seller.insertMap.put("Title", title);
        seller.restrictionMap.put("ID", id);
        try {
            seller.gzdbc.update(GZDBTables.PRODUCT, seller.insertMap, seller.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * Sets the description that this product will display.
     *
     * @param description The new description to be displayed.
     * @throws InvalidProductException If the description exceeds the allowed
     * length.
     */
    public void setDescription(String description) throws InvalidProductException {
        if (!GZFormatter.isValidMaxLength(description, 250)) {
            throw new InvalidProductException(ProductExceptions.LENGTH_DESCRIPTION);
        }
        this.description = description;
        seller.resetMaps();
        seller.insertMap.put("Description", description);
        seller.restrictionMap.put("ID", id);
        try {
            seller.gzdbc.update(GZDBTables.PRODUCT, seller.insertMap, seller.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * Sets the price that this product will cost.
     *
     * @param price The new price it will cost.
     * @throws InvalidProductException If the price is not a valid number.
     */
    public void setPrice(BigDecimal price) throws InvalidProductException {
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            throw new InvalidProductException(ProductExceptions.PRICE);
        }
        this.price = price;
        seller.resetMaps();
        seller.insertMap.put("Price", price);
        seller.restrictionMap.put("ID", id);
        try {
            seller.gzdbc.update(GZDBTables.PRODUCT, seller.insertMap, seller.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * Sets the quantity of this plant that is for sale as a product.
     *
     * @param quantity The new quantity to be put for sale.
     * @throws InvalidProductException If the quantity exceeds the amount of
     * this type of plant that the seller has.
     */
    public void setQuantity(int quantity) throws InvalidProductException {
        if (quantity < plantSale.getQuantity()) {
            throw new InvalidProductException(ProductExceptions.QUANTITY);
        }
        this.quantity = quantity;
        seller.resetMaps();
        seller.insertMap.put("Quantity", quantity);
        seller.restrictionMap.put("ID", id);
        try {
            seller.gzdbc.update(GZDBTables.PRODUCT, seller.insertMap, seller.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * Sets a representative picture of this product.
     *
     * @param productPicture The new picture to be displayed.
     */
    public void setProductPicture(BufferedImage productPicture) {
        this.productPicture = productPicture;
    }

    /**
     * {@inheritDoc}. The deletion of this plant registry completely.
     */
    @Override
    public void delete() {
        seller.resetMaps();
        seller.insertMap.put("ID", id);
        try {
            seller.gzdbc.delete(GZDBTables.PRODUCT, seller.insertMap);
        } catch (GZDBResultException e) {
        }
    }

}