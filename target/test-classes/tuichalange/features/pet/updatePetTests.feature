Feature: Update Pet Api Tests

  Scenario Outline: Owner pet is updated successfully with name (PUT /owners/{ownerId}/pets/{petId} is not working)
    Given user creates pets owner
    And user creates pet with owner
    And user updates pet with owner with name <name>
    When user requests pet details by id
    Then pet with owner details are correct
    Examples:
      | name                          |
      |                               |
      | A                             |
      | John                          |
      | ThirtyCharsTestNametesttestte |
      | test_*()&^%$#@!?/             |

  Scenario Outline: Pet is updated successfully
    Given user creates pets owner
    And user creates pet with owner
    And user updates pet with name <name> and new birthdate
    When user requests pet details by id
    Then pet with owner details are correct
    Examples:
      | name                          |
      |                               |
      | A                             |
      | John                          |
      | ThirtyCharsTestNametesttestte |
      | test_*()&^%$#@!?/             |

  Scenario: Pet is not updated with null name
    Given user creates pets owner
    And user creates pet with owner
    When user updates pet without name
    Then response header contains error must not be null

  Scenario: Pet is not updated with null birth date
    Given user creates pets owner
    And user creates pet with owner
    When user updates pet without birthdate
    Then response header contains error must not be null

  Scenario: Pet is not updated with null pet type
    Given user creates pets owner
    And user creates pet with owner
    When user updates pet without pet type
    Then response header contains error must not be null