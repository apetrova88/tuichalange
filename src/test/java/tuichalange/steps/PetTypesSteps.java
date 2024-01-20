package tuichalange.steps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import models.PetTypeDto;
import org.junit.jupiter.api.Assertions;
import tuichalange.actions.ApiActions;
import utils.JsonUtils;

import java.util.List;

public class PetTypesSteps {

    private final ApiActions apiActions = new ApiActions();
    private final ObjectMapper mapper = new ObjectMapper();

    private CommonSteps commonSteps;

    private List<PetTypeDto> petTypeDtos;
    private PetTypeDto petTypeDto;
    private Response response;

    public PetTypesSteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @Given("list of pet types")
    public void listOfPetTypes() {
        petTypeDtos = getPetTypeList();
    }

    @When("^user creates pet type with name (.*)$")
    public void userCreatesPetType(String name) {
        Response response = apiActions.createPetType(name);
        petTypeDto = JsonUtils.getObjectFromJsonResponse(response, PetTypeDto.class);
    }

    @When("^user creates pet type with invalid name (.*)$")
    public void userCreatesInvalidPetType(String name) {
        String value = name.equals("null") ? null : name;
        commonSteps.setResponse(apiActions.createPetTypeBadRequest(value));
    }

    @Then("new pet type is present in pet types list")
    public void newPetTypeNameIsPresentInPetTypesList() {
        List<PetTypeDto> petTypeDtoActual = getPetTypeList();

        //prepare expected object
        petTypeDtos.add(petTypeDto);

        //check objects are the same
        Assertions.assertEquals(petTypeDtos, petTypeDtoActual);
    }

    @Then("new pet type is not created")
    public void newPetTypeIsNotCreated() {
        List<PetTypeDto> petTypeDtoActual = getPetTypeList();

        //check objects are the same
        Assertions.assertEquals(petTypeDtos, petTypeDtoActual);
    }

    @When("^user update pet type with name (.*)$")
    public void userUpdatePetTypeWithName(String name) {
        response = apiActions.updatePetType(name, petTypeDto.getId());
        petTypeDto.setName(name);
    }

    @Then("updated pet type is present in pet types list")
    public void updatedPetTypeIsPresentInPetTypesList() {
        List<PetTypeDto> petTypeDtos = getPetTypeList();

        Assertions.assertTrue(petTypeDtos.contains(petTypeDto));
    }

    @When("^user update pet type with invalid name (.*)$")
    public void userUpdatePetTypeWithInvalidNameName(String name) {
        String value = name.equals("null") ? null : name;
        commonSteps.setResponse(apiActions.updatePetTypeBadRequest(value, petTypeDto.getId()));
    }

    @Then("pet type remains the same in pet types list")
    public void petTypeRemainsTheSameTypesList() {
        List<PetTypeDto> petTypeDtos = getPetTypeList();

        Assertions.assertTrue(petTypeDtos.contains(petTypeDto));
    }

    @When("user delete pet type")
    public void userDeletePetType() {
        apiActions.deletePetType(petTypeDto.getId());
    }

    @Then("pet type is not present in pet type list")
    public void petTypeIsNotPresentInPetTypeList() {
        List<PetTypeDto> petTypeDtos = getPetTypeList();
        Assertions.assertFalse(petTypeDtos.contains(petTypeDto));
    }

    @When("pet type is requested")
    public void petTypeIsRequested() {
        response = apiActions.getPetTypeById(petTypeDto.getId());
    }

    @Then("correct pet type is returned")
    public void correctPetTypeIsReturned() {
        PetTypeDto petTypeDtoActual = JsonUtils.getObjectFromJsonResponse(response, PetTypeDto.class);
        Assertions.assertEquals(petTypeDto, petTypeDtoActual);
    }

    @When("nonexistent pet type is requested")
    public void nonexistentPetTypeIsRequested() {
        List<PetTypeDto> petTypeDtos = getPetTypeList();

        //get last pet type id
        PetTypeDto petTypeDto = petTypeDtos.get(petTypeDtos.size() - 1);
        Integer nonexistentId = petTypeDto.getId() + 100;

        commonSteps.setResponse(apiActions.requestPetTypeById(nonexistentId));
    }

    @When("nonexistent pet type is updated")
    public void nonexistentPetTypeIsUpdated() {
        List<PetTypeDto> petTypeDtos = getPetTypeList();

        //get last pet type id
        PetTypeDto petTypeDto = petTypeDtos.get(petTypeDtos.size() - 1);
        Integer nonexistentId = petTypeDto.getId() + 100;

        commonSteps.setResponse(apiActions.updateNonExistentPetType("test", nonexistentId));
    }

    @When("nonexistent pet type is deleted")
    public void nonexistentPetTypeIsDeleted() {
        List<PetTypeDto> petTypeDtos = getPetTypeList();

        //get last pet type id
        PetTypeDto petTypeDto = petTypeDtos.get(petTypeDtos.size() - 1);
        Integer nonexistentId = petTypeDto.getId() + 100;

        commonSteps.setResponse(apiActions.deleteNonexistentPetType(nonexistentId));
    }

    @SneakyThrows
    private List<PetTypeDto> getPetTypeList() {
        Response response = apiActions.getPetTypes();
        return mapper.readValue(response.getBody().asString(), new TypeReference<>(){});
    }
}
