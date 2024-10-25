package com.glamdring.greenZenith.handlers;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

public class GZDBSuperManager {

    public GZDBConnector gzdbc;

    public LinkedHashMap<String, Object> insertMap = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> restrictionMap = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

    public LinkedHashSet<String> tableFields = null;
    public LinkedHashMap<String, String> tableTypes = null;
    public LinkedHashMap<String, Object> tableTypesReference = null;

    public GZDBSuperManager(GZDBTables table) throws GZDBResultException {
        gzdbc = new GZDBConnector();
        tableFields = gzdbc.getTableFields(table);
        tableTypes = gzdbc.getTableTypes(table);
    }

}
