package controllers;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.PetTypeCommonDto;

import static constants.ApiConstants.API_BASE_URL;
import static constants.ApiConstants.PETTYPES;
import static constants.ApiConstants.PETTYPES_PETTYPE_ID;

public class PetTypesController {

    public RequestSpecification setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .filters(
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter());
    }

    /**
     * getPetTypes
     *
     * @return response
     */
    public Response getPetTypes() {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETTYPES);

        return RestAssured.given(spec).get();
    }

    /**
     * getPetTypeById
     *
     * @param petTypeId
     * @return
     */

    public Response getPetTypeById(Integer petTypeId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETTYPES_PETTYPE_ID)
                .pathParam("petTypeId", petTypeId);

        return RestAssured.given(spec).get();
    }

    /**
     * postPetTypes
     *
     * @param petTypeCommonDto
     * @return
     */
    public Response postPetTypes(PetTypeCommonDto petTypeCommonDto) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETTYPES)
                .body(petTypeCommonDto);

        return RestAssured.given(spec).post();
    }

    /**
     * putPetTypes
     *
     * @param petTypeCommonDto
     * @param petTypeId
     * @return
     */
    public Response putPetTypes(PetTypeCommonDto petTypeCommonDto, Integer petTypeId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETTYPES_PETTYPE_ID)
                .pathParam("petTypeId", petTypeId)
                .body(petTypeCommonDto);

        return RestAssured.given(spec).put();
    }

    /**
     * deletePetType
     *
     * @param petTypeId
     * @return
     */
    public Response deletePetType(Integer petTypeId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(PETTYPES_PETTYPE_ID)
                .pathParam("petTypeId", petTypeId);

        return RestAssured.given(spec).delete();
    }
}
