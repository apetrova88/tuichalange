Feature: Update Pet Types Api

  Scenario Outline: Pet type is updated successfully
    Given user creates pet type with name <name1>
    When user update pet type with name <name2>
    Then updated pet type is present in pet types list
    Examples:
      | name1                                                                            | name2                                                                            |
      | testPet1                                                                         | testPet2                                                                         |
      | P                                                                                | T                                                                                |
      | 123                                                                              | 1234                                                                             |
      | testPet_!$%@/123                                                                 | testPet_!$%@/124                                                                 |
      | testnamewith80charstestnamewith80charstestnamewith80charstestnamewith80charstest | testnamewith80charstestnamewith80charstestnamewith80charstestnamewith80charsteOO |

  Scenario Outline: Pet type is not updated with invalid request
    Given user creates pet type with name <name1>
    When user update pet type with invalid name <name2>
    Then response header contains error <error>
    And pet type remains the same in pet types list
    Examples:
      | name1   | name2                                                                             | error                         |
      | testPet |                                                                                   | size must be between 1 and 80 |
      | testPet | null                                                                              | must not be null              |
      | testPet | morethan80charsmorethan80charsmorethan80charsmorethan80charsmorethan80charsmoreth | size must be between 1 and 80 |

  Scenario: Pet type not found error in case updating nonexistent pet type
    When nonexistent pet type is updated
    Then 404 status code is returned
