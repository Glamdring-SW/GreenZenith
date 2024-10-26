package com.glamdring.greenZenith.exceptions.database.constants;

public enum GZDBExceptionMessages {

    INSERT("SQLExceptionOnInsert"),
    SELECT("SQLExceptionOnSelect"),
    UPDATE("SQLExceptionOnUpdate"),
    DELETE("SQLExceptionOnDelete"),
    UNREACHABLE_CASE("UnreachableCaseAccessed"),
    CONSTRUCTION_CONNECTOR("ConnectorConstructionError"),
    KEYINPUT_1("KeyConflictsOnInput1"),
    KEYINPUT_2("KeyConflictsOnInput2"),
    INPUT_SMALLER("InputSmallerThanRequired"),
    INPUT_BIGGER("InputBiggerThanRequired"),
    SELECT_NOFIELD("NoCorrespondantFieldForSelect"),
    UPDATE_NOFIELD("NoCorrespondantFieldForUpdate");

    private final String exceptionMessage;

    GZDBExceptionMessages(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
