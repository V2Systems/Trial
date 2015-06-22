$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("StandaloneTest.feature");
formatter.feature({
  "id": "standalone",
  "description": "As a Cucumber Developer\r\nThis is used to test individual Scenario",
  "name": "Standalone",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "standalone;the-xml-sent-to-rules-manager-is-missing-inativitytime-tag-(optional-inativitytime-tag---not-included)",
  "tags": [
    {
      "name": "@standaloneTest",
      "line": 5
    }
  ],
  "description": "",
  "name": "The xml sent to rules manager is missing inativityTime tag (optional inativityTime tag - not included)",
  "keyword": "Scenario",
  "line": 6,
  "type": "scenario"
});
formatter.step({
  "name": "\"RMGR\" application is running on server IP \"172.22.20.115\"",
  "keyword": "Given ",
  "line": 7
});
formatter.step({
  "name": "\"ASSG\" application is running on server IP \"172.22.20.115\"",
  "keyword": "And ",
  "line": 8
});
formatter.step({
  "name": "\"ORACLE\" application is running on server IP \"172.22.20.108\", having DB SID \"ASSCM1S\" and port \"1525\"",
  "keyword": "And ",
  "line": 9
});
formatter.step({
  "name": "add 2 campaigns of duration 30 seconds to campaignList, from campaigns downloaded on STB IP \"172.22.19.171\"",
  "keyword": "And ",
  "line": 10
});
formatter.step({
  "name": "add campaign of duration 30 seconds to campaignList, from below table:",
  "keyword": "And ",
  "line": 11,
  "rows": [
    {
      "cells": [
        "CampaignId",
        "ClashGroup",
        "ClashCount"
      ],
      "line": 12
    },
    {
      "cells": [
        "81300",
        "1",
        "1"
      ],
      "line": 13
    }
  ]
});
formatter.step({
  "name": "add campaign of duration 30 seconds to campaignList, from below table:",
  "keyword": "And ",
  "line": 14,
  "rows": [
    {
      "cells": [
        "CampaignId"
      ],
      "line": 15
    },
    {
      "cells": [
        "99300002"
      ],
      "line": 16
    },
    {
      "cells": [
        "99300003"
      ],
      "line": 17
    }
  ]
});
formatter.step({
  "name": "substitute campaignList to next 10 events starting eventId 1000 on service \"MAH\" to create campaignList-events map",
  "keyword": "When ",
  "line": 18
});
formatter.step({
  "name": "set inactivityTime tag attribute \"primaryDevice\" as 3600 seconds",
  "keyword": "And ",
  "line": 19
});
formatter.step({
  "name": "set inactivityTime tag attribute \"secondaryDevice\" as 3600 seconds",
  "keyword": "And ",
  "line": 20
});
formatter.step({
  "name": "use campaignList-events map to build \"RMGR\" XML \"c:\\abc.xml\"",
  "keyword": "And ",
  "line": 21
});
formatter.step({
  "name": "post xml \"c:\\abc.xml\" to \"RMGR\" application",
  "keyword": "And ",
  "line": 22
});
formatter.step({
  "name": "receive http response code 204 from application \"RMGR\"",
  "keyword": "Then ",
  "line": 23
});
formatter.step({
  "name": "records inserted in \"RMGR\" DB table \"AVAIL\"",
  "keyword": "And ",
  "line": 24
});
formatter.step({
  "name": "end of scenario",
  "keyword": "And ",
  "line": 25
});
formatter.match({
  "arguments": [
    {
      "val": "RMGR",
      "offset": 1
    },
    {
      "val": "172.22.20.115",
      "offset": 44
    }
  ],
  "location": "WrapperDictionary.application_is_running_on_server_IP(String,String)"
});
formatter.result({
  "duration": 81557701,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ASSG",
      "offset": 1
    },
    {
      "val": "172.22.20.115",
      "offset": 44
    }
  ],
  "location": "WrapperDictionary.application_is_running_on_server_IP(String,String)"
});
formatter.result({
  "duration": 46651,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ORACLE",
      "offset": 1
    },
    {
      "val": "172.22.20.108",
      "offset": 46
    },
    {
      "val": "ASSCM1S",
      "offset": 77
    },
    {
      "val": "1525",
      "offset": 96
    }
  ],
  "location": "WrapperDictionary.application_is_running_on_server_IP_having_DB_SID_and_port(String,String,String,String)"
});
formatter.result({
  "duration": 90502,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 4
    },
    {
      "val": "30",
      "offset": 28
    },
    {
      "val": "172.22.19.171",
      "offset": 93
    }
  ],
  "location": "WrapperDictionary.add_campaigns_of_duration_seconds_to_campaignList_from_campaigns_downloaded_on_STB_IP(int,int,String)"
});
formatter.result({
  "duration": 1088940653,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30",
      "offset": 25
    }
  ],
  "location": "WrapperDictionary.add_campaign_of_duration_seconds_to_campaignList_from_below_table(int,DataTable)"
});
formatter.result({
  "duration": 742680,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30",
      "offset": 25
    }
  ],
  "location": "WrapperDictionary.add_campaign_of_duration_seconds_to_campaignList_from_below_table(int,DataTable)"
});
formatter.result({
  "duration": 78684,
  "status": "passed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "arguments": [
    {
      "val": "primaryDevice",
      "offset": 34
    },
    {
      "val": "3600",
      "offset": 52
    }
  ],
  "location": "WrapperDictionary.set_inactivityTime_tag_attribute_as_seconds(String,int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "secondaryDevice",
      "offset": 34
    },
    {
      "val": "3600",
      "offset": 54
    }
  ],
  "location": "WrapperDictionary.set_inactivityTime_tag_attribute_as_seconds(String,int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "RMGR",
      "offset": 38
    },
    {
      "val": "c:\\abc.xml",
      "offset": 49
    }
  ],
  "location": "WrapperDictionary.use_campaignList_events_map_to_build_XML(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "c:\\abc.xml",
      "offset": 10
    },
    {
      "val": "RMGR",
      "offset": 26
    }
  ],
  "location": "WrapperDictionary.post_xml_to_application(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "204",
      "offset": 27
    },
    {
      "val": "RMGR",
      "offset": 49
    }
  ],
  "location": "WrapperDictionary.receive_http_response_code_from_application(int,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "RMGR",
      "offset": 21
    },
    {
      "val": "AVAIL",
      "offset": 37
    }
  ],
  "location": "WrapperDictionary.records_inserted_in_DB_table(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "WrapperDictionary.end_of_scenario()"
});
formatter.result({
  "status": "skipped"
});
});