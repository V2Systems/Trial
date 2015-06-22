package com.bskyb.inttest.adsmart.cucumber.glue.stepdefinitations;

import com.bskyb.inttest.adsmart.utils.Methods;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by I&T Lab User on 25/02/2015.
 */
public class WrapperDictionary {

    RulesManagerGlue rulesManagerGlue = new RulesManagerGlue();
    AssgGlue assgGlueGlue = new AssgGlue();
    OracleGlue oracleglue = new OracleGlue();
    STBGlue stbGlue = new STBGlue();

    @Given("^\"([^\"]*)\" application is running on server IP \"([^\"]*)\"$")
    public void application_is_running_on_server_IP(String appName, String serverIp) throws Throwable {
        if(appName.equalsIgnoreCase("RMGR")) {
            rulesManagerGlue.setRmgrIP(serverIp);
            rulesManagerGlue.setRmgrRestInterface();
        }
        if(appName.equalsIgnoreCase("ASSG")) {
            assgGlueGlue.setAssgIP(serverIp);
        }
    }
    @Given("^\"([^\"]*)\" application is running on server IP \"([^\"]*)\", having DB SID \"([^\"]*)\" and port \"([^\"]*)\"$")
    public void application_is_running_on_server_IP_having_DB_SID_and_port(String appName, String oracleIP, String oracleSid, String oraclePort) throws Throwable {
        oracleglue.setupConfig(oracleSid, oracleIP, oraclePort);
    }


    //Grouping

    @Given("^add (\\d+) campaigns of duration (\\d+) seconds to campaignList, from campaigns downloaded on STB IP \"([^\"]*)\"$")
    public void add_campaigns_of_duration_seconds_to_campaignList_from_campaigns_downloaded_on_STB_IP(int numberOfCampaigns, int campaignDuration, String stbIP) throws Throwable {
        assertEquals(numberOfCampaigns, stbGlue.getSTBDownloadedCampaigns(numberOfCampaigns,campaignDuration,stbIP).size()-1); //-1 to Exclude table headers
        rulesManagerGlue.addCampaigns(campaignDuration, stbGlue.getSTBDownloadedCampaigns(numberOfCampaigns,campaignDuration,stbIP));
    }

    @Given("^add campaign of duration (\\d+) seconds to campaignList, from below table:$")
    public void add_campaign_of_duration_seconds_to_campaignList_from_below_table(int duration, DataTable campaigns) throws Throwable {
        rulesManagerGlue.addCampaigns(duration, Methods.dataTableToListOfList(campaigns));
    }

    @Given("^add (\\d+) campaigns of duration (\\d+) seconds to campaignList, where starting campaignId is (\\d+)$")
    public void add_campaigns_of_duration_seconds_to_campaignList_where_starting_campaignId_is(int numberOfCampaigns, int duration, int firstCampaignId) throws Throwable {
        rulesManagerGlue.addCampaigns(duration, Methods.arrayToListOfList(Methods.generateNumericArrayList("CampaignIds",firstCampaignId, numberOfCampaigns)));
    }

    @Given("^add (\\d+) campaigns of duration (\\d+) seconds to campaignList, where starting campaignId is (\\d+), clashGroup=(\\d+) and clashCount=(\\d+)$")
    public void add_campaigns_of_duration_seconds_to_campaignList_where_starting_campaignId_is_clashGroup_and_clashCount(int numberOfCampaigns, int duration, int firstCampaignId, int clashGroup, int clashCount) throws Throwable {
        rulesManagerGlue.addCampaigns(duration, Methods.arrayToListOfList(Methods.generateNumericArrayList("CampaignIds",firstCampaignId, numberOfCampaigns),Methods.intTointArray(clashGroup),Methods.intTointArray(clashCount)));
    }
    @Given("^add (\\d+) campaigns of duration (\\d+) seconds to campaignList, where starting campaignId is (\\d+) and clashDataPerCampaign=(\\d+)  maxClashGroup=(\\d+) and maxClashCount=(\\d+)$")
    public void add_campaigns_of_duration_seconds_to_campaignList_where_starting_campaignId_is_and_clashDataPerCampaign_MaxClashGroup_and_MaxClashCount(int numberOfCampaigns, int duration, int firstCampaignId, int clashDataPerCampaign, int maxClashGroup, int maxClashCount) throws Throwable {
        rulesManagerGlue.addCampaigns(duration, Methods.arrayToListOfList(Methods.generateNumericArrayList("CampaignIds",firstCampaignId, numberOfCampaigns),Methods.intArrayGenerator(clashDataPerCampaign, maxClashGroup),Methods.intArrayGenerator(clashDataPerCampaign,maxClashCount)));
    }

    @When("^clear all campaigns from campaignList$")
    public void clear_all_campaigns_from_campaignList() throws Throwable {
        rulesManagerGlue.removeAllCampaigns();
    }


    @When("^substitute campaignList to next (\\d+) events on service \"([^\"]*)\" to create campaignList-events map$")
    public void substitute_campaignList_to_next_events_on_service_to_create_campaignList_events_map(int eventCount, String networkName) throws Throwable {
        rulesManagerGlue.networkName=networkName;
        rulesManagerGlue.substituteCampaignsforEvents(assgGlueGlue.getLastEventId(networkName, eventCount) + 1, eventCount);
    }

