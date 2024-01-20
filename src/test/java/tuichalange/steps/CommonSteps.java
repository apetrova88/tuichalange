package tuichalange.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.Data;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

@Data
public class CommonSteps {

    private Response response;

    @And("^response header contains error (.*)$")
    public void responseContainsError(String error) {
        String errorHeader = response.getHeaders().get("errors").getValue();
        Assertions.assertTrue(errorHeader.contains(error));
    }

    @Then("^(.*) error is present in response$")
    public void errorIsPresentInResponse(String error) {
        Assertions.assertTrue(response.getBody().asString().contains(error), "error is missed in response");
    }

    @Then("response is empty")
    public void responseIsEmpty() {
        Assertions.assertTrue(response.getBody().asString().isEmpty());
    }

    @Then("^(.*) status code is returned$")
    public void responseIsEmpty(int code) {
        Assertions.assertEquals(code, response.getStatusCode());
    }
}
