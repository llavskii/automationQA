Feature: Automationpractice

  Scenario: Searching for women T-shirts
    Given I am on main page of automationpractice
    When I move mouse to button Women and check it's dimension
    And I move and click mouse to T-Shirts in list under button Women
    Then I see "T-shirts" for women

  Scenario: Searching for women T-shirts (Example of broken test)
    Given I am on main page of automationpractice
    When I move mouse to button Women and check it's dimension
    And I move and click mouse to T-Shirts in list under button Women
    Then I see "Jackets" for women
