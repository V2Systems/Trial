package com.bskyb.inttest.adsmart.customtelnet;

import com.bskyb.inttest.adsmart.utils.Methods;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.*;


/**
 * Created by I&T Lab User on 02/03/2015.
 */
public class TelNetClient {
    static TelnetClient telnetClient;
    static DataInputStream dataInputStream;
    static PrintStream printStream;
    static String lastCommandStatus;
    static String lastCommandOutput;
    static String [] possiblePrompts = {"#","$"};


    static String hostname;
    static String username;
    static String password;
    static int port;

    public void connect(String hostname, int port, String username, String password){
        try{


            this.hostname=hostname;
            this.port=port;
            telnetClient = new TelnetClient();
            telnetClient.connect(hostname,port);

            dataInputStream = new DataInputStream(telnetClient.getInputStream());
            printStream = new PrintStream(telnetClient.getOutputStream());

            //Authentication
            readUntil( "login: " );
            write(username);
            readUntil(possiblePrompts);
            write(password);

        }catch(Exception e){
            Methods.printConditional("Exception in TelnetClient.connect ::" + e);
        }

    }
    public String sendCommand( String command ) {
        try {
            write(command);
            return readUntil(possiblePrompts);
        }catch(Exception e){
            Methods.printConditional("Exception in TelNetClient.sendCommand: " + e);
        }
    return null;
    }

    public void disconnect(){
        try {
            if (telnetClient != null)
                telnetClient.disconnect();
        }catch (Exception e){
            Methods.printConditional("Exception in TelNetClient.disconnect: " + e);
        }
    }

    public String readUntil( String pattern ) {
        try {
            char lastChar = pattern.charAt( pattern.length() - 1 );
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = ( char )dataInputStream.read();
            while( true ) {
                System.out.print( ch );
                sb.append( ch );
                if( ch == lastChar ) {
                    if( sb.toString().endsWith( pattern ) ) {
                        return sb.toString();
                    }
                }
                ch = ( char )dataInputStream.read();
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    public String readUntil( String [] pattern ) {
        try {

            char lastChars[];
            boolean found = false;

            lastChars = new char[pattern.length];
            for(int i=0; i<pattern.length; i++)
            {
                lastChars[i] = pattern[i].charAt(pattern[i].length()-1);
            }

            StringBuffer sb = new StringBuffer();
            char ch = ( char )dataInputStream.read();

            while( true ) {
                System.out.print( ch );
                sb.append( ch );
                for(int i=0; i<pattern.length; i++){
                    if( ch == lastChars[i] ) {
                        if( sb.toString().endsWith( pattern[i] ) ) {
                            return sb.toString();
                        }
                    }
                }
                ch = ( char )dataInputStream.read();
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    public void write( String value ) {
        try {
            printStream.println( value );
            printStream.flush();
            System.out.println( value );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
