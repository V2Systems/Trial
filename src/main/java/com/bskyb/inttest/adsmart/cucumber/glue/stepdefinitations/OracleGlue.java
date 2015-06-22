package com.bskyb.inttest.adsmart.cucumber.glue.stepdefinitations;

import com.bskyb.inttest.adsmart.customsql.SqlClient;

/**
 * Created by I&T Lab User on 25/02/2015.
 */
public class OracleGlue {

    static String oracleSid;
    static String oracleIP;
    static String oraclePort;
    static String username = "system";
    static String password;
    static SqlClient sqlClient;

    public void setupConfig(String oracleSid, String oracleIP, String oraclePort){

        this.oracleIP = oracleIP;
        this.oracleSid = oracleSid;
        this.oraclePort=oraclePort;
        this.password = username+"_"+oracleSid;
    }

    public int executeCountRecordQuery(String queryString) {
        try {
            sqlClient = new SqlClient();
            sqlClient.setConnectionDetails("oracle", oracleIP, oraclePort, oracleSid, username, password);
            sqlClient.getConnection();
            sqlClient.executeQuery(queryString);
            sqlClient.getResultSet();
            return sqlClient.getRowCount();
        }catch(Exception e){
            System.out.println("Exception in OracleGlue.executeCountRecordQuery");
        }
        return 0;
    }
}
