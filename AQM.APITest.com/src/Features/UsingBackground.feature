@Test
Feature: Using the Backgroung in Feature files

  Background: 
    Given I am requesting "BaseURIgorest".

  Scenario: Requesting a Get method with Token with Parent Node and position
    When I go to "public-api/users/" with access token as "cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv".
    Then I am sending the GET request and get "200" with following details in parentnode "result" on position "2" for token API and multi values
      | id |
      |  3 |

  Scenario: Requesting a Get method with Token with Parent Node
    When I go to "public-api/users/" with access token as "cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv".
    Then I am sending the GET request and get "200" with following details in parentnode "_meta".
      | totalCount |
      |       2133 |
