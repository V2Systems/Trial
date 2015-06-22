Feature: Get campaigns data from STB
  As a BSS user
  I want to know which campaigns were download on STB

  Scenario: Fetch all campaign data from STB
    Given STB has IP "172.22.19.176" and refered as "STB1"
    And I Downlod the "CDB.DB" File from STB
    And I Downlod the "PCAT.DB" File from STB
    And STB has downloaded Campaigns
    And verify Database query result as below:
      | SQL_Query                  |Record_Count|
    Then end of scenario