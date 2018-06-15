Feature: Simple feature tests

  Scenario: Visit login page
    Given user visits login page

  Scenario: Visit login page and successfully log in
    Given user visits login page
    When user logs in with username 'admin@example.com' and password '123456'
    Then page title should be 'Hello Spring Boot - Topics'

  Scenario: Visit login page and fail at log in
    Given user visits login page
    When user logs in with username 'not_a_user@example.com' and password '123456'
    Then user should see login error
