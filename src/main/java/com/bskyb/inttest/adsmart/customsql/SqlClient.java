package com.bskyb.inttest.adsmart.customsql;

import java.sql.*;
import java.util.Properties;

/**
 * Created by I&T Lab User on 16/12/2014.
 */
public class SqlClient {

    static Connection conn = null;
    static String dbms;
    static String serverName;
    static String portNumber = "1525";
    static String dbName;
    static String userName;
    static String password;
    static String queryString;
    static Statement statement;
    static ResultSet resultSet;
    static int rowCount=0;
    static ResultSetMetaData resultSetMetadata;


    public void setConnectionDetails(String dbms, String serverName, String portNumber, String dbName, String userName, String password){
        this.dbms=dbms;
        this.serverName=serverName;
        this.portNumber = portNumber;
        this.dbName=dbName;
        this.userName=userName;
        this.password=password;
    }

    public Connection getConnection() throws SQLException {

        try{

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Properties connectionProps = new Properties();
            connectionProps.put("user", this.userName);
            connectionProps.put("password", this.password);

            if (this.dbms.equals("oracle")) {
                conn = DriverManager.getConnection(
                        "jdbc:" + this.dbms + ":thin:@" +
                                this.serverName +
                                ":" + this.portNumber + ":"+
                        this.dbName, connectionProps );
            }

            System.out.println("Connected to database: " + this.serverName +"::"+ this.dbName);
            return conn;
        }catch(Exception e){
            System.out.println("Error While establishing sql connection");
            return null;
        }
    }
    public void executeQuery(String queryString){
        try {
            this.queryString=queryString;
            statement = conn.createStatement();
            boolean result= statement.execute(queryString);
            if(result){

                resultSet=statement.executeQuery(queryString);
                resultSetMetadata=resultSet.getMetaData();
            }

        }catch(SQLException e){
            System.out.println("Exception "+e+" in SqlClient.close");
        }
    }

    public ResultSet getResultSet(){
        try{

            System.out.print("SQL: "+ this.queryString +"\n");
            int numberOfRows = 0;
            rowCount=0;
            //printing column Headers
            for(int i=0; i< resultSetMetadata.getColumnCount();i++)
                System.out.print(resultSetMetadata.getColumnLabel(i+1)+ "\t");
            System.out.print("\n");

            //printing Result set
            while(resultSet.next()) {
                for(int i=0; i< resultSetMetadata.getColumnCount();i++)
                {

                    System.out.print(resultSet.getString(resultSetMetadata.getColumnLabel(i+1))+ "\t");
                }
                System.out.println("\n"); numberOfRows++;
            }
            System.out.println("Total rows returned: " + numberOfRows);
            this.rowCount=numberOfRows;
            return this.resultSet;
        }catch(SQLException e){
            System.out.println("Exception in SqlClient.getResultSet");
            return null;
        }
    }

    public void close(){
        try {
            statement.close();
            resultSet.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Exception in SqlClient.close");
        }
    }

    public static int getRowCount() {
        return rowCount;
    }
}
