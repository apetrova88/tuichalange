package tuichalange.steps;

import builders.PetsDetailsDtoBuilder;
import builders.PetsDtoBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import models.OwnerDetailsDto;
import models.PetsDetailsDto;
import models.PetsDto;
import org.junit.jupiter.api.Assertions;
import tuichalange.actions.ApiActions;
import utils.JsonUtils;

import java.util.List;

public class PetSteps {

    private final ApiActions apiActions = new ApiActions();
    private final ObjectMapper mapper = new ObjectMapper();

    private CommonSteps commonSteps;
    private OwnerSteps ownerSteps;

    private List<PetsDetailsDto> petsDetailsDtos;
    private PetsDetailsDto petsDetailsDto;
    private PetsDto petsDto;
    private Response response;

    public PetSteps(CommonSteps commonSteps, OwnerSteps ownerSteps) {
        this.commonSteps = commonSteps;
        this.ownerSteps = ownerSteps;
    }

    @Given("list of pets")
    public void listOfPets() {
        petsDetailsDtos = getPetsList();
    }

    @SneakyThrows
    private List<PetsDetailsDto> getPetsList() {
        Response response = apiActions.getPets();
        return mapper.readValue(response.getBody().asString(), new TypeReference<>(){});
    }

    @When("^user creates pet with name (.*)$")
    public void userCreatesPetsWithName(String name) {
        petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setName(name);

        Response response = apiActions.createPet(petsDto);
        petsDetailsDto = JsonUtils.getObjectFromJsonResponse(response, PetsDetailsDto.class);
    }

    @When("^user creates pet with owner with name (.*)$")
    public void userCreatesOwnerPetsWithName(String name) {
        PetsDto petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setName(name);

        response = apiActions.createPetWithOwner(petsDto, ownerSteps.getOwnerDetailsDto().getId());
        petsDetailsDto = JsonUtils.getObjectFromJsonResponse(response, PetsDetailsDto.class);
    }

    @When("user creates pet with owner")
    public void userCreatesPet() {
        petsDto = PetsDtoBuilder.createDefaultPetsDto();
        Response response = apiActions.createPetWithOwner(petsDto, ownerSteps.getOwnerDetailsDto().getId());
        petsDetailsDto = JsonUtils.getObjectFromJsonResponse(response, PetsDetailsDto.class);
        ownerSteps.setPetsDetailsDto(petsDetailsDto);
    }

    @When("nonexistent pet is requested")
    public void nonexistentPetIsRequested() {
        List<PetsDetailsDto> petsDetailsDtos = getPetsList();

        //get last pet type id
        PetsDetailsDto petsDetailsDto = petsDetailsDtos.get(petsDetailsDtos.size() - 1);
        Integer nonexistentId = petsDetailsDto.getId() + 100;

        commonSteps.setResponse(apiActions.requestPetById(nonexistentId));
    }

    @When("user creates pet with owner without name")
    public void userCreatesOwnerPetsWithoutName() {
        PetsDto petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setName(null);

        commonSteps.setResponse(apiActions.createOwnerPetBadRequest(petsDto, ownerSteps.getOwnerDetailsDto().getId()));
    }

    @When("user creates pet with owner without birthdate")
    public void userCreatesOwnerPetsWithoutBirtDate() {
        PetsDto petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setBirthDate(null);

        commonSteps.setResponse(apiActions.createOwnerPetBadRequest(petsDto, ownerSteps.getOwnerDetailsDto().getId()));
    }

    @When("user creates pet with owner without pet type")
    public void userCreatesOwnerPetsWithoutPetType() {
        PetsDto petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setType(null);

        commonSteps.setResponse(apiActions.createOwnerPetBadRequest(petsDto, ownerSteps.getOwnerDetailsDto().getId()));
    }

    @When("^user creates pet with owner with birthdate (.*)$")
    public void userCreatesOwnerPetsWithBirthDate(String birthdate) {
        PetsDto petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setBirthDate(birthdate);

        Response response = apiActions.createPetWithOwner(petsDto, ownerSteps.getOwnerDetailsDto().getId());
        petsDetailsDto = JsonUtils.getObjectFromJsonResponse(response, PetsDetailsDto.class);
    }

    @When("^user updates pet with owner with name (.*)$")
    public void userUpdatesOwnerPetsWithName(String name) {
        petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setName(name);

        response = apiActions.updatePetWithOwner(petsDto, ownerSteps.getOwnerDetailsDto().getId(), petsDetailsDto.getId());
        petsDetailsDto = JsonUtils.getObjectFromJsonResponse(response, PetsDetailsDto.class);
    }

    @When("^user updates pet with name (.*) and new birthdate$")
    public void userUpdatesPet(String name) {
        petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setName(name);

        apiActions.updatePet(petsDto, petsDetailsDto.getId());
    }

    @When("^user updates pet without name$")
    public void userUpdatesPetWithNullName() {
        petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setName(null);

        commonSteps.setResponse(apiActions.updatePetBadRequest(petsDto, petsDetailsDto.getId()));
    }

    @When("^user updates pet without birthdate")
    public void userUpdatesPetWithNullBirtDate() {
        petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setBirthDate(null);

        commonSteps.setResponse(apiActions.updatePetBadRequest(petsDto, petsDetailsDto.getId()));
    }

    @When("^user updates pet without pet type")
    public void userUpdatesPetWithNullPetType() {
        petsDto = PetsDtoBuilder.createDefaultPetsDto();
        petsDto.setType(null);

        commonSteps.setResponse(apiActions.updatePetBadRequest(petsDto, petsDetailsDto.getId()));
    }

    @Then("new pet is present in pets list")
    public void newPetIsPresentInPetsList() {
        List<PetsDetailsDto> petsDetailsDtos = getPetsList();

        Assertions.assertTrue(petsDetailsDtos.contains(petsDetailsDto));
    }

    @Then("user requests pet details by id")
    public void getPetsDetailsById() {
        Response response = apiActions.getPet(petsDetailsDto.getId());
        petsDetailsDto = JsonUtils.getObjectFromJsonResponse(response, PetsDetailsDto.class);
    }

    @Then("user deletes pet by id")
    public void deletePet() {
        apiActions.deletePetById(petsDetailsDto.getId());
    }

    @Then("pet with owner details are correct")
    public void petDetailsAreCorrect() {
        PetsDetailsDto petsDetailsDtoExpected = PetsDetailsDtoBuilder.createPetsDetailsDto(
                petsDto, petsDetailsDto.getId(), ownerSteps.getOwnerDetailsDto().getId());

        Assertions.assertEquals(petsDetailsDtoExpected, petsDetailsDto);
    }

    @Then("pet is not present in owners list")
    public void petIsNotPresentInOwnersList() {
        List<PetsDetailsDto> petsDetailsDtos = getPetsList();

        Assertions.assertFalse(petsDetailsDtos.contains(petsDetailsDto));
    }

    @When("user deletes pet with nonexistent id")
    public void userDeletesPetWithNonexistentId() {
        List<PetsDetailsDto> petsDetailsDtos = getPetsList();

        //get last id
        PetsDetailsDto petsDetailsDto = petsDetailsDtos.get(petsDetailsDtos.size() - 1);
        Integer nonexistentId = petsDetailsDto.getId() + 100;

        commonSteps.setResponse(apiActions.deletePet(nonexistentId));
    }
}
