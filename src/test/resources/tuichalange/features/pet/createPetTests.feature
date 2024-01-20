Feature: Create Pet Api Tests

  Scenario Outline: New pet is created successfully with name (POST /pets is not working)
    Given list of pets
    When user creates pet with name <name>
    Then new pet is present in pets list
    Examples:
      | name                          |
      |                               |
      | A                             |
      | Bin                           |
      | ThirtyCharsTestNametesttestte |
      | test_*()&^%$#@!?/             |

  @Smoke
  Scenario Outline: New owner pet is created successfully with name
    Given user creates pets owner
    When user creates pet with owner with name <name>
    Then new pet is present in pets list
    Examples:
      | name                          |
      |                               |
      | A                             |
      | Bin                           |
      | ThirtyCharsTestNametesttestte |
      | test_*()&^%$#@!?/             |

  Scenario: New owner pet is created successfully with birth date
    Given user creates pets owner
    When user creates pet with owner with birthdate 2022-09-09
    Then new pet is present in pets list

  Scenario: New owner pet is not created in case name is null
    Given user creates pets owner
    When user creates pet with owner without name
    Then response header contains error must not be null

  Scenario: New owner pet is not created in case birth date is null
    Given user creates pets owner
    When user creates pet with owner without birthdate
    Then response header contains error must not be null

  Scenario: New owner pet is not created in case pet type is null
    Given user creates pets owner
    When user creates pet with owner without birthdate
    Then response header contains error must not be null
