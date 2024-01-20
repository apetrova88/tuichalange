package tuichalange.steps;

import builders.OwnerDetailsDtoBuilder;
import builders.OwnerDtoBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.Data;
import lombok.SneakyThrows;
import models.OwnerDetailsDto;
import models.OwnerDto;
import models.PetsDetailsDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import tuichalange.actions.ApiActions;
import utils.JsonUtils;

import java.util.List;

@Data
public class OwnerSteps {

    private final ApiActions apiActions = new ApiActions();
    private final ObjectMapper mapper = new ObjectMapper();

    private CommonSteps commonSteps;

    private List<OwnerDetailsDto> ownerDetailsDtoList;
    private OwnerDetailsDto ownerDetailsDto;
    private OwnerDto ownerDto;
    private Response response;
    private PetsDetailsDto petsDetailsDto;

    public OwnerSteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @Given("list of owners")
    public void listOfOwners() {
        ownerDetailsDtoList = getOwnersList();
    }

    @SneakyThrows
    private List<OwnerDetailsDto> getOwnersList() {
        Response response = apiActions.getOwners();
        return mapper.readValue(response.getBody().asString(), new TypeReference<>(){});
    }

    @When("^user creates owner with firstName (.*)$")
    public void userCreatesOwnerWithFirstName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();
        ownerDto.setFirstName(name);

