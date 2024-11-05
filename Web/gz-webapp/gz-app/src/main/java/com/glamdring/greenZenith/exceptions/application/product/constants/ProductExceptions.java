package com.glamdring.greenZenith.exceptions.application.product.constants;

/**
 * Contains predefined exception messages to indicate a special type of
 * exception when caused by an interaction of the User with it's products and
 * cart.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.1
 * @since 0.2
 */
public enum ProductExceptions {

    /**
     * The length of the User's product name does not follow the established
     * restrictions.
     */
    LENGTH_TITLE("InvalidProductTitleLength"),
    /**
     * The length of the User's product description does not follow the
     * established restrictions.
     */
    LENGTH_DESCRIPTION("InvalidProductDescriptionLength"),
    /**
     * The price of the User's product is not a valid number.
     */
    PRICE("InvalidProductPrice"),
    /**
     * The quantity of the User's product is not a valid number.
     */
    QUANTITY("InvalidProductQuantity"),
    /**
     * The image of the User's product can not be utilized.
     */
    IMAGE("InvalidProductPicture"),
    /**
     * The plant that is being sold by a User is not valid.
     */
    PLANTSALE("InvalidProductPlantParent"),
    /**
     * The data or connection of the owner of this product is not valid.
     */
    SELLER("InvalidProductSeller"),;

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String exceptionMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param exceptionMessage The string value of the keyword.
     */
    ProductExceptions(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Gets the predefined exception message designed to give significant but
     * concise information.
     *
     * @return The predefined exception message string value held by the item.
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
