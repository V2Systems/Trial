package com.bskyb.inttest.adsmart.cucumber.glue.stepdefinitations;

import com.bskyb.inttest.adsmart.customhttp.CustomHttpClient;
import com.bskyb.inttest.adsmart.customssh.SshClient;
import com.bskyb.inttest.adsmart.utils.Methods;
import com.bskyb.inttest.adsmart.xmlbuilder.AvailIdCampaignIdMap;
import com.bskyb.inttest.adsmart.xmlbuilder.Campaign;
import com.bskyb.inttest.adsmart.xmlbuilder.RulesMgrXmlBuilder;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by I&T Lab User on 16/12/2014.
 */
public class RulesManagerGlue {

    //Global Variables
    static List<Campaign> listCampaigns;
    static String eventId;
    static int numberOfEvent=0;
    static AvailIdCampaignIdMap availIdCampaignIdMap;
    static List<AvailIdCampaignIdMap> listAvailIdCampaignIdMap;
    static RulesMgrXmlBuilder rulesMgrXmlBuilder;
    static int primaryDeviceInactivity=-1, secondaryDeviceInactivity=-1;
    static String xmlDataFile = "c:\\rmgr_cucumber_generated.xml";
    static String postUrl = "http://172.22.20.115:6101/SCTE118RulesIngest";
    static String postUrlSuffix = ":6101/SCTE118RulesIngest";
    static String networkName = "MAH";
    static String lastHttpResponse="Not Initialised";
    static String rmgrIP = "172.22.20.115";
    static Date xmlReferenceDateTime;

    public void substituteCampaignsforEvents( int startingEventId, int numberOfEvent){
        this.numberOfEvent = numberOfEvent;
        for (int i = 0; i < numberOfEvent; i++) {
                substituteCampaignsforEvent(startingEventId + i);
            }
    }

    public void substituteCampaignsforEvent(int eventId){
        if(listCampaigns!=null) {
            this.eventId = Integer.toString(eventId);
            this.availIdCampaignIdMap = new AvailIdCampaignIdMap(this.eventId, listCampaigns);
            if (this.listAvailIdCampaignIdMap == null)
                this.listAvailIdCampaignIdMap = new ArrayList<AvailIdCampaignIdMap>();
            this.listAvailIdCampaignIdMap.add(availIdCampaignIdMap);
        }
    }
    public void setRmgrRestInterface(){
        this.postUrl= "http://"+rmgrIP+postUrlSuffix;
    }

    public void postXml(){
            CustomHttpClient c = new CustomHttpClient(postUrl);
            c.setPostFile(xmlDataFile);
            c.setResponseFile(Methods.getApplicationPath()+"\\postXmlresponse.xml"); //logging
            c.post();
            lastHttpResponse = c.getLastStatus();
            Methods.printConditional(lastHttpResponse);
    }

    public void checkHttpResponse(int httpResponseCode){
        switch(httpResponseCode) {
            case 204:
                assertEquals("HTTP/1.1 204 No Content", lastHttpResponse);
                break;
            case 200:
                assertEquals("HTTP/1.1 204 No Content", lastHttpResponse);
                break;
            case 400:
                assertEquals("HTTP/1.1 400 Bad Request", lastHttpResponse);
                break;
            default:
                assertEquals("Not Defined response Code", lastHttpResponse);
        }
    }

    public void addCampaigns(int duration, List<List<String>> table){
        if(listCampaigns==null)
            listCampaigns = new ArrayList<Campaign>();
        for(int i=1; i<table.size();i++) {
            List<String> row = table.get(i);
            Campaign c=null;

            //if greater than 1 & valid pair of clashGroup clashCount in the datatable
            if(row.size() > 1 && (row.size() % 2)==1) {
                int[][] clashGroup_clashCount = new int[(int)Math.floor(row.size()/2)][2];
                int k=0;
               for(int j=1; j<row.size();)
                {
                    clashGroup_clashCount[k][0]=Integer.parseInt(row.get(j++));
                    clashGroup_clashCount[k][1]=Integer.parseInt(row.get(j++));
                    k++;
                }
                c = new Campaign(row.get(0), duration, clashGroup_clashCount);
            }
            if(row.size() == 1) {
                c = new Campaign(row.get(0), duration);
            }

            this.listCampaigns.add(c);
        }
    }

    public void removeAllCampaigns(){
        listCampaigns=null;
    }

    public void setInactivityPeriod(String deviceType, int inactivityTime){
        if(deviceType.equalsIgnoreCase("primaryDevice"))
            this.primaryDeviceInactivity=inactivityTime;
        if(deviceType.equalsIgnoreCase("secondaryDevice"))
            this.secondaryDeviceInactivity=inactivityTime;
    }

    public void generateXML(){
        if(listAvailIdCampaignIdMap!=null) {
            rulesMgrXmlBuilder = new RulesMgrXmlBuilder(this.listAvailIdCampaignIdMap);
            rulesMgrXmlBuilder.setNetworkName(networkName);
            if(primaryDeviceInactivity!=-1 && secondaryDeviceInactivity!=-1)
            {
                rulesMgrXmlBuilder.setPrimaryDeviceInactivity(primaryDeviceInactivity);
                rulesMgrXmlBuilder.setSecondaryDeviceInactivity(secondaryDeviceInactivity);
            }

        }
        if(rulesMgrXmlBuilder!=null) {
            if(xmlReferenceDateTime!=null)
                rulesMgrXmlBuilder.createXML(xmlDataFile, xmlReferenceDateTime);
            else
                rulesMgrXmlBuilder.createXML(xmlDataFile);
        }
    }

    public void dbCheckInsertedRows_AVAIL(OracleGlue oracleGlue){
        String queryString = "select * from rulesmanager.AVAIL t where t.NETWORKNAME='"+this.networkName+"'" + " AND AVAILSTART >='" + rulesMgrXmlBuilder.getAvailStartValue().toString()+"'";
        assertEquals(numberOfEvent,oracleGlue.executeCountRecordQuery(queryString) );

    }

    public static void setNetworkName(String networkName) {
        RulesManagerGlue.networkName = networkName;
    }

    public static void setRmgrIP(String rmgrIP) {
        RulesManagerGlue.rmgrIP = rmgrIP;
    }

    public static void setXmlDataFile(String xmlDataFile) {
        if(!xmlDataFile.contains(":\\"))
            RulesManagerGlue.xmlDataFile = Methods.getApplicationPath()+ xmlDataFile;
        else
            RulesManagerGlue.xmlDataFile = xmlDataFile;
    }

    public void cleanup(){
        listAvailIdCampaignIdMap=null;
        rulesMgrXmlBuilder=null;
        xmlDataFile = "c:\\rmgr_cucumber_generated.xml";
        numberOfEvent=0;
        xmlReferenceDateTime = Methods.getDateTime(Methods.getLongDateTime());   //Setting reference back to currentDateTime
        //Setting both inactivityPeriod to -9999 will not insert the tag in generated xml
        primaryDeviceInactivity=-1;
        secondaryDeviceInactivity=-1;
    }
}
