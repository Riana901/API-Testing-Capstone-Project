package starter.ConvertPoint;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GetterConvertPoint;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlConvertPoint;

public class UpdateConvertPoint {

    @Step("Admin mengakses dengan endpoint yang valid untuk update convert point")
    public String apiUpdateConvertPoint() {
        return urlConvertPoint;
    }

    @Step("Admin send request dengan valid untuk melakukan update Convert Point")
    public void sendRequestUpdateConvertPoint() {
        String token = GenerateTokenAdmin.generateTokenAdmin();

        JSONObject requestBody = new JSONObject();

        requestBody.put("point", 5);
        requestBody.put("valuePoint", 5000);

        int lastConvertPointID = GetterConvertPoint.getLastConvertPointID();
        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlConvertPoint + lastConvertPointID);
    }

    @Step("Update berhasil")
    public void updateConvertPointSuccess() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.UPDATE_CONVERT_POINT_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'point'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'valuePoint'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Update Convert Point Invalid
     */

    @Step("Admin send request dengan ID yang invalid untuk update")
    public void sendRequestInvalidUpdateConvertPoint() {

        JSONObject requestBody = new JSONObject();

        requestBody.put("point", 5);
        requestBody.put("valuePoint", 5000);

        int lastConvertPointID = GetterConvertPoint.getLastConvertPointID()+1;
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlConvertPoint + lastConvertPointID);
    }

    @Step("Update gagal dilakukan")
    public void updateConvertPointFailed() {
    }
}
