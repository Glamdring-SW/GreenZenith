package com.glamdring.greenZenith.userInteractions.products;

import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.product.InvalidProductException;
import com.glamdring.greenZenith.exceptions.application.product.constants.ProductExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Controls the individual product desired for purchase by a User.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.3
 */
public class CartProduct implements Killable {

    private User owner;
    private Product product;
    private int quantity;

    public CartProduct(int id, User owner) throws InvalidProductException {
        try {
            owner.resetMaps();
            owner.insertMap.put("PUser_ID", owner.getId());
            owner.insertMap.put("Product_ID", id);
            LinkedHashMap<String, Object> productListDB = owner.gzdbc.select(GZDBTables.CART, owner.insertMap).get(0);
            int productID = (int) productListDB.get("Product_ID");
            this.owner = owner;
            this.product = new Product(productID, owner.getConnector());
            this.quantity = (int) productListDB.get("Quantity");
        } catch (GZDBResultException | InvalidProductException e) {
            throw new InvalidProductException(ProductExceptions.SELLER, e);
        }
    }

    public User getOwner() {
        return owner;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * {@inheritDoc}. The deletion of this product registry completely.
     */
    @Override
    public void delete() {
        owner.resetMaps();
        owner.insertMap.put("PUser_ID", owner.getId());
        owner.insertMap.put("Product_ID", product.getId());
        try {
            owner.gzdbc.delete(GZDBTables.CART, owner.insertMap);
        } catch (GZDBResultException e) {
        }
    }

}
