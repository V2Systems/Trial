package com.bskyb.inttest.adsmart.cucumber.glue.stepdefinitations;

import com.bskyb.inttest.adsmart.customftp.FTPServer;
import com.bskyb.inttest.adsmart.customsql.SqliteClient;
import com.bskyb.inttest.adsmart.customtelnet.TelNetClient;
import com.bskyb.inttest.adsmart.utils.Methods;
import java.io.File;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by I&T Lab User on 02/03/2015.
 */
public class STBGlue {
    static String stbIP;
    static String STBReference="";
    static String STBDBFile;
    static String rCDBfilePath = "/mnt/nds/dev_5/part_0/FSN_DATA/";
    static String rPCATfilePath = "/mnt/nds/dev_5/part_0/FSN_DATA/";
    static String lFilePath="c:\"";
    static String fileName = "CDB.DB";
    static String cmdGetFile;

    public static void setStbIP(String stbIP) {
        STBGlue.stbIP = stbIP;
    }

    public void getStbFile(String fileName){
        try{
        //Start FTP Server to receive files
        FTPServer ftpServer = new FTPServer();
        lFilePath = ftpServer.getHomeDir();
        Thread thread = new Thread(ftpServer);
        thread.start();

         //Connect to STB using telnet And send files over ftp
        TelNetClient telnetClient = new TelNetClient();
        telnetClient.connect(stbIP,23,"root","");
            //Telnet Command formation
            if(fileName.matches("CDB.DB")){
                //check if the file already exists delete before you download latest.
                File file = new File(lFilePath+STBReference+"_"+fileName);
                if(file.exists())
                    file.delete();
                //"ftpput -v -u anonymous 172.22.19.9 -P 2121 " + STBReference + "_CDB.DB" + " /mnt/nds/dev_5/part_0/FSN_DATA/CDB.DB"
                cmdGetFile = "ftpput -v -u anonymous " + ftpServer.getFTPServerIp() + " -P "+ftpServer.getFtpPort()+" "+ STBReference+"_"+fileName+" "+rCDBfilePath+fileName;
                telnetClient.sendCommand(cmdGetFile);
            }
            if(fileName.matches("PCAT.DB")){
                //"ftpput -v -u anonymous 172.22.19.9 -P 2121 " + STBReference + "_CDB.DB" + " /mnt/nds/dev_5/part_0/FSN_DATA/CDB.DB"
                cmdGetFile = "ftpput -v -u anonymous " + ftpServer.getFTPServerIp() + " -P "+ftpServer.getFtpPort()+" "+ STBReference+"_"+fileName+" "+rPCATfilePath+fileName;
                telnetClient.sendCommand(cmdGetFile);
            }

        //below command is to make sure that earlier command successfully executed and completed
        telnetClient.sendCommand("ls -l");

        telnetClient.disconnect();
        ftpServer.stop();

        }catch(Exception e){
            Methods.printConditional("Exception in STBGlue.getCDB :- " + e);
            }

    }

    public ResultSet executeSqliteQuery(String STBFile, String QueryString){
        setSTBDBFile(STBFile);
        SqliteClient sqliteClient = new SqliteClient();
        sqliteClient.setConnectionDetails(STBDBFile,"sqlite");
        sqliteClient.getConnection();
        sqliteClient.executeQuery(QueryString);
        return sqliteClient.getResultSet();

    }

    public static void setSTBDBFile(String STBFile) {
        if(STBFile.matches("CDB.DB"))
        STBDBFile = lFilePath+STBReference+"_"+STBFile;
    }

    public static String getSTBDBFile() {
        return STBDBFile;
    }

    public static void cleanup(){

    }

    public List<List<String>> getSTBDownloadedCampaigns(int numberOfCampaigns, int campaignDuration, String stbIP) {
        try{
            this.setStbIP(stbIP);
            this.getStbFile("CDB.DB");
            String queryString = "select distinct CAI.CAMPAIGN_ID from INSTANCES I, CAI Where CAI.AD_INSTANCE_ID = I.AD_INSTANCE_ID AND I.AVAIL_STAT=1 LIMIT " + numberOfCampaigns;
        //select distinct CAI.CAMPAIGN_ID from INSTANCES I, CAI Where CAI.AD_INSTANCE_ID = I.AD_INSTANCE_ID AND I.AVAIL_STAT=1 LIMIT 10
            return Methods.resultResetToListOfList(executeSqliteQuery("CDB.DB", queryString));

        }catch(Exception e){
            Methods.printConditional("Exception in STBGlue.getSTBDownloadedCampaigns: " + e);
        }
        return null;
    }

}
