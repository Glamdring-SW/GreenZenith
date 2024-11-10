package com.glamdring.greenZenith.exceptions.application.plant.constants;

/**
 * Contains predefined exception messages to indicate a special type of
 * exception when caused by an interaction of the User with it's plants.
 *
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.2
 */
public enum PlantExceptions {

    /**
     * The length of the User's plant name does not follow the established
     * restrictions.
     */
    LENGTH_NAME("No se pueden guardar nombres de plantas mayores a 25 caracteres."),
    /**
     * The length of the User's plant description does not follow the
     * established restrictions.
     */
    LENGTH_DESCRIPTION("No se aceptan descripciones mayores de 500 caracteres."),
    /**
     * The quantity of the User's plant is not a valid number.
     */
    QUANTITY("No se aceptan cantidades menores a 1."),
    /**
     * The quantity that will be decreased by 1 yields a non valid number.
     */
    QUANTITY_DECREASEBY1("Ya no se puede reducir mas la cantidad de esta planta."),
    /**
     * The image of the User's plant can not be utilized.
     */
    IMAGE("La imagen proporcionada no puede ser utilizada."),
    /**
     * The date of the User's plant can not be utilized.
     */
    DATE_NULL("La fecha de plantado es mucho mayor de lo comun, asegurese de que la planta no sea mayor de 100 años, si puede verificar que su planta es mayor de 100 años, contactenos!"),
    /**
     * The date of the User's plant exceeds the allowed years.
     */
    DATE_BIGGER("La fecha de plantado es mucho mayor de lo comun, asegurese de que la planta no sea mayor de 100 años, si puede verificar que su planta es mayor de 100 años, contactenos!"),
    /**
     * The water time list of the User's holds non valid times.
     */
    SCHEDULE("El horario de riego no es valido."),
     /**
     * The water time list of the User's plant can not be utilized.
     */
    SCHEDULE_EMPTY("El horario de riego no es valido."),
    /**
     * The owner of this plant cannot be resolved.
     */
    OWNER("El usuario dueño de esta planta no posee accesso a su informacion."),
    /**
     * The connection to the database cannot be resolved.
     */
    DATABASE("El accesso a los datos de esta planta no fue exitoso."),
    /**
     * The specified plant data does not resolve to any real data within the
     * database.
     */
    INEXISTANT("La planta especificada no existe."),;

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String exceptionMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param exceptionMessage The string value of the keyword.
     */
    PlantExceptions(String exceptionMessage) {
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
