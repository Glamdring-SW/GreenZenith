package com.glamdring.greenZenith.userInteractions.plants.constants;

/**
 * Contains predefined confirmation messages to indicate if a certain action
 * regarding a plant was executed successfully.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public enum PlantConfirmations {

    /**
     * If the update of a plant's name was successful.
     */
    NAME("El nombre de la planta fue actualizado correctamente."),
    /**
     * If the update of a plant's description was successful.
     */
    DESCRIPTION("La descripcion de la planta fue actualizada correctamente."),
    /**
     * If the update of a plant's quantity was successful.
     */
    QUANTITY("La cantidad total de plantas de este tipo fue actualizada correctamente."),
    /**
     * If the update of a plant's date was successful.
     */
    PLANTINGDATE("La fecha de plantado fue actualizada correctamente."),
    /**
     * If the update of a plant's picture was not successful but we resorted to
     * use the default profile picture.
     */
    PICTURE_DEFAULT("La imagen de esta planta no pudo ser utilizada, se asigno una imagen por defecto."),
    /**
     * If the update of a plant's picture was successful.
     */
    PICTURE_UPDATE("La imagen de esta planta fue actualizada correctamente."),
    /**
     * If the update of a plant's schedule was successful.
     */
    SCHEDULE("El horario de riego de esta planta fue actualizado correctamente."),
    /**
     * If no changes were made to the plant.
     */
    NOCHANGES("No se realizo ningun cambio o actualizacion."),;

    /**
     * Holds the value of each item's predefined confirmation message.
     */
    private final String confirmationMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param confirmationMessage The string value of the keyword.
     */
    PlantConfirmations(String confirmationMessage) {
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
