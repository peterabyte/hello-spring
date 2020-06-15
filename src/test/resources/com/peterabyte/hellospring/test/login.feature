Feature: Login features

  Scenario: Visit login page
    Given user visits login page

  Scenario: Visit login page and successfully log in
    Given default user logs in
    Then page title should be 'Hello Spring Boot - Topics'

  Scenario Outline: Visit login page and fail at log in
    Given user visits login page
    When user logs in with username '<username>' and password '<password>'
    Then user should see login error

    Examples:
      | username               | password     |
      | not_a_user@example.com | 123456       |
      | admin@example.com      | bad_password |
