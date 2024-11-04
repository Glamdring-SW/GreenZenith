package com.glamdring.greenZenith.exceptions.application.product.constants;

public enum ProductExceptions {

    /**
     * The length of the User's plant name does not follow the established
     * restrictions.
     */
    LENGTH_TITLE("InvalidPlantNameLength"),
    /**
     * The length of the User's plant description does not follow the
     * established restrictions.
     */
    LENGTH_DESCRIPTION("InvalidPlantDescriptionLength"),
    /**
     * The price of the User's product is not a valid number.
     */
    PRICE("InvalidPlantPicture"),
    /**
     * The quantity of the User's product is not a valid number.
     */
    QUANTITY("InvalidPlantQuantity"),
    /**
     * The image of the User's product can not be utilized.
     */
    IMAGE("InvalidPlantPicture"),;

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
