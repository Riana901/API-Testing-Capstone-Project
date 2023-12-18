package starter.ConvertPoint;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlConvertPoint;

public class GetAllConvertPoint {

    @Step("Admin akses dengan endpoint yang valid untuk Get All Convert Point")
    public String apiGetAllConvertPoint() {
        return urlConvertPoint;
    }

    @Step("Admin send request Get All Convert Point dengan valid")
    public void sendRequestGetAllConvertPoint() {
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(apiGetAllConvertPoint());
    }

    @Step("Semua convert point berhasil ditampilkan")
    public void allConvertPointDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_CONVERT_POINT_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'point'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'valuePoint'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
