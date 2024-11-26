package com.glamdring.greenZenith.userInteractions.products;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.product.InvalidProductException;
import com.glamdring.greenZenith.exceptions.application.product.constants.ProductExceptions;
import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.userInteractions.plants.Plant;
import com.glamdring.greenZenith.userInteractions.plants.PlantList;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Provides a list that contains all the products a User has for sale.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.1
 */
public class ProductList {

    /**
     * A list containing all the specified products.
     */
    ArrayList<Product> productList = new ArrayList<>();
    /**
     * A list containing all the specified products by using their IDs as keys.
     */
    LinkedHashMap<Integer, Product> productMap = new LinkedHashMap<>();

    /**
     * Initializes all the products from the database in a list and map.
     *
     * @param gzdbc A connection to the database to retrieve data correctly.
     * @throws InvalidProductException If the access to the database cannot be
     * resolved.
     */
    public ProductList(GZDBConnector gzdbc) throws InvalidProductException {
        try {
            ArrayList<LinkedHashMap<String, Object>> productListDB = gzdbc.selectAll(GZDBTables.PRODUCT);
            if (!productListDB.isEmpty()) {
                for (LinkedHashMap<String, Object> product : productListDB) {
                    int id = (int) product.get("ID");
                    productList.add(new Product(id, gzdbc));
                    productMap.put(id, new Product(id, gzdbc));
                }
            }
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.DATABASE, e);
        }
    }

    /**
     * Initializes the list and map of this user's products.
     *
     * @param owner The user to get all the products from.
     * @throws InvalidPlantException If the user does not provide a valid
     * connection.
     */
    public ProductList(User owner) throws InvalidProductException {
        try {
            PlantList plants = owner.getPlants();
            for (Plant plant : plants.getPlantList()) {
                owner.resetMaps();
                owner.insertMap.put("Plant_ID", plant.getId());
                ArrayList<LinkedHashMap<String, Object>> productListDB = owner.gzdbc.select(GZDBTables.PRODUCT, owner.insertMap);
                if (!productListDB.isEmpty()) {
                    for (LinkedHashMap<String, Object> product : productListDB) {
                        int id = (int) product.get("ID");
                        int plantSaleID = (int) product.get("Plant_ID");
                        productList.add(new Product(id, new Plant(plantSaleID, owner)));
                        productMap.put(id, new Product(id, new Plant(plantSaleID, owner)));
                    }
                }
            }
        } catch (GZDBResultException | InvalidPlantException | InvalidUserException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * The list with all the defined p, either from the total or from a User.
     *
     * @return A list with the data of each plant.
     */
    public ArrayList<Product> getProductList() {
        return productList;
    }

    /**
     * The map with all the defined products, either from the total or from a
     * User.
     *
     * @return A map with the data of each product and their IDs as keys.
     */
    public LinkedHashMap<Integer, Product> getProductMap() {
        return productMap;
    }

    /**
     * Checks if a User does not have any products in his possesion.
     *
     * @return If a product is owned by a User.
     */
    public boolean isEmpty() {
        return productList.isEmpty() && productMap.isEmpty();
    }

    /**
     * Gets the product data from the map that contains all the plants.
     *
     * @param id The ID of the product to look for.
     * @return The product data.
     * @throws InvalidProductException If the ID does not resolve to any product
     * registry.
     */
    public Product getFromMap(int id) throws InvalidProductException {
        if (productMap.get(id) == null) {
            throw new InvalidProductException(ProductExceptions.INEXISTANT);
        }
        return productMap.get(id);
    }

    /**
     * Gets the product data from the list that contains all the products.
     *
     * @param id The ID of the product to look for.
     * @return The product data.
     * @throws InvalidProductException If the ID does not resolve to any product
     * registry.
     */
    public Product getFromList(int id) throws InvalidProductException {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        throw new InvalidProductException(ProductExceptions.INEXISTANT);
    }

    /**
     * Add a new product to the list and map of products.
     *
     * @param product The new product to be added.
     */
    public void add(Product product) {
        int id = product.getId();
        productList.add(product);
        productMap.put(id, product);
    }

    /**
     * Updates all the data of a certain plant given by it's ID, any null or
     * empy parameters are not considered for the update.
     *
     * @param id The ID of the product to be updated.
     * @param newTitle The new name of this product.
     * @param newDescription The new description of this product.
     * @param newPrice The new price of this product.
     * @param newQuantity The new quantity of this product to be sold.
     * @param newPlantPicture The new picture of this product.
     * @return A message indicating the successfulness and failure of the
     * update.
     * @throws InvalidProductException If the ID does not resolve to any plant
     * registry.
     */
    public String update(int id, String newTitle, String newDescription, BigDecimal newPrice, int newQuantity, BufferedImage newProductPicture) throws InvalidProductException {
        if (productMap.get(id) == null) {
            throw new InvalidProductException(ProductExceptions.INEXISTANT);
        }
        String returnMessage = productMap.get(id).updateProductBatch(newTitle, newDescription, newPrice, newQuantity, newProductPicture);
        return returnMessage;
    }

    /**
     * Deletes a product from the database and then the list and maps.
     *
     * @param id The ID of the product to be deleted.
     * @throws InvalidProductException If the ID does not resolve to any product
     * registry.
     */
    public void delete(int id) throws InvalidProductException {
        if (productMap.get(id) == null) {
            throw new InvalidProductException(ProductExceptions.INEXISTANT);
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productMap.get(id).delete();
                productList.remove(i);
                productMap.remove(id);
                break;
            }
        }
    }
}
