package com.glamdring.greenZenith.userInteractions.products;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.product.InvalidProductException;
import com.glamdring.greenZenith.exceptions.application.product.constants.ProductExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Controls the products desired for purchase by a User.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.1
 */
public class Cart {

    /**
     * A list containing all the specified products.
     */
    ArrayList<CartProduct> productList = new ArrayList<>();
    /**
     * A list containing all the specified products by using their IDs as keys.
     */
    LinkedHashMap<Integer, CartProduct> productMap = new LinkedHashMap<>();

    /**
     * Initializes the list and map of this user's products.
     *
     * @param owner The user to get all the products from.
     * @throws InvalidPlantException If the user does not provide a valid
     * connection.
     */
    public Cart(User owner) throws InvalidProductException {
        try {
            owner.resetMaps();
            owner.insertMap.put("PUser_ID", owner.getId());
            ArrayList<LinkedHashMap<String, Object>> productListDB = owner.gzdbc.select(GZDBTables.CART, owner.insertMap);
            if (!productListDB.isEmpty()) {
                for (LinkedHashMap<String, Object> product : productListDB) {
                    int id = (int) product.get("Product_ID");
                    productList.add(new CartProduct(id, owner));
                    productMap.put(id, new CartProduct(id, owner));
                }
            }
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * The list with all the defined p, either from the total or from a User.
     *
     * @return A list with the data of each plant.
     */
    public ArrayList<CartProduct> getProductList() {
        return productList;
    }

    /**
     * The map with all the defined products, either from the total or from a
     * User.
     *
     * @return A map with the data of each product and their IDs as keys.
     */
    public LinkedHashMap<Integer, CartProduct> getProductMap() {
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
    public CartProduct getFromMap(int id) throws InvalidProductException {
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
    public CartProduct getFromList(int id) throws InvalidProductException {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct().getId() == id) {
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
    public void add(User owner, int id, int quantity) throws InvalidProductException {
        try {
            owner.resetMaps();
            owner.insertMap.put("PUser_ID", owner.getId());
            owner.insertMap.put("Product_ID", id);
            owner.insertMap.put("Quantity", quantity);
            owner.gzdbc.insert(GZDBTables.CART, owner.insertMap);
            productList.add(new CartProduct(id, owner));
            productMap.put(id, new CartProduct(id, owner));
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
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
    public void update(User owner, int id, int quantity) throws InvalidProductException {
        try {
            owner.resetMaps();
            owner.insertMap.put("PUser_ID", owner.getId());
            owner.insertMap.put("Product_ID", id);
            owner.insertMap.put("Quantity", quantity);
            owner.restrictionMap.put("PUser_ID", owner.getId());
            owner.restrictionMap.put("Product_ID", id);
            owner.gzdbc.update(GZDBTables.CART, owner.insertMap, owner.restrictionMap);
            productList.add(new CartProduct(id, owner));
            productMap.put(id, new CartProduct(id, owner));
        } catch (GZDBResultException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    /**
     * Deletes a product from the database and then the list and maps.
     *
     * @param id The ID of the product to be deleted.
     * @throws InvalidProductException If the ID does not resolve to any product
     * registry.
     */
    public void delete(User owner, int id) throws InvalidProductException {
        if (productMap.get(id) == null) {
            throw new InvalidProductException(ProductExceptions.INEXISTANT);
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct().getId() == id) {
                try {
                    owner.resetMaps();
                    owner.insertMap.put("PUser_ID", owner.getId());
                    owner.insertMap.put("Product_ID", id);
                    owner.gzdbc.delete(GZDBTables.CART, owner.insertMap);
                    productList.remove(i);
                    productMap.remove(id);
                    break;
                } catch (GZDBResultException e) {
                    throw new InvalidProductException(ProductExceptions.SELLER, e);
                }
            }
        }
    }

    public void deleteAll(User owner) {
       for (Integer id : productMap.keySet()) {
           productMap.get(id).delete();
       }
       productList.clear();
       productMap.clear();
    }
}
