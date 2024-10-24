package com.glamdring.greenZenith.handlers;

import java.util.HashMap;
import java.util.HashSet;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

public class UserConnector {

    public GZDBConnector gzdbc;

    public HashMap<String, Object> insertMap = new HashMap<>();
    public HashMap<String, Object> restrictionMap = new HashMap<>();
    public HashMap<String, Object> resultMap = new HashMap<>();

    public HashSet<String> tableFields = null;
    public HashMap<String, String> tableTypes = null;
    public HashMap<String, Object> tableTypesReference = null;

    public UserConnector() throws GZDBResultException {
        gzdbc = new GZDBConnector();
        tableFields = gzdbc.getTableFields(GZDBTables.USER);
        tableTypes = gzdbc.getTableTypes(GZDBTables.USER);
    }

}
