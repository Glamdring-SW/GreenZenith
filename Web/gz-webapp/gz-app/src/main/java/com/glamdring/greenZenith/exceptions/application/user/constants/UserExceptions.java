package com.glamdring.greenZenith.exceptions.application.user.constants;

/**
 * Contains predefined exception messages to indicate a special type of
 * exception when caused by an interaction of the User.
 *
 * @author Glamdring (Σxz)
 * @version 2.0.0
 * @since 0.1
 */
public enum UserExceptions {

    /**
     * The connection to the database utilizng the
     * {@link com.glamdring.greenZenith.externals.database.GZDBConnector} failed
     * to construct.
     */
    GZDBCONNECTION("La conexion a la base de datos no se realizo con exito"),
    /**
     * The ID utilized for establishing a User couldn't be found within the
     * database.
     */
    INEXISTANT("No existe un usuario con tales datos"),
    /**
     * The email utilized for establishing a User couldn't be found within the
     * database.
     */
    EMAIL("El correo electronico ingresado no es valido."),
    /**
     * The password given does not match the one in the database, negating the
     * access to the account.
     */
    ACCOUNT_ACCESS("No se pudo acceder a la cuenta, asegurese de haber ingresado la contraseña correctamente."),
    /**
     * The old password does not match the user's current password for providing
     * validity to update the user's data.
     */
    PASSWORD_MATCHES("La contraseña actual es incorrecta, no se puede actualizar el perfil."),
    /**
     * The password given is of null value and cannot be utilized.
     */
    PASSWORD_NULL("La contraseña actual no puede estar vacia."),
    /**
     * The age entered by the User is not within the valid range.
     */
    AGE_SMALLER("La edad ingresada es menor de lo admitido."),
    /**
     * The age entered by the User is not within the valid range.
     */
    AGE_BIGGER("La edad ingresada es mayor de lo admitido."),
    /**
     * The picture entered by the User cannot be saved.
     */
    PICTURE("La imagen dada no puede ser utilizada."),
    /**
     * The picture entered by the User cannot be saved.
     */
    PICTURE_HANDLER("Hubo un fallo al cargar el controlador de imagenes."),
    /**
     * The location entered by the User cannot be resolved.
     */
    LOCATION("La localizacion dada no es valida."),
    /**
     * The length of the User's username does not follow the established
     * restrictions.
     */
    LENGTH_USERNAME("La longitud del nombre de usuario excede el limite de 50 caracteres."),
    /**
     * The format of the User's email does not follow the established format,
     *
     * @see com.glamdring.greenZenith.handlers.formats.GZFormatter
     */
    FORMAT_EMAIL("El correo electronico no es valido, asegurese que se trate de un correo valido."),
    /**
     * The format of the User's password does not follow the established format,
     *
     * @see com.glamdring.greenZenith.handlers.formats.GZFormatter
     */
    FORMAT_PASSWORD("La contraseña no es segura, debe contener como minimo una letra mayuscula, un numero, un caracter especial y ser mayor a 12 digitos."),
    /**
     * The list of the user's plants cannot be resolved.
     *
     */
    PLANTS("La lista conteniendo sus plantas no posee informacion valida."),
    /**
     * The ID of the plant does not match the provided criteria for identity
     * validation.
     *
     */
    PLANT_ID("La informacion de la planta seleccionada posee algun error."),
    /**
     * The the name of this plant is a duplicate of another one.
     *
     */
    PLANT_DUPLICATE("El nombre de esta planta ya esta en uso."),
    /**
     * The list of the user's products cannot be resolved.
     */
    PRODUCTS("La lista conteniendo sus productos no posee informacion valida."),
    /**
     * The format of the User's password does not follow the established format,
     */
    CART("Su carrito no posee informacion valida.");

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String exceptionMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param exceptionMessage The string value of the keyword.
     */
    UserExceptions(String exceptionMessage) {
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
