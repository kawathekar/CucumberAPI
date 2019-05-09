Feature: API Testing for Get methods.

  Scenario: Requesting a Get method without additional info than resource
    Given I am requesting "BaseURIreqres".
    When I am on "api/users?page=2" resource.
    Then I am sending the GET request and get "200" with following parameters.
      | page |
      |    2 |

  Scenario: Requesting a Get method with Token with Parent Node and position
    Given I am requesting "BaseURIgorest".
    When I go to "public-api/users/" with access token as "cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv".
    Then I am sending the GET request and get "200" with following details in parentnode "result" on position "2" for token API and multi values
      | id |
      |  4 |

  Scenario: Requesting a Get method with Token with Parent Node
    Given I am requesting "BaseURIgorest".
    When I go to "public-api/users/" with access token as "cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv".
    Then I am sending the GET request and get "200" with following details in parentnode "_meta".
      | totalCount |
      |       1612 |

  Scenario: Requesting a Get method with Token with Parent Node and Child Node
    Given I am requesting "BaseURIgorest".
    When I go to "public-api/users/" with access token as "cRkKx7qcJnr8p0cBKrsSSMFQ0L_LWRTEo_hv".
    Then I am sending the GET request and get "200" with following details in parentnode "_meta" and child node "rateLimit".
      | reset |
      |     1 |

  Scenario: Requesting a Post method with request payload
    Given I am requesting "BaseURIreqres".
    When I go to "api/login" and post the following parmeters
      | email          | password |
      | Abhishek@gmail | QA       |
    Then I am sending the POST request and get "201" with below Bearer Token.
      | token             |
      | QpwL5tke4Pnpja7X4 |