        Response response = apiActions.createOwner(ownerDto);
        ownerDetailsDto = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);
    }

    @When("user creates pets owner")
    public void userCreatesOwner() {
        ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();

        Response response = apiActions.createOwner(ownerDto);
        ownerDetailsDto = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);
    }

    @When("^user creates owner with incorrect firstName (.*)$")
    public void userCreatesOwnerWithIncorrectFirstName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();

        String value = name.equals("null") ? null : name;
        ownerDto.setFirstName(value);

        commonSteps.setResponse(apiActions.createOwnerIncorrectValues(ownerDto));
    }

    @When("^user creates owner with incorrect lastName (.*)$")
    public void userCreatesOwnerWithIncorrectLastName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();

        String value = name.equals("null") ? null : name;
        ownerDto.setLastName(value);

        commonSteps.setResponse(apiActions.createOwnerIncorrectValues(ownerDto));
    }

    @When("^user creates owner with incorrect address (.*)$")
    public void userCreatesOwnerWithIncorrectAddress(String address) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();

        String value = address.equals("null") ? null : address;
        ownerDto.setAddress(value);

        commonSteps.setResponse(apiActions.createOwnerIncorrectValues(ownerDto));
    }

    @When("^user creates owner with incorrect city (.*)$")
    public void userCreatesOwnerWithIncorrectCity(String city) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();

        String value = city.equals("null") ? null : city;
        ownerDto.setCity(value);

        commonSteps.setResponse(apiActions.createOwnerIncorrectValues(ownerDto));
    }

    @When("^user creates owner with incorrect telephone (.*)$")
    public void userCreatesOwnerWithIncorrectTelephone(String telephone) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();

        String value = telephone.equals("null") ? null : telephone;
        ownerDto.setTelephone(value);

        commonSteps.setResponse(apiActions.createOwnerIncorrectValues(ownerDto));
    }

    @When("^user creates owner with lastName (.*)$")
    public void userCreatesOwnerWithLastName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();
        ownerDto.setLastName(name);

        Response response = apiActions.createOwner(ownerDto);
        ownerDetailsDto = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);
    }

    @When("^user creates owner with city (.*)$")
    public void userCreatesOwnerWithCity(String city) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();
        ownerDto.setCity(city);

        Response response = apiActions.createOwner(ownerDto);
        ownerDetailsDto = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);
    }

    @When("^user creates owner with address (.*)$")
    public void userCreatesOwnerWithAddress(String address) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();
        ownerDto.setAddress(address);

        Response response = apiActions.createOwner(ownerDto);
        ownerDetailsDto = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);
    }

    @When("^user creates owner with telephone (.*)$")
    public void userCreatesOwnerWithTelephone(String telephone) {
        OwnerDto ownerDto = OwnerDtoBuilder.createDefaultOwnerDto();
        ownerDto.setTelephone(telephone);

        Response response = apiActions.createOwner(ownerDto);
        ownerDetailsDto = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);
    }

    @Then("new owner is present in owners list")
    public void newOwnerIsPresentInOwnersList() {
        List<OwnerDetailsDto> ownerDetailsDtoList = getOwnersList();

        Assertions.assertTrue(ownerDetailsDtoList.contains(ownerDetailsDto));
    }

    @Then("new owner is not updated")
    public void newOwnerIsNotUpdated() {
        Response response = apiActions.getOwnerById(ownerDetailsDto.getId());
        OwnerDetailsDto ownerDetailsDtoActual = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);

        //check objects are the same
        Assertions.assertEquals(ownerDetailsDto, ownerDetailsDtoActual);
    }

    @When("user request pets owner by owner id")
    public void userRequestPetsOwnerByOwnerId() {
        response = apiActions.getOwnerById(ownerDetailsDto.getId());
    }

    @When("user deletes pets owner by owner id")
    public void userDeletesPetsOwnerByOwnerId() {
        apiActions.deleteOwnerById(ownerDetailsDto.getId());
    }

    @When("user gets pets owner details by owner id")
    public void userGetsPetsOwnerByOwnerId() {
        response = apiActions.getOwnerById(ownerDetailsDto.getId());
        ownerDetailsDto = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);
    }

    @When("user request pets owner by nonexistent owner id")
    public void userRequestPetsOwnerByNonexistentOwnerId() {
        List<OwnerDetailsDto> ownerDetailsDtoList = getOwnersList();

        //get last id
        OwnerDetailsDto ownerDetailsDto = ownerDetailsDtoList.get(ownerDetailsDtoList.size() - 1);
        Integer nonexistentId = ownerDetailsDto.getId() + 100;

        commonSteps.setResponse(apiActions.requestOwnerById(nonexistentId));
    }

    @Then("correct owner details are returned")
    public void ownerDetailsAreReturned() {
        OwnerDetailsDto ownerDetailsDtoActual = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);

        OwnerDetailsDto ownerDetailsDtoExpected = OwnerDetailsDtoBuilder.createOwnerDetailsDto(ownerDto, ownerDetailsDto.getId(), List.of());

        Assertions.assertEquals(ownerDetailsDtoExpected, ownerDetailsDtoActual);
    }

    @Then("correct owner with pet details are returned")
    public void ownerWithPetDetailsAreReturned() {
        OwnerDetailsDto ownerDetailsDtoActual = JsonUtils.getObjectFromJsonResponse(response, OwnerDetailsDto.class);

        OwnerDetailsDto ownerDetailsDtoExpected = OwnerDetailsDtoBuilder.createOwnerDetailsDto(ownerDto, ownerDetailsDto.getId(), List.of(petsDetailsDto));

        Assertions.assertEquals(ownerDetailsDtoExpected, ownerDetailsDtoActual);
    }

    @When("^user updates owner with firstName (.*)$")
    public void userUpdatesOwnerWithFirstName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);
        ownerDto.setFirstName(name);

        apiActions.updateOwnerById(ownerDto, ownerDetailsDto.getId());
        ownerDetailsDto.setFirstName(name);
    }

    @When("^user updates owner with lastName (.*)$")
    public void userUpdatesOwnerWithLastName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);
        ownerDto.setLastName(name);

        apiActions.updateOwnerById(ownerDto, ownerDetailsDto.getId());
        ownerDetailsDto.setLastName(name);
    }

    @When("^user updates owner with address (.*)$")
    public void userUpdatesOwnerWithAddress(String address) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);
        ownerDto.setAddress(address);

        apiActions.updateOwnerById(ownerDto, ownerDetailsDto.getId());
        ownerDetailsDto.setAddress(address);
    }

    @When("^user updates owner with city (.*)$")
    public void userUpdatesOwnerWithCity(String city) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);
        ownerDto.setCity(city);

        apiActions.updateOwnerById(ownerDto, ownerDetailsDto.getId());
        ownerDetailsDto.setCity(city);
    }

    @When("^user updates owner with telephone (.*)$")
    public void userUpdatesOwnerWithTelephone(String telephone) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);
        ownerDto.setTelephone(telephone);

        apiActions.updateOwnerById(ownerDto, ownerDetailsDto.getId());
        ownerDetailsDto.setTelephone(telephone);
    }

    @When("^user updates owner with incorrect firstName (.*)$")
    public void userUpdatesOwnerWithIncorrectFirstName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);

        String value = name.equals("null") ? null : name;
        ownerDto.setFirstName(value);

        commonSteps.setResponse(apiActions.updateOwnerInvalid(ownerDto, ownerDetailsDto.getId()));
    }

    @When("^user updates owner with incorrect lastName (.*)$")
    public void userUpdatesOwnerWithIncorrectLastName(String name) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);

        String value = name.equals("null") ? null : name;
        ownerDto.setLastName(value);

        commonSteps.setResponse(apiActions.updateOwnerInvalid(ownerDto, ownerDetailsDto.getId()));
    }

    @When("^user updates owner with incorrect address (.*)$")
    public void userUpdatesOwnerWithIncorrectAddress(String address) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);

        String value = address.equals("null") ? null : address;
        ownerDto.setAddress(value);

        commonSteps.setResponse(apiActions.updateOwnerInvalid(ownerDto, ownerDetailsDto.getId()));
    }

    @When("^user updates owner with incorrect city (.*)$")
    public void userUpdatesOwnerWithIncorrectCity(String city) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);

        String value = city.equals("null") ? null : city;
        ownerDto.setCity(value);

        commonSteps.setResponse(apiActions.updateOwnerInvalid(ownerDto, ownerDetailsDto.getId()));
    }

    @When("^user updates owner with incorrect telephone (.*)$")
    public void userUpdatesOwnerWithIncorrectTelephone(String telephone) {
        OwnerDto ownerDto = OwnerDtoBuilder.createOwnerDtoFromDetails(ownerDetailsDto);

        String value = telephone.equals("null") ? null : telephone;
        ownerDto.setTelephone(value);

        commonSteps.setResponse(apiActions.updateOwnerInvalid(ownerDto, ownerDetailsDto.getId()));
    }

    @When("user updates owner with nonexistent id")
    public void userUpdatesOwnerWithNonexistentId() {
        List<OwnerDetailsDto> ownerDetailsDtoList = getOwnersList();

        //get last id
        OwnerDetailsDto ownerDetailsDto = ownerDetailsDtoList.get(ownerDetailsDtoList.size() - 1);
        Integer nonexistentId = ownerDetailsDto.getId() + 100;

        commonSteps.setResponse(apiActions.updateOwner(OwnerDtoBuilder.createDefaultOwnerDto(), nonexistentId));
    }

    @When("user deletes owner with nonexistent id")
    public void userDeletesOwnerWithNonexistentId() {
        List<OwnerDetailsDto> ownerDetailsDtoList = getOwnersList();

        //get last id
        OwnerDetailsDto ownerDetailsDto = ownerDetailsDtoList.get(ownerDetailsDtoList.size() - 1);
        Integer nonexistentId = ownerDetailsDto.getId() + 100;

        commonSteps.setResponse(apiActions.deleteOwner(nonexistentId));
    }

    @Then("updated owner is present in owners list")
    public void updatedOwnerIsPresentInOwnersList() {
        List<OwnerDetailsDto> ownerDetailsDtoList = getOwnersList();

        Assertions.assertTrue(ownerDetailsDtoList.contains(ownerDetailsDto));
    }

    @Then("owner remains the same")
    public void ownerRemainsTheSame() {
        List<OwnerDetailsDto> ownerDetailsDtoList = getOwnersList();

        Assertions.assertTrue(ownerDetailsDtoList.contains(ownerDetailsDto));
    }

    @And("new owner is not created")
    public void newOwnerIsNotCreated() {
        List<OwnerDetailsDto> ownerDetailsDtoListActual = getOwnersList();

        Assertions.assertEquals(ownerDetailsDtoList.size(), ownerDetailsDtoListActual.size());
    }

    @Then("owner is not present in owners list")
    public void ownerIsDeleted() {
        List<OwnerDetailsDto> ownerDetailsDtoList = getOwnersList();

        Assertions.assertFalse(ownerDetailsDtoList.contains(ownerDetailsDto));
    }

    @When("^user request pets owner by (lastName) (.*)$")
    public void userRequestPetsOwnerByLastName(String searchParamName, String searchParamValue) {
        response = apiActions.searchOwnerByParameter(searchParamName, searchParamValue);
    }

    @When("^user request pets owner by nonexistent (lastName)$")
    public void userRequestPetsOwnerByNonexistentLastname(String searchParamName) {
        commonSteps.setResponse(apiActions.searchOwner(searchParamName, RandomStringUtils.randomAlphabetic(10)));
    }

    @SneakyThrows
    @Then("^only owners with (.*) last name are returned$")
    public void ownersWithSmithLastNameAreReturned(String lastName) {
        List<OwnerDetailsDto> ownerDetailsDtoList = mapper.readValue(response.getBody().asString(), new TypeReference<>(){});

        Assertions.assertTrue(ownerDetailsDtoList.stream().allMatch(x -> x.getLastName().equals(lastName)));
    }

    @SneakyThrows
    @Then("^owners whose last name contains (.*) are returned$")
    public void ownersContainsLastNameAreReturned(String lastName) {
        List<OwnerDetailsDto> ownerDetailsDtoList = mapper.readValue(response.getBody().asString(), new TypeReference<>(){});

        Assertions.assertTrue(ownerDetailsDtoList.stream().allMatch(x -> x.getLastName().contains(lastName)));
    }
}
