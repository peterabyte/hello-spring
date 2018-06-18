Feature: Topics features

  Scenario: User visits topics page
    Given logged in user
    And user visits topics page

  Scenario: User creates new topic
    Given logged in user
    And user visits topics page
    When user clicks 'newTopic' link
    And user enters 'The Topic' into 'name' input
    And user enters 'This is a topic' into 'description' input
    And user clicks 'submitTopic' button
    Then page title should be 'Hello Spring Boot - The Topic'

  Scenario: User creates new comment in topic
    Given logged in user
    And user visits topics page
    When user clicks 'newTopic' link
    And user enters 'The Topic' into 'name' input
    And user enters 'This is a topic' into 'description' input
    And user clicks 'submitTopic' button
    And user enters 'This is a comment' into 'comment' input
    And user clicks 'submitComment' button
    Then topic page should contain '1' comments
