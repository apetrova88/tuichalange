Feature: Create Owner Api Tests

  @Smoke
  Scenario Outline: New owner is created successfully with first name
    Given list of owners
    When user creates owner with firstName <firstName>
    Then new owner is present in owners list
    Examples:
      | firstName                     |
      | A                             |
      | John                          |
      | ThirtyCharsTestNametesttestte |

  Scenario Outline: New owner is not created in case incorrect first name format
    Given list of owners
    When user creates owner with incorrect firstName <firstName>
    Then response header contains error <error>
    And new owner is not created
    Examples:
      | firstName                       | error                         |
      |                                 | size must be between 1 and 30 |
      | null                            | must not be null              |
      | ThirtyOneCharsTestNametesttesst | size must be between 1 and 30 |
      | test 1/2 * ()                   | must match                    |

  Scenario Outline: New owner is created successfully with last name
    Given list of owners
    When user creates owner with lastName <lastName>
    Then new owner is present in owners list
    Examples:
      | lastName                      |
      | B                             |
      | Dou                           |
      | ThirtyCharsTestNametesttestte |

  Scenario Outline: New owner is not created in case incorrect last name format
    Given list of owners
    When user creates owner with incorrect lastName <lastName>
    Then response header contains error <error>
    And new owner is not created
    Examples:
      | lastName                        | error                         |
      |                                 | size must be between 1 and 30 |
      | null                            | must not be null              |
      | ThirtyOneCharsTestNametesttesst | size must be between 1 and 30 |
      | test 1/2 * ()                   | must match                    |

  Scenario Outline: New owner is created successfully with address format
    Given list of owners
    When user creates owner with address <address>
    Then new owner is present in owners list
    Examples:
      | address                                                                                                                                                                                                                                                        |
      | A                                                                                                                                                                                                                                                              |
      | 101 B. Down St, 1/2 &*%$#@                                                                                                                                                                                                                                     |
      | twoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHun |

  Scenario Outline: New owner is not created in case incorrect address
    Given list of owners
    When user creates owner with incorrect address <address>
    Then response header contains error <error>
    And new owner is not created
    Examples:
      | address                                                                                                                                                                                                                                                          | error                          |
      |                                                                                                                                                                                                                                                                  | size must be between 1 and 255 |
      | null                                                                                                                                                                                                                                                             | must not be null               |
      | twoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftyS | size must be between 1 and 255 |

  Scenario Outline: New owner is created successfully with city
    Given list of owners
    When user creates owner with city <city>
    Then new owner is present in owners list
    Examples:
      | city                                                                            |
      | A                                                                               |
      | Milton-Freewater (Oregon)                                                       |
      | test 123 / &*%$#@                                                               |
      | EightyCharsTestCityEightyCharsTestCityEightyCharsTestCityEightyCharsTestCityyyy |

  Scenario Outline: New owner is not created in case incorrect city format
    Given list of owners
    When user creates owner with incorrect city <city>
    Then response header contains error <error>
    And new owner is not created
    Examples:
      | city                                                                              | error                         |
      |                                                                                   | size must be between 1 and 80 |
      | null                                                                              | must not be null              |
      | EightyOneCharsTestCityEightyOneCharsTestCityEightyOneCharsTestCityEightyOneCharsT | size must be between 1 and 80 |

  Scenario Outline: New owner is created successfully with telephone
    Given list of owners
    When user creates owner with telephone <telephone>
    Then new owner is present in owners list
    Examples:
      | telephone            |
      | 0                    |
      | 01234567890123456789 |
      #bug - test failed due to error - 'numeric value out of bounds (<10 digits>.<0 digits> expected)', but 20 chars should be possible

  Scenario Outline: New owner is not created in case incorrect telephone format
    Given list of owners
    When user creates owner with incorrect telephone <telephone>
    Then response header contains error <error>
    And new owner is not created
    Examples:
      | telephone             | error                         |
      |                       | size must be between 1 and 20 |
      | null                  | must not be null              |
      | 012345678901234567890 | size must be between 1 and 20 |
      | test                  | must match                    |
      | 0 845                 | must match                    |