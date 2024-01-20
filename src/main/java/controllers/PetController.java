package controllers;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.PetTypeCommonDto;
import models.PetsDto;

import static constants.ApiConstants.API_BASE_URL;
import static constants.ApiConstants.OWNERS_OWNER_ID_PETS;
import static constants.ApiConstants.OWNERS_OWNER_ID_PETS_PET_ID;
import static constants.ApiConstants.PETS;
import static constants.ApiConstants.PETS_PET_ID;
import static constants.ApiConstants.PETTYPES;
import static constants.ApiConstants.PETTYPES_PETTYPE_ID;

public class PetController {

    public RequestSpecification setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .filters(
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter());
    }

    /**
     * getPets
     *
     * @return response
     */
    public Response getPets() {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETS);

        return RestAssured.given(spec).get();
    }

    /**
     * getPetById
     *
     * @param petId
     * @return
     */

    public Response getPetById(Integer petId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETS_PET_ID)
                .pathParam("petId", petId);

        return RestAssured.given(spec).get();
    }

    /**
     * postPet
     *
     * @param petsDto
     * @return
     */
    public Response postPet(PetsDto petsDto) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETS)
                .body(petsDto);

        return RestAssured.given(spec).post();
    }

    /**
     * postOwnerPet
     *
     * @param petsDto
     * @param ownerId
     * @return
     */
    public Response postOwnerPet(PetsDto petsDto, Integer ownerId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS_OWNER_ID_PETS)
                .pathParam("ownerId", ownerId)
                .body(petsDto);

        return RestAssured.given(spec).post();
    }

    /**
     * putPet
     *
     * @param petsDto
     * @param petId
     * @return
     */
    public Response putPet(PetsDto petsDto, Integer petId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETS_PET_ID)
                .pathParam("petId", petId)
                .body(petsDto);

        return RestAssured.given(spec).put();
    }

    public Response putOwnerPet(PetsDto petsDto, Integer petId,Integer ownerId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS_OWNER_ID_PETS_PET_ID)
                .pathParam("ownerId", ownerId)
                .pathParam("petId", petId)
                .body(petsDto);

        return RestAssured.given(spec).put();
    }

    /**
     * deletePet
     *
     * @param petId
     * @return
     */
    public Response deletePet(Integer petId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETS_PET_ID)
                .pathParam("petId", petId);

        return RestAssured.given(spec).delete();
    }
}
