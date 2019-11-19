@Hotel @Positive
Feature: Search in the hotel tab

  Scenario Outline: Check if request is send with given parameters
    Given user opens main page
    Then user selects destination to <destination>
    And user selects the <number> dates
    And user enters <adults> adults and <kids> kids
    When  user sends the search request
    Then there should be at least on item in <destination>

    Examples:
      | destination | adults | kids |number|
      | Moscow      | 1      | 0    |1     |
      | Prague      | 2      | 1    |2     |
      | Paris       | 4      | 6    |3     |
      | Madrid      | 1      | 0    |4     |