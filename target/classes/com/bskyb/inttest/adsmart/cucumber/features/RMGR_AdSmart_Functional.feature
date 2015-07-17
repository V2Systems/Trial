Feature: AdSmart Substitution Control Platform - Rules Manager Functional Test Cases
  As a System Integrator
  I can send set of Targeted Substitution Advertising (TSA) Slots that have been scheduled along with
  the "Hints", Clash rules

  @basic_p
  Scenario:  The xml sent to rules manager is missing inativityTime tag (optional inativityTime tag - not included)
	Given "RMGR" application is running on server IP "172.22.20.115"
	And "ASSG" application is running on server IP "172.22.20.115"
	And "ORACLE" application is running on server IP "172.22.20.108", having DB SID "ASSCM1S" and port "1525"
	And add 10 campaigns of duration 30 seconds to campaignList, from campaigns downloaded on STB IP "172.22.19.174"
	And add 200 campaigns of duration 30 seconds to campaignList, where starting campaignId is 1
	And add campaign of duration 30 seconds to campaignList, from below table:
	  | CampaignId | ClashGroup  | ClashCount  |
	  | 81300      | 1           | 1           |
	And add campaign of duration 30 seconds to campaignList, from below table:
	  | CampaignId | ClashGroup  | ClashCount  |
	  | 99300002      | 4           | 3           |
	When substitute campaignList to next 10 events on service "SKD" to create campaignList-events map
	And use campaignList-events map to build "RMGR" XML "c:\abc.xml"
	And post xml "c:\abc.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @basic
  Scenario:  Create Hints for SD & HD Channel (SD & HD Channel)
	When substitute campaignList to next 1 events on service "SKD" to create campaignList-events map
	And use campaignList-events map to build "RMGR_SKD" XML
	And post xml to "RMGR_SKD" application
	Then receive http response code 204 from application "RMGR"
	And substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And use campaignList-events map to build "RMGR_MAH" XML
	And post xml to "RMGR_MAH" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @basic
  Scenario:  Create Hints for schedule in the past (Hints for schedule in past )
	When substitute campaignList to past 1 events on service "MAH" to create campaignList-events map
	And use campaignList-events map to build "RMGR" XML "c:\pastEvent.xml"
	And post xml "c:\pastEvent.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And end of scenario

