Feature: AdSmart Substitution Session Inactivity Period Capping Scenarios
  As a System Integrator
  I can send set of Targeted Substitution Advertising (TSA) Slots that have been scheduled along with
  the "Hints", Clash rules and various combination of inactivityTime & deviceType values

  @basic
  Scenario:  The xml sent to rules manager is missing inativityTime tag (optional inativityTime tag - not included)
	Given "RMGR" application is running on server IP "172.22.20.115"
	And "ASSG" application is running on server IP "172.22.20.115"
	And "ORACLE" application is running on server IP "172.22.20.108", having DB SID "ASSCM1S" and port "1525"
	And clear all campaigns from campaignList
	And add 3 campaigns of duration 30 seconds to campaignList, from campaigns downloaded on STB IP "172.22.19.175"
	And add 1 campaigns of duration 30 seconds to campaignList, where starting campaignId is 1
	And add campaign of duration 30 seconds to campaignList, from below table:
	  | CampaignId | ClashGroup  | ClashCount  |
	  | 81300      | 1           | 1           |
	And add campaign of duration 30 seconds to campaignList, from below table:
	  | CampaignId | ClashGroup  | ClashCount  |
	  | 80000      | 4           | 3           |
	When substitute campaignList to next 10 events on service "MAH" to create campaignList-events map
	And use campaignList-events map to build "RMGR" XML "c:\abc.xml"
	And post xml "c:\abc.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario


  @basic
  Scenario:  The xml sent to rules manager with inativityTime tag, attribute primaryDevice=600 & secondaryDevice = 65535 (only primaryDevice)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 600 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 65535 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated1.xml"
	And post xml "c:\cucumberGenrated1.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @basic
  Scenario:  The xml sent to rules manager with inativityTime tag, attribute primaryDevice=65535 & secondaryDevice = 2500 (only secondaryDevice)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "secondaryDevice" as 2500 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

	@Primary
  Scenario:  The xml sent to rules manager with inativityTime tag, attribute primaryDevice=10800 & secondaryDevice = 10800 (Valid primaryDevice & secondaryDevice = 10800)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 10800 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 10800 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Primary
  Scenario:  The xml sent to rules manager with inativityTime tag, Max attribute value ie. primaryDevice=65534 & secondaryDevice = 65534 (Max valid primaryDevice & secondaryDevice = 65534)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 65534 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 65534 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Primary
  Scenario:  The xml sent to rules manager with inativityTime tag, attribute primaryDevice=0 & secondaryDevice = 0 (Min valid primaryDevice & secondaryDevice = 0)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 0 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 0 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Primary
  Scenario:  The xml sent to rules manager with inativityTime tag, attribute primaryDevice=65535 & secondaryDevice = 65535 (Supress primaryDevice & secondaryDevice = 65535)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 65535 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 65535 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 204 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Provocative
  Scenario:  The xml sent to rules manager with inativityTime tag, invalid attribute value ie. primaryDevice>65535 & secondaryDevice < 65535 (Invalid primaryDevice > 65535)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 66535 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 1800 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Provocative
  Scenario:  The xml sent to rules manager with inativityTime tag, invalid attribute value ie. primaryDevice < 65535 & secondaryDevice > 65535 (Invalid secondaryDevice > 65535)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 3600 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 70000 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Provocative
  Scenario:  The xml sent to rules manager with inativityTime tag, invalid attribute value ie. primaryDevice > 65535 & secondaryDevice > 65535 (invalid both primaryDevice & secondaryDevice > 65535)
	When substitute campaignList to next 1 events on service "MAH" to create campaignList-events map
	And set inactivityTime tag attribute "primaryDevice" as 8000 seconds
	And set inactivityTime tag attribute "secondaryDevice" as 70000 seconds
	And use campaignList-events map to build "RMGR" XML "c:\cucumberGenrated2.xml"
	And post xml "c:\cucumberGenrated2.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And records inserted in "RMGR" DB table "AVAIL"
	And end of scenario

  @Provocative
  Scenario:  The xml sent to rules manager to simulate Max Hint Size breached (Hint Max Size > 4K)
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
  Scenario:  The xml sent to rules manager is incomplete (Missing attribute secondaryDevice)
	When post xml "\src\testData\xml\rulesManager\InactivityPeriod-MissingAttr_secondaryDevice.xml" to "RMGR" application
	Then receive http response code 400 from application "RMGR"
	And end of scenario
