package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import lombok.SneakyThrows;


public class JsonUtils {

    /**
     * getObjectFromJsonString
     * deserialization from json string to Java object
     *
     * @param jsonString
     * @param dtoClass
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> T getObjectFromJsonString(String jsonString, Class<T> dtoClass) {
        ObjectMapper objectMapper = JsonMapper.builder().build();

        return objectMapper.readValue(jsonString, dtoClass);

    }

    /**
     * getObjectFromJsonResponse
     * deserialization from json response to Java object
     *
     * @param jsonResponse
     * @param dtoClass
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> T getObjectFromJsonResponse(Response jsonResponse, Class<T> dtoClass) {
        ObjectMapper objectMapper = JsonMapper.builder().build();

        return objectMapper.readValue(jsonResponse.getBody().asString(), dtoClass);
    }
}
