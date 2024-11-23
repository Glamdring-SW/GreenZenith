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
    LENGTH_TITLE("El titulo del producto excede los 30 caracteres"),
    /**
     * The length of the User's product description does not follow the
     * established restrictions.
     */
    LENGTH_DESCRIPTION("La descripcion del producto es mayor de 250 caracteres."),
    /**
     * The price of the User's product is not a valid number.
     */
    PRICE("El precio ingresado no es un numero valido."),
    /**
     * The quantity of the User's product is not a valid number.
     */
    QUANTITY("La cantidad ingresada no es valida."),
    /**
     * The image of the User's product can not be utilized.
     */
    IMAGE("La imagen ingresada no puede ser utilizada."),
    /**
     * The plant that is being sold by a User is not valid.
     */
    PLANTSALE("La planta para este producto no puede ser utilizada."),
    /**
     * The data or connection of the owner of this product is not valid.
     */
    SELLER("El vendedor de este producto no es valido."),
    /**
     * The connection to the database cannot be resolved.
     */
    DATABASE("El accesso a los datos de este producto no fue exitoso."),
    /**
     * The specified product data does not resolve to any real data within the
     * database.
     */
    INEXISTANT("El producto especificado no existe."),;

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
