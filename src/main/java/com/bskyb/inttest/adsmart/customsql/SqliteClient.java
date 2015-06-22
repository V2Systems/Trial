package com.bskyb.inttest.adsmart.customsql;

import com.bskyb.inttest.adsmart.utils.Methods;

import java.sql.*;
import java.util.Properties;

/**
 * Created by I&T Lab User on 03/03/2015.
 */
public class SqliteClient {
    static Connection conn = null;
    static String fileName;
    static String dbms;
    static String queryString;
    static Statement statement;
    static ResultSet resultSet;
    static int rowCount=0;
    static ResultSetMetaData resultSetMetadata;

    public void setConnectionDetails(String fileName, String dbms ){
        this.fileName=fileName;
        this.dbms=dbms;

    }
    public Connection getConnection() {

        try{

            Class.forName("org.sqlite.JDBC");
            Properties connectionProps = new Properties();
            if (this.dbms.equals("sqlite")) {
                conn = DriverManager.getConnection(
                        "jdbc:" + this.dbms + ":" +
                                this.fileName, connectionProps );
            }

            Methods.printConditional("Connected to sqlLite database File: " + this.fileName);
            return conn;
        }catch(Exception e){
            System.out.println("Error While establishing sql connection" + e);
            return null;
        }
    }

    public void executeQuery(String queryString){
        try {
            this.queryString=queryString;
            statement = conn.createStatement();
            boolean result= statement.execute(queryString);
            if(result){

                this.resultSet=statement.executeQuery(queryString);
                this.resultSetMetadata=resultSet.getMetaData();
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

            executeQuery(queryString);
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
