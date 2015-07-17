Feature: Standalone
  As a Cucumber Developer
  This is used to test individual Scenario

  @standaloneTest
  Scenario:  The xml sent to rules manager is missing inativityTime tag (optional inativityTime tag - not included)
    Given "RMGR" application is running on server IP "172.22.20.115"
    And "ASSG" application is running on server IP "172.22.20.115"
    And "ORACLE" application is running on server IP "172.22.20.108", having DB SID "ASSCM1S" and port "1525"
    And add 3 campaigns of duration 30 seconds to campaignList, from campaigns downloaded on STB IP "172.22.19.171"
    And add campaign of duration 30 seconds to campaignList, from below table:
      | CampaignId |
      | 99300003    |
    When substitute campaignList to next 10 events on service "Sky Living UK" to create campaignList-events map
    And set inactivityTime tag attribute "primaryDevice" as 3600 seconds
    And set inactivityTime tag attribute "secondaryDevice" as 3600 seconds
    And use campaignList-events map to build "RMGR" XML "c:\abc.xml"
    And post xml "c:\abc.xml" to "RMGR" application
    Then receive http response code 204 from application "RMGR"
    And records inserted in "RMGR" DB table "AVAIL"
    And end of scenario