Feature: AdSmart Substitution Control Platform - Rules Manager Functional (Provocative) Test Cases
  As a System Integrator
  I can send set of Targeted Substitution Advertising (TSA) Slots that have been scheduled along with
  the "Hints", Clash rules

  @TestSetup
  Scenario:  The xml sent to rules manager is missing inativityTime tag (optional inativityTime tag - not included)
	Given "RMGR" application is running on server IP "172.22.20.115"
	And "ASSG" application is running on server IP "172.22.20.115"
	And "ORACLE" application is running on server IP "172.22.20.108", having DB SID "ASSCM1S" and port "1525"
	And add 10 campaigns of duration 30 seconds to campaignList, from campaigns downloaded on STB IP "172.22.19.175"
	And add 1 campaigns of duration 30 seconds to campaignList, where starting campaignId is 1
	And add campaign of duration 30 seconds to campaignList, from below table:
	  | CampaignId | ClashGroup  | ClashCount  |
	  | 81300      | 1           | 1           |
	And add campaign of duration 30 seconds to campaignList, from below table:
	  | CampaignId | ClashGroup  | ClashCount  |
	  | 99300002      | 4           | 3           |
	And end of scenario

  @Provocative
  Scenario:  The xml sent to rules manager to simulate Max Hint Size breached (Hint Max Size > 4K - Error in ASSG)
	When clear all campaigns from campaignList
	And add 240 campaigns of duration 30 seconds to campaignList, where starting campaignId is 500 and clashDataPerCampaign=4  maxClashGroup=25 and maxClashCount=10
	When substitute campaignList to next 10 events on service "SKD" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 1800 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 3600 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated_MAXHINT.xml"
	And post xml "c:\cucumberGenrated_MAXHINT.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Provocative
  Scenario:  Create Hints for non AdSmart Channel (Channel is not AdSmart enable )
	When substitute campaignList to next 1 events on service "BAD" to create campaignList-events map
	And use campaignList-events map to build "RMGR" XML
	And post xml to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And end of scenario

  @Provocative
  Scenario:  Create Hints for schedule in the past (Hints for schedule in past )
	When substitute campaignList to past 1 events on service "MAH" to create campaignList-events map
	And use campaignList-events map to build "RMGR" XML "c:\pastEvent.xml"
	And post xml "c:\pastEvent.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And end of scenario


  @Provocative
  Scenario:  The xml sent to rules manager is incomplete (Malformed xml)
	When post xml "\src\testData\xml\rulesManager\Malformed.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And end of scenario

  @Provocative
  Scenario:  The xml sent to rules manager is outdated (schedule end Date in the past xml)
	When post xml "\src\testData\xml\rulesManager\endDateInThePast.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And end of scenario

  @Provocative
  Scenario:  Create Hints for AdSmart Channel (Max Hints breached > 255 )
	When clear all campaigns from campaignList
	And add 256 campaigns of duration 30 seconds to campaignList, where starting campaignId is 1000
	And substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And use campaignList-events map to build "RMGR" XML
	And post xml to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And end of scenario