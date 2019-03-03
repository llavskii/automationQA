Feature:
  Searching by Sputnik on https://www.sputnik.ru/

  Scenario: Using Sputnik
    Given user opened the site
    And user clicks input
    When user search for 'Виктор Пелевин'
    And user clicks Search
    Then user sees list of results
    And 'Виктор Пелевин' should be contained