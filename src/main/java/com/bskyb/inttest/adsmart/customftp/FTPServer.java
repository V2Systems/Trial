package com.bskyb.inttest.adsmart.customftp;

import com.bskyb.inttest.adsmart.utils.Methods;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I&T Lab User on 02/03/2015.
 */
public class FTPServer implements Runnable{

    static int ftpPort= 2121;
    static String FTPServerIp = Methods.getLocalIP();
    static FtpServer server;
    static String homeDir="c:/";

    @Override
    public void run() {
        this.start();
    }

    public void start(){
        try{
            FtpServerFactory serverFactory = new FtpServerFactory();
            ListenerFactory factory = new ListenerFactory();
            ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
            connectionConfigFactory.setAnonymousLoginEnabled(true);
            serverFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());

            BaseUser user = new BaseUser();
            user.setName("anonymous");
            user.setHomeDirectory(homeDir);

            List<Authority> authorities = new ArrayList<Authority>();
            authorities.add(new WritePermission());
            user.setAuthorities(authorities);
            serverFactory.getUserManager().save(user);

            // set the port of the listener
            factory.setPort(ftpPort);
            // replace the default listener
            serverFactory.addListener("default", factory.createListener());
            // start the server
            server = serverFactory.createServer();
            server.start();

        }catch(Exception e){
            Methods.printConditional("Error in method FTPServer.start :- " + e);
        }
    }



    public void stop(){
        try{
            server.stop();
        }catch(Exception e){
            Methods.printConditional("Error in method FTPServer.stop");
        }

    }

    public static void setHomeDir(String homeDir) {
        FTPServer.homeDir = homeDir;
    }

    public static String getHomeDir() {
        return homeDir;
    }

    public static void setFtpPort(int ftpPort) {
        FTPServer.ftpPort = ftpPort;
    }

    public static String getFTPServerIp() {
        return FTPServerIp;
    }

    public static int getFtpPort() {
        return ftpPort;
    }
}
