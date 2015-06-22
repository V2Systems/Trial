package com.bskyb.inttest.adsmart;


import com.bskyb.inttest.adsmart.customhttp.CustomHttpClient;
import com.bskyb.inttest.adsmart.customsql.SqlClient;
import com.bskyb.inttest.adsmart.customssh.SshClient;
import com.bskyb.inttest.adsmart.selenium.CustomHtmlUnitDriver;
import com.bskyb.inttest.adsmart.selenium.CustomWebDriver;
import com.bskyb.inttest.adsmart.sikuli.SikuliWebDriver;
import com.bskyb.inttest.adsmart.utils.Methods;
import com.bskyb.inttest.adsmart.windows.Winexe;
import com.bskyb.inttest.adsmart.xmlbuilder.AvailIdCampaignIdMap;
import com.bskyb.inttest.adsmart.xmlbuilder.RulesMgrXmlBuilder;
import jodd.datetime.JDateTime;
import jodd.datetime.JulianDateStamp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I&T Lab User on 04/12/2014.
 */


public class TestClass {

    public static void main(String args[]) {

        try {

/*
            String xmlDataFile = "c:\\post.xml";
            CustomHttpClient c = new CustomHttpClient("http://172.22.20.110:6150/Dynamic/Campaign/99300003");//"
            c.setPostFile(xmlDataFile);
            c.setResponseFile("C:\\response2.xml");
            c.post();
*/
//ssh Block


            //SshClient.setHostname("172.22.20.115");
            //SshClient.setPort(22);
            //SshClient.setUsername("pbu10");
            //SshClient.setPassword("Changeme_11");
            //SshClient.setHostname("172.22.20.131");
//            SshClient.connect("172.22.20.115",22,"pbu10","c:\\id_dsa","12345");
//            SshClient.executeCommand("cd /apps; ls -l ", true);
            //SshClient.connect("172.22.19.178", 23, "root", "");
            //SshClient.exeCommand("date");
            //System.out.println(SshClient.getLastCommandStatus()+ " ::" + SshClient.getLastCommandOutput());

            //SshClient.executeCommand("pwd");
            //SshClient.executeCommand("sh /home/pbu10/getlastevent.sh PIK");
            //SshClient.executeCommand("cd /apps; ls -l");

//Windows Commands
            /*
            Winexe w = new Winexe();
            w.runCommand();
            */

//Rules Manager Block

/*
            String[] campaigns = {"99999","72049","72136","14120430","14101330","76001"};

            int startAvailId = 101700757;

            List<AvailIdCampaignIdMap> AvailIdCampaignIdMap = new ArrayList<AvailIdCampaignIdMap>();
            AvailIdCampaignIdMap AvailCampMapItem;

            for(int i=0; i<1; i++) {
                AvailCampMapItem = new AvailIdCampaignIdMap(Integer.toString(startAvailId + i), campaigns);
                AvailIdCampaignIdMap.add(AvailCampMapItem);

            }

            RulesMgrXmlBuilder y = new RulesMgrXmlBuilder(AvailIdCampaignIdMap);
            y.createXML(xmlDataFile);
*/


//httpClient Block
            /*
            CustomHttpClient c = new CustomHttpClient("http://172.22.20.115:6101/SCTE118RulesIngest");//http://172.22.20.110:6150/Dynamic/Campaign"
            c.setPostFile(xmlDataFile);
            c.setResponseFile("C:\\response2.xml");
            c.post();

            //c.setUrl("http://172.22.20.110:6150/Dynamic/Campaign");
            //c.get();



            //System.setProperty("webdriver.ie.driver","C:\\Users\\I&T Lab User\\Downloads\\IEDriverServer_Win32_2.44.0");
            //System.out.println(System.getProperty("webdriver.ie.driver"));

/*
            CustomWebDriver d = new CustomWebDriver("IE");
            d.setUrl("http://172.26.170.6:15232/ndsconsole/app.html");
            d.openUrl();
            d.getPageSource();
            d.destroy();
//httpClient Block
/*
            SikuliWebDriver d = new SikuliWebDriver();
            d.setUrl("http://172.22.20.144:5015/ndsconsole/app.html");
            d.openUrl();
            d.maximize();

            //Just wait
            Thread.sleep(4000);
            d.findImageDblClickAndSendText("C:\\Sikuli\\screencaptures\\NConsole-UserName.png", "administrator");
            d.findImageAndSendText("C:\\Sikuli\\screencaptures\\NConsole-Password.png", "admin12345");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-Login.png");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-AppSelector.png");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-Services.png");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-Services-ServiceTab.png");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-Filter.png");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-FilterSelector.png");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-Filter-Id.png");
            d.findImageAndSendText("C:\\Sikuli\\screencaptures\\NConsole-FilterInput.png", "1020");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-FindNow.png");
            d.findImageAndClick("C:\\Sikuli\\screencaptures\\NConsole-Logout.png");


            Thread.sleep(1000);
            //d.getPageSource();
            d.destroy();
            //c.setUrl("http://172.22.20.110:6150/Dynamic/Campaign");
            //c.get();
*/

//SQL Block
            /*
            SqlClient sqlClient = new SqlClient();
            sqlClient.setConnectionDetails("oracle", "172.22.20.108", "1525", "ASSCM1S", "rulesmanager", "psswrd1234");
            sqlClient.getConnection();
            sqlClient.executeQuery("select * from AVAIL t");
            sqlClient.getResultSet();
            */

// Gas Triggering schedule
            int eventDuration =30;
            long longDateTime = Methods.getLongDateTime();
            JDateTime jdt = new JDateTime();
            Methods.deleteFile("c:\\mah.out");

            for(int i=0;i<8000;i++) {

                //Methods.writeToFile("MAH~" + Methods.getDateTime("ddMM",longDateTime) + Methods.getDateTime("HHmm",longDateTime) + "~" + Methods.getMJDDate(longDateTime)+"\n","c:\\mah.out");
                //Methods.writeToFile("MAH~" + Methods.getDateTime("ddMM",longDateTime) + String.format("%04d",i) + "~" + Methods.getMJDDate(longDateTime)+"\n","c:\\mah.out");
                //Methods.printConditional("MAH~" + Methods.getDateTime("ddMM", longDateTime) + String.format("%04d", i) + "~" + String.format("%.7f", jdt.getJulianDate().getModifiedJulianDate().doubleValue()) + "\n");
                jdt.setMillisecond(0);
                Methods.writeToFile("LVH~" + Methods.getDateTime("9"+"ddMM", longDateTime) + String.format("%04d", i) + "~" + String.format("%.7f", jdt.getJulianDate().getModifiedJulianDate().doubleValue()) + "\n", "c:\\mah.out");
                //Methods.printConditional(Double.toString(jdt.getJulianDate().getModifiedJulianDate().doubleValue()));
                jdt.addSecond(eventDuration);

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
