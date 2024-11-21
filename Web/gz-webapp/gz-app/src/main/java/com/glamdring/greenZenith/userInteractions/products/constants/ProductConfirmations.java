package com.glamdring.greenZenith.userInteractions.products.constants;

/**
 * Contains predefined confirmation messages to indicate if a certain action
 * regarding a product was executed successfully.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.3
 */
public enum ProductConfirmations {

    /**
     * If the update of a product's title was successful.
     */
    TITLE("El titulo del producto fue actualizado correctamente."),
    /**
     * If the update of a user's email was successful.
     */
    DESCRIPTION("La descripcion del producto fue actualizada correctamente."),
    /**
     * If the update of a user's picture was successful.
     */
    PRICE("El precio fue actualizada correctamente."),
    /**
     * If the update of a user's age was successful.
     */
    QUANTITY("La cantidad total de este producto fue actualizada correctamente."),
    /**
     * If the update of a user's picture was not successful but we resorted to
     * use the default profile picture..
     */
    PICTURE_DEFAULT("La imagen de este producto no pudo ser utilizada, se asigno una imagen por defecto."),
    /**
     * If the update of a user's name password was successful.
     */
    PICTURE_UPDATE("La imagen de este producto fue actualizada correctamente."),
    /**
     * If the update of a user's administrator access was successful.
     */
    NOCHANGES("No se realizo ningun cambio o actualizacion."),;


     /**
     * Holds the value of each item's predefined exception message.
     */
    private final String confirmationMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param confirmationMessage The string value of the keyword.
     */
    ProductConfirmations(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    /**
     * Gets the predefined confirmation message designed to give significant but
     * concise information.
     *
     * @return The predefined confirmation message string value held by the
     * item.
     */
    public String getConfirmationMessage() {
        return confirmationMessage;
    }
}
