package com.glamdring.greenZenith.exceptions.database.constants;

import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.GZDBExecutor;

/**
 * Contains predefined exception messages to indicate a special type of
 * exception when performing SQL Actions and Queries, including the creation,
 * filling and execution of statements.
 *
 * @see GZDBExecutor
 * @see GZDBConnector
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.1
 */
public enum GZDBExceptionMessages {

    /**
     * Insertions on the MySQL Database failed.
     */
    INSERT("Hubo un fallo al guardar la nueva informacion."),
    /**
     * Selections on the MySQL Database failed.
     */
    SELECT("Hubo un fallo al obtener la informacion."),
    /**
     * Changing of data on the MySQL Database failed.
     */
    UPDATE("Hubo un fallo al actualizar la informacion."),
    /**
     * Deletion of a table on the MySQL Database failed.
     */
    DELETE("Hubo un fallo al borrar la informacion."),
    /**
     * The {@link com.glamdring.greenZenith.externals.database.GZDBConnector}
     * failed to initialize itself succesfully in memory.
     */
    CONSTRUCTION_CONNECTOR("Hubo un fallo al obtener nuestro conector a la base de datos."),
    /**
     * The field established to seek information in was not found in the
     * database
     */
    SELECT_NOFIELD("No existe algun campo que contenga esa informacion."),
    /**
     * The field established to update information of was not found in the
     * database
     */
    UPDATE_NOFIELD("No existe un campo que pueda actualizar.");

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String exceptionMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param exceptionMessage The string value of the keyword.
     */
    GZDBExceptionMessages(String exceptionMessage) {
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
