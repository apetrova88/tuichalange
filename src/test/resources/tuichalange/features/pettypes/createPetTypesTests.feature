Feature: Create Pet Types Api

  @Smoke
  Scenario Outline: New pet type is present in pet type list
    Given list of pet types
    When user creates pet type with name <name>
    Then new pet type is present in pet types list
    Examples:
      | name                                                                             |
      | P                                                                                |
      | testPet                                                                          |
      | 123                                                                              |
      | testPet_!$%@/123                                                                 |
      | testnamewith80charstestnamewith80charstestnamewith80charstestnamewith80charstest |

  Scenario Outline: Pet type is not created with invalid request
    Given list of pet types
    When user creates pet type with invalid name <name>
    Then response header contains error <error>
    And new pet type is not created
    Examples:
      | name                                                                              | error                         |
      |                                                                                   | size must be between 1 and 80 |
      | null                                                                              | must not be null              |
      | morethan80charsmorethan80charsmorethan80charsmorethan80charsmorethan80charsmoreth | size must be between 1 and 80 |