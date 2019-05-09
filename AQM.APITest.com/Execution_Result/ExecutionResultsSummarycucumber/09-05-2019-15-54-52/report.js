$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("UsingBackground.feature");
formatter.feature({
  "line": 2,
  "name": "Using the Backgroung in Feature files",
  "description": "",
  "id": "using-the-backgroung-in-feature-files",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Test"
    }
  ]
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "I am requesting \"BaseURIgorest\".",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "BaseURIgorest",
      "offset": 17
    }
  ],
  "location": "StepDefinition.i_am_requesting(String)"
});
formatter.result({
  "duration": 91817256,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Requesting a Get method with Token with Parent Node and position",
  "description": "",
  "id": "using-the-backgroung-in-feature-files;requesting-a-get-method-with-token-with-parent-node-and-position",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "I go to \"public-api/users/\" with access token as \"cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv\".",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I am sending the GET request and get \"200\" with following details in parentnode \"result\" on position \"2\" for token API and multi values",
  "rows": [
    {
      "cells": [
        "id"
      ],
      "line": 10
    },
    {
      "cells": [
        "3"
      ],
      "line": 11
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "public-api/users/",
      "offset": 9
    },
    {
      "val": "cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv",
      "offset": 50
    }
  ],
  "location": "StepDefinition.i_go_to_with_access_token_as(String,String)"
});
formatter.result({
  "duration": 552450,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 38
    },
    {
      "val": "result",
      "offset": 81
    },
    {
      "val": "2",
      "offset": 102
    }
  ],
  "location": "StepDefinition.i_am_sending_the_GET_request_and_get_with_following_details_in_parentnode_on_position_for_token_API_and_multi_values(String,String,String,DataTable)"
});
formatter.result({
  "duration": 2366900262,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "I am requesting \"BaseURIgorest\".",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "BaseURIgorest",
      "offset": 17
    }
  ],
  "location": "StepDefinition.i_am_requesting(String)"
});
formatter.result({
  "duration": 618667,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Requesting a Get method with Token with Parent Node",
  "description": "",
  "id": "using-the-backgroung-in-feature-files;requesting-a-get-method-with-token-with-parent-node",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 14,
  "name": "I go to \"public-api/users/\" with access token as \"cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv\".",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "I am sending the GET request and get \"200\" with following details in parentnode \"_meta\".",
  "rows": [
    {
      "cells": [
        "totalCount"
      ],
      "line": 16
    },
    {
      "cells": [
        "2128"
      ],
      "line": 17
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "public-api/users/",
      "offset": 9
    },
    {
      "val": "cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv",
      "offset": 50
    }
  ],
  "location": "StepDefinition.i_go_to_with_access_token_as(String,String)"
});
formatter.result({
  "duration": 738354,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 38
    },
    {
      "val": "_meta",
      "offset": 81
    }
  ],
  "location": "StepDefinition.i_am_sending_the_GET_request_and_get_with_following_details_in_parentnode(int,String,DataTable)"
});
formatter.result({
  "duration": 559315793,
  "error_message": "java.lang.AssertionError: expected [2133] but found [2128]\r\n\tat org.testng.Assert.fail(Assert.java:94)\r\n\tat org.testng.Assert.failNotEquals(Assert.java:513)\r\n\tat org.testng.Assert.assertEqualsImpl(Assert.java:135)\r\n\tat org.testng.Assert.assertEquals(Assert.java:116)\r\n\tat org.testng.Assert.assertEquals(Assert.java:190)\r\n\tat org.testng.Assert.assertEquals(Assert.java:200)\r\n\tat Main.StepDefinition.i_am_sending_the_GET_request_and_get_with_following_details_in_parentnode(StepDefinition.java:193)\r\n\tat ✽.Then I am sending the GET request and get \"200\" with following details in parentnode \"_meta\".(UsingBackground.feature:15)\r\n",
  "status": "failed"
});
});