    @When("^substitute campaignList to past (\\d+) events on service \"([^\"]*)\" to create campaignList-events map$")
    public void substitute_campaignList_to_past_events_on_service_to_create_campaignList_events_map(int eventCount, String networkName) throws Throwable {
        rulesManagerGlue.xmlReferenceDateTime = Methods.getDateTime(Methods.getLongDateTime()-86400000); //Setting reference to currentDateTime - 1Day
        rulesManagerGlue.networkName=networkName;
        rulesManagerGlue.substituteCampaignsforEvents(assgGlueGlue.getLastEventId(networkName, eventCount) + 1, eventCount);
    }

    @When("^substitute campaignList to next (\\d+) events starting eventId (\\d+) on service \"([^\"]*)\" to create campaignList-events map$")
    public void substitute_campaignList_to_next_events_starting_eventId_on_service_to_create_campaignList_events_map(int eventCount, int startingEventId, String networkName) throws Throwable {
        rulesManagerGlue.networkName=networkName;
        rulesManagerGlue.substituteCampaignsforEvents(startingEventId + 1, eventCount);

    }

    @When("^post xml to \"([^\"]*)\" application$")
    public void post_xml_to_application(String appName) throws Throwable {
        if(appName.equalsIgnoreCase("RMGR")) {
            rulesManagerGlue.postXml();
        }
    }

    @When("^post xml \"([^\"]*)\" to \"([^\"]*)\" application$")
    public void post_xml_to_application(String xmlFileName, String appName) throws Throwable {
        if(appName.equalsIgnoreCase("RMGR")) {
            rulesManagerGlue.setXmlDataFile(xmlFileName);
            rulesManagerGlue.postXml();
        }
    }

    @Then("^receive http response code (\\d+) from application \"([^\"]*)\"$")
    public void receive_http_response_code_from_application(int responseCode, String appName) throws Throwable {
        if(appName.equalsIgnoreCase("RMGR")){
            rulesManagerGlue.checkHttpResponse(responseCode);
        }
    }

    @When("^CampaignManager has (\\d+) Seconds campaigns as below:$")
    public void CampaignManager_has_Seconds_campaigns_as_below(int duration, DataTable campaigns) throws Throwable {
        rulesManagerGlue.addCampaigns(duration, Methods.dataTableToListOfList(campaigns));
    }


    @When("^set inactivityTime tag attribute \"([^\"]*)\" as (\\d+) seconds$")
    public void set_inactivityTime_tag_attribute_as_seconds(String deviceType, int inactivityTime) throws Throwable {
        rulesManagerGlue.setInactivityPeriod(deviceType,inactivityTime);
    }

    @When("^substitute above listed campaigns for eventId (\\d+)$")
    public void substitute_above_listed_campaigns_for_eventId(int eventId) throws Throwable {
        rulesManagerGlue.substituteCampaignsforEvent(eventId);
    }


    @When("^use campaignList-events map to build \"([^\"]*)\" XML$")
    public void use_campaignList_events_map_to_build_XML(String appName) throws Throwable {
        if(appName.equalsIgnoreCase("RMGR")) {
            rulesManagerGlue.generateXML();
        }
    }

    @When("^use campaignList-events map to build \"([^\"]*)\" XML \"([^\"]*)\"$")
    public void use_campaignList_events_map_to_build_XML(String appName, String xmlFileName) throws Throwable {
        if(appName.equalsIgnoreCase("RMGR")) {
            RulesManagerGlue.setXmlDataFile(xmlFileName);
            rulesManagerGlue.generateXML();
        }
    }

    @Then("^records inserted in \"([^\"]*)\" DB table \"([^\"]*)\"$")
    public void records_inserted_in_DB_table(String appName, String TableName) throws Throwable {
        Thread.sleep(5000);
        if(appName.equalsIgnoreCase("RMGR")) {
            if(appName.equalsIgnoreCase("AVAIL")) {
                rulesManagerGlue.dbCheckInsertedRows_AVAIL(oracleglue);
            }
        }
    }

    @Then("^verify Database query result as below:$")
    public void verify_Database_query_result_as_below(DataTable dbQuery_expectedResult) throws Throwable {
        Thread.sleep(5000);
        Methods.printConditional(Methods.dataTableToListOfList(dbQuery_expectedResult).toString());

    }

    @Given("^STB has IP \"([^\"]*)\"$")
    public void STB_has_IP(String stbIP) throws Throwable {
        stbGlue.setStbIP(stbIP);
    }
    @Given("^STB has IP \"([^\"]*)\" and refered as \"([^\"]*)\"$")
    public void STB_has_IP_and_refered_as(String stbIP, String STBReference) throws Throwable {
        STBGlue.STBReference=STBReference;
        stbGlue.setStbIP(stbIP);
    }
    @Given("^I Downlod the \"([^\"]*)\" File from STB$")
    public void I_Downlod_the_CDB_File_from_STB(String fileName) throws Throwable {
        stbGlue.getStbFile(fileName);
    }
    @Given("^STB has downloaded Campaigns$")
    public void STB_has_downloaded_Campaigns() throws Throwable {
        stbGlue.executeSqliteQuery("CDB.DB","select distinct CAI.CAMPAIGN_ID from INSTANCES I, CAI Where CAI.AD_INSTANCE_ID = I.AD_INSTANCE_ID AND I.AVAIL_STAT=1");
    }
    @Then("^end of scenario$")
    public void end_of_scenario() throws Throwable {
        rulesManagerGlue.cleanup();
        STBGlue.cleanup();
        Methods.printConditional("\n*****************END OF TEST*******************\n");
    }

}
