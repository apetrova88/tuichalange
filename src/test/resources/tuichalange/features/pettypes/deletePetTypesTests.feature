Feature: Delete Pet Types Api

  Scenario: Pet type can be deleted successfully
    Given user creates pet type with name testPetType
    When user delete pet type
    Then pet type is not present in pet type list

  Scenario: Pet type not found error in case deleting nonexistent pet type
    When nonexistent pet type is deleted
    Then 404 status code is returned
