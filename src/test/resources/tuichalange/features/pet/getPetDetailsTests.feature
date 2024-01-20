Feature: Get Pet Details Api Tests

  @Smoke
  Scenario: Owner pet details can be requested by id
    Given user creates pets owner
    And user creates pet with owner
    When user requests pet details by id
    Then pet with owner details are correct

  Scenario: Pet not found error in case requesting nonexistent pet
    When nonexistent pet is requested
    Then 404 status code is returned
