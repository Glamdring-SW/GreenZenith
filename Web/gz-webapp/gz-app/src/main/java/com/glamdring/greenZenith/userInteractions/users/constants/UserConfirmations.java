package com.glamdring.greenZenith.userInteractions.users.constants;

/**
 * Contains predefined confirmation messages to indicate if a certain regarding a user action was
 * executed successfully.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public enum UserConfirmations {

    /**
     * If the register of a user was successful.
     */
    REGISTER("Se registro el usuario exitosamente"),
    /**
     * If the log in of a user was successful.
     */
    LOGIN("Se inicio sesion exitosamente."),
    /**
     * If the update of a user's name was successful.
     */
    USERNAME_UPDATE("El nombre de usuario fue actualizado correctamente."),
    /**
     * If the update of a user's email was successful.
     */
    EMAIL_UPDATE("El correo electronico fue actualizado correctamente."),
    /**
     * If the update of a user's age was successful.
     */
    AGE_UPDATE("La edad fue actualizada correctamente."),
    /**
     * If the update of a user's picture was successful.
     */
    PICTURE_UPDATE("La imagen del perfil fue actualizada correctamente."),
    /**
     * If the update of a user's picture was not successful but we resorted to
     * use the default profile picture..
     */
    PICTURE_DEFAULT("Se utilizo la imagen de perfil por defecto, asegurese de ingresar un"),
    /**
     * If the update of a user's name password was successful.
     */
    PASSWORD_UPDATE("La contraseña fue actualizada correctamente."),
    /**
     * If the update of a user's location was successful.
     */
    LOCATION_UPDATE("La ubicacion fue actualizada correctamente."),
    /**
     * If the update of a user's administrator access was successful.
     */
    ADMINISTRATORACCESS_UPDATE("Se actualizo el accesso a las opciones de administracion de manera correcta."),
    /**
     * If the update of a user's administrator access was successful.
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
    UserConfirmations(String confirmationMessage
    ) {
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
