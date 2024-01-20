Feature: Get Pet Types Details Api

  Scenario: Pet type can be requested by id
    Given user creates pet type with name testPetType
    When pet type is requested
    Then correct pet type is returned

  Scenario: Pet type not found error in case requesting nonexistent pet type
    When nonexistent pet type is requested
    Then 404 status code is returned
