package starter.ConvertPoint;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GetterConvertPoint;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlConvertPoint;

public class GetConvertPointByID {

    @Step("Admin memasukkan endpoint yang valid untuk Get All Convert Point by ID")
    public String apiGetAllConvertPointById() {
        return urlConvertPoint;
    }

    @Step("Admin send request dengan ID yang valid untuk Get All Convert Point by ID")
    public void sendRequestGetAllConvertPointById() {
        int lastConvertPointID = GetterConvertPoint.getLastConvertPointID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlConvertPoint + lastConvertPointID);
    }

    @Step("Convert Point sesuai ID berhasil ditampilkan")
    public void convertPointByIdDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_CONVERT_POINT_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'point'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'valuePoint'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Get Convert Point By ID Invalid ID
     */

    @Step("Admin send request dengan ID yang invalid untuk Get Convert Point by ID")
    public void sendRequestConvertPointInvalidId() {
        int invalidConvertPointID = GetterConvertPoint.getLastConvertPointID()+1;
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authoprization", "Bearer " + token)
                .get(urlConvertPoint + invalidConvertPointID+1);
    }

    @Step("convert point sesuai ID tidak ditemukan")
    public void invalidIdConvertPoint() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_CONVERT_POINT_BY_ID_INVALID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
