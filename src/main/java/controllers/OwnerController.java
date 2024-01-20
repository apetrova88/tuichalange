package controllers;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.OwnerDto;

import static constants.ApiConstants.API_BASE_URL;
import static constants.ApiConstants.OWNERS;
import static constants.ApiConstants.OWNERS_OWNER_ID;

public class OwnerController {

    public RequestSpecification setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .filters(
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter());
    }

    /**
     * getOwners
     *
     * @return response
     */
    public Response getOwners() {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS);

        return RestAssured.given(spec).get();
    }

    /**
     * getOwnersWithQueryParams
     *
     * @param searchParam
     * @param searchValue
     * @return
     */
    public Response getOwnersWithQueryParams(String searchParam, String searchValue) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS)
                .queryParam(searchParam, searchValue);

        return RestAssured.given(spec).get();
    }

    /**
     * getOwnerById
     *
     * @param ownerId
     * @return
     */

    public Response getOwnerById(Integer ownerId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS_OWNER_ID)
                .pathParam("ownerId", ownerId);

        return RestAssured.given(spec).get();
    }

    /**
     * postOwners
     *
     * @param ownerDto
     * @return
     */
    public Response postOwner(OwnerDto ownerDto) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS)
                .body(ownerDto);

        return RestAssured.given(spec).post();
    }

    /**
     * putPetTypes
     *
     * @param ownerDto
     * @param ownerId
     * @return
     */
    public Response putOwner(OwnerDto ownerDto, Integer ownerId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS_OWNER_ID)
                .pathParam("ownerId", ownerId)
                .body(ownerDto);

        return RestAssured.given(spec).put();
    }

    /**
     * deleteOwner
     *
     * @param ownerId
     * @return
     */
    public Response deleteOwner(Integer ownerId) {
        RequestSpecification spec = RestAssured
                .given(setUp())
                .baseUri(API_BASE_URL)
                .basePath(OWNERS_OWNER_ID)
                .pathParam("ownerId", ownerId);

        return RestAssured.given(spec).delete();
    }
}
