package com.bskyb.inttest.adsmart.customssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.bskyb.inttest.adsmart.utils.Methods;

import java.io.*;

/**
 * Created by I&T Lab User on 15/12/2014.
 */
public abstract class SshClient {
    static Connection connection;
    static String hostname=null;
    static int port=-1;
    static String username=null;
    static String password=null;
    static Session session = null;
    static String lastCommandStatus;
    static String lastCommandOutput;
    static boolean isAuthenticated=false;

    public static int connect(){
        if(hostname!=null && port!=-1 && username!=null && password !=null )
            return connect(hostname,port,username,password);

        return -1;
    }
    public static int connect (String hostname, int port, String username, String password) {
        try{
            SshClient.port=port;
            SshClient.hostname=hostname;
            SshClient.username=username;
            SshClient.password=password;

            connection = new Connection(hostname,SshClient.port);
            connection.connect();
            isAuthenticated = connection.authenticateWithPassword(username, password);

            if (!isAuthenticated)
                throw new IOException("authenticateWithPassword failed.");
            Methods.printConditional("connected to server " + hostname);

           return 0;
        }catch(Exception e){
            Methods.printConditional("Exception in SshClient.connect: " + e.toString());
            return 1; //Exception
        }

    }

    public static int connect(String hostname, int port, String username, String keyfilePath, String keyfilePass){
        try{
            File keyfile = new File(keyfilePath); // or "~/.ssh/id_dsa"
            connection = new Connection(hostname, port);
            //connection.connect;
            connection.connect(null,10000,10000);
            isAuthenticated = connection.authenticateWithPublicKey(username, keyfile, keyfilePass);
           if (!isAuthenticated)
            {
                Methods.printConditional("authenticateWithPublicKey failed.");
                Methods.printConditional(connection.getRemainingAuthMethods(username).clone()[0]);
            }

            return 0;
        }catch(Exception e){
            Methods.printConditional("Exception in SshClient.connect: " + e.toString());
            return 1; //Exception
        }

    }

    public static String executeCommand(String command) {
        return SshClient.executeCommand(command, false);
    }

    public static String executeCommand(String command, boolean verbose) {
        try{
            /* Create a session */
            if(isAuthenticated) {
                session = connection.openSession();
                command.trim();

                if (command != "") {
                    StringBuilder completeString = new StringBuilder();
                    session.execCommand(command);
                    Thread.sleep(2000);
                    InputStream stdout = new StreamGobbler(session.getStdout());
                    BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                    String line;
                    while ((line = br.readLine()) != null) {
                        completeString.append("\n"+line);
                    }

                    Methods.printConditional(command, verbose);
                    lastCommandOutput = completeString.toString();
                    Methods.printConditional(lastCommandOutput, verbose);
                    lastCommandStatus = session.getExitStatus().toString();
                    Methods.printConditional("exit code(" + lastCommandStatus + ")", verbose);
                    SshClient.close();
                    return lastCommandOutput;
                }
            }
            else
                Methods.printConditional("Not connected to any server...! Please use method SshClient.connect()");

        }catch(Exception e){
            SshClient.close();
            return ("Exception in SshClient.executeCommand: " + e);

        }
        SshClient.close();
        return "No command to execute";
    }

    public static String getLastCommandOutput() {
        return lastCommandOutput;
    }

    public static String getLastCommandStatus() {
        return SshClient.lastCommandStatus;
    }

    public static int close() {
        try{
            if(session!=null)
                session.close();
            return 0;
        }catch(Exception  e){
            Methods.printConditional("Exception in SshClient.close: " + e.toString());
            return 1;
        }
    }

    public static void setHostname(String hostname) {
        SshClient.hostname = hostname; isAuthenticated=false;
    }

    public static void setUsername(String username) {
        SshClient.username = username; isAuthenticated=false;
    }

    public static void setPort(int port) {
        SshClient.port = port; isAuthenticated=false;
    }

    public static void setPassword(String password) {
        SshClient.password = password; isAuthenticated=false;
    }
}
