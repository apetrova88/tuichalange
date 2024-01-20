Feature: Update Owner Api Tests

  Scenario Outline: Owner successfully updated with new first name
    Given user creates pets owner
    When user updates owner with firstName <firstName>
    Then updated owner is present in owners list
    Examples:
      | firstName                     |
      | A                             |
      | John                          |
      | ThirtyCharsTestNametesttestte |

  Scenario Outline: Owner is not updated in case incorrect first name format in request
    Given user creates pets owner
    When user updates owner with incorrect firstName <firstName>
    Then response header contains error <error>
    And new owner is not updated
    Examples:
      | firstName                       | error                         |
      |                                 | size must be between 1 and 30 |
      | null                            | must not be null              |
      | ThirtyOneCharsTestNametesttesst | size must be between 1 and 30 |
      | test 1/2 * ()                   | must match                    |

  Scenario Outline: Owner successfully updated with new last name
    Given user creates pets owner
    When user updates owner with lastName <lastName>
    Then updated owner is present in owners list
    Examples:
      | lastName                      |
      | B                             |
      | Dou                           |
      | ThirtyCharsTestNametesttestte |

  Scenario Outline: Owner is not updated in case incorrect last name format in request
    Given user creates pets owner
    When user updates owner with incorrect lastName <lastName>
    Then response header contains error <error>
    And new owner is not updated
    Examples:
      | lastName                        | error                         |
      |                                 | size must be between 1 and 30 |
      | null                            | must not be null              |
      | ThirtyOneCharsTestNametesttesst | size must be between 1 and 30 |
      | test 1/2 * ()                   | must match                    |

  Scenario Outline: Owner successfully updated with new address
    Given user creates pets owner
    When user updates owner with address <address>
    Then updated owner is present in owners list
    Examples:
      | address                                                                                                                                                                                                                                                        |
      | A                                                                                                                                                                                                                                                              |
      | 101 B. Down St, 1/2 &*%$#@                                                                                                                                                                                                                                     |
      | twoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHundredFiftyFifeCharsAddresstwoHun |

  Scenario Outline: Owner is not updated in case incorrect address format in request
    Given user creates pets owner
    When user updates owner with incorrect address <address>
    Then response header contains error <error>
    And new owner is not updated
    Examples:
      | address                                                                                                                                                                                                                                                          | error                          |
      |                                                                                                                                                                                                                                                                  | size must be between 1 and 255 |
      | null                                                                                                                                                                                                                                                             | must not be null               |
      | twoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftySixCharsAddresstwoHundredFiftyS | size must be between 1 and 255 |

  Scenario Outline: Owner successfully updated with new city
    Given user creates pets owner
    When user updates owner with city <city>
    Then updated owner is present in owners list
    Examples:
      | city                                                                            |
      | A                                                                               |
      | Milton-Freewater (Oregon)                                                       |
      | test 123 / &*%$#@                                                               |
      | EightyCharsTestCityEightyCharsTestCityEightyCharsTestCityEightyCharsTestCityyyy |

  Scenario Outline: Owner is not updated in case incorrect city format in request
    Given user creates pets owner
    When user updates owner with incorrect city <city>
    Then response header contains error <error>
    And new owner is not updated
    Examples:
      | city                                                                              | error                         |
      |                                                                                   | size must be between 1 and 80 |
      | null                                                                              | must not be null              |
      | EightyOneCharsTestCityEightyOneCharsTestCityEightyOneCharsTestCityEightyOneCharsT | size must be between 1 and 80 |

  Scenario Outline: Owner successfully updated with new telephone
    Given user creates pets owner
    When user updates owner with telephone <telephone>
    Then updated owner is present in owners list
    Examples:
      | telephone            |
      | 0                    |
      | 01234567890123456789 |
      #bug - test failed due to error - '{"className":"org.springframework.transaction.TransactionSystemException","exMessage":"Could not commit JPA transaction"}'

  Scenario Outline: Owner is not updated in case incorrect telephone format in request
    Given user creates pets owner
    When user updates owner with incorrect telephone <telephone>
    Then response header contains error <error>
    And new owner is not updated
    Examples:
      | telephone             | error                         |
      |                       | size must be between 1 and 20 |
      | null                  | must not be null              |
      | 012345678901234567890 | size must be between 1 and 20 |
      | test                  | must match                    |
      | 0 845                 | must match                    |

  Scenario: 404 error in case update owner by nonexistent id
    When user updates owner with nonexistent id
    Then 404 status code is returned

