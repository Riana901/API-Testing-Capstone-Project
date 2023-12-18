package starter.ConvertPoint;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateTokenAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static starter.URL.urlConvertPoint;
import static starter.URL.urlInvalidConvertPoint;

public class CreateConvertPoint {

    @Step("Admin mengakses endpoint untuk membuat convert point dengan valid")
    public String apiCreateConvertPoint() {
        return urlConvertPoint;
    }

    @Step("Admin send request untuk membuat convert point dengan valid")
    public void sendRequestConvertPoint() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("point", 10);
        requestBody.put("valuePoint", 10000);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiCreateConvertPoint());
    }

    @Step("Convert Point baru berhasil dibuat")
    public void convertPointCreated() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.CREATE_CONVERT_POINT_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("Succesfully create ConvertPoint")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'point'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'valuePoint'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));

    }

}
