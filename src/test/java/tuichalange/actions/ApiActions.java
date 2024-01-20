package tuichalange.actions;

import builders.PetTypeCommonDtoBuilder;
import controllers.OwnerController;
import controllers.PetController;
import controllers.PetTypesController;
import io.restassured.response.Response;
import lombok.Data;
import models.OwnerDto;
import models.PetsDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

@Data
public class ApiActions {

    private final PetTypesController petTypesController = new PetTypesController();
    private final OwnerController ownerController = new OwnerController();
    private final PetController petController = new PetController();


    public Response getPetTypes() {
        Response response = petTypesController.getPetTypes();
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response getPetTypeById(Integer petTypeId) {
        Response response = requestPetTypeById(petTypeId);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response requestPetTypeById(Integer petTypeId) {
        return petTypesController.getPetTypeById(petTypeId);
    }

    public Response createPetType(String name) {
        Response response = petTypesController.postPetTypes(PetTypeCommonDtoBuilder.createPetTypeDto(name));
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        return response;
    }

    public Response createPetTypeBadRequest(String name) {
        Response response = petTypesController.postPetTypes(PetTypeCommonDtoBuilder.createPetTypeDto(name));
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        return response;
    }

    public Response updatePetType(String name, Integer petTypeId) {
        Response response = petTypesController.putPetTypes(PetTypeCommonDtoBuilder.createPetTypeDto(name), petTypeId);
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
        return response;
    }

    public Response updateNonExistentPetType(String name, Integer petTypeId) {
        Response response = petTypesController.putPetTypes(PetTypeCommonDtoBuilder.createPetTypeDto(name), petTypeId);
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        return response;
    }

    public Response updatePetTypeBadRequest(String name, Integer petTypeId) {
        Response response = petTypesController.putPetTypes(PetTypeCommonDtoBuilder.createPetTypeDto(name), petTypeId);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        return response;
    }

    public Response deletePetType(Integer petTypeId) {
        Response response = petTypesController.deletePetType(petTypeId);
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
        return response;
    }

    public Response deleteNonexistentPetType(Integer petTypeId) {
        Response response = petTypesController.deletePetType(petTypeId);
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        return response;
    }

    public Response getOwners() {
        Response response = ownerController.getOwners();
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response getOwnerById(Integer ownerId) {
        Response response = requestOwnerById(ownerId);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response searchOwnerByParameter(String searchParam, String searchValue) {
        Response response = ownerController.getOwnersWithQueryParams(searchParam, searchValue);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response searchOwner(String searchParam, String searchValue) {
        return ownerController.getOwnersWithQueryParams(searchParam, searchValue);
    }

    public Response deleteOwnerById(Integer ownerId) {
        Response response = deleteOwner(ownerId);
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
        return response;
    }

    public Response deleteOwner(Integer ownerId) {
        return ownerController.deleteOwner(ownerId);
    }

    public Response requestOwnerById(Integer ownerId) {
        return ownerController.getOwnerById(ownerId);
    }

    public Response updateOwner(OwnerDto ownerDto, Integer ownerId) {
        return ownerController.putOwner(ownerDto, ownerId);
    }

    public Response createOwner(OwnerDto ownerDto) {
        Response response = ownerController.postOwner(ownerDto);
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        return response;
    }

    public Response createOwnerIncorrectValues(OwnerDto ownerDto) {
        Response response = ownerController.postOwner(ownerDto);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        return response;
    }

    public Response updateOwnerById(OwnerDto ownerDto, Integer ownerId) {
        Response response = ownerController.putOwner(ownerDto, ownerId);
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
        return response;
    }

    public Response updateOwnerInvalid(OwnerDto ownerDto, Integer ownerId) {
        Response response = ownerController.putOwner(ownerDto, ownerId);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        return response;
    }

    public Response createPet(PetsDto petsDto) {
        Response response = petController.postPet(petsDto);
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        return response;
    }

    public Response createPetWithOwner(PetsDto petsDto, Integer ownerId) {
        Response response = petController.postOwnerPet(petsDto, ownerId);
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        return response;
    }

    public Response updatePetWithOwner(PetsDto petsDto, Integer ownerId, Integer petId) {
        Response response = petController.putOwnerPet(petsDto, ownerId, petId);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response updatePet(PetsDto petsDto, Integer petId) {
        Response response = petController.putPet(petsDto, petId);
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
        return response;
    }

    public Response updatePetBadRequest(PetsDto petsDto, Integer petId) {
        Response response = petController.putPet(petsDto, petId);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        return response;
    }

    public Response getPet(Integer petId) {
        Response response = requestPetById(petId);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response createOwnerPetBadRequest(PetsDto petsDto, Integer ownerId) {
        Response response = petController.postOwnerPet(petsDto, ownerId);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        return response;
    }

    public Response getPets() {
        Response response = petController.getPets();
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        return response;
    }

    public Response requestPetById(Integer petId) {
        return petController.getPetById(petId);
    }

    public Response deletePetById(Integer petId) {
        Response response = deletePet(petId);
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
        return response;
    }

    public Response deletePet(Integer petId) {
        return petController.deletePet(petId);
    }
}
