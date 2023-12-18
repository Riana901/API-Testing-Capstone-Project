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
import static starter.URL.urlConvertPoint;

public class DeleteConvertPoint {

    @Step("Admin akses dengan endpoint yang valid untuk delete convert point")
    public String apiDeleteConvertPoint() {
        return urlConvertPoint;
    }

    @Step("Admin send request untuk menghapus convert point")
    public void sendRequestConvertPoint() {
        int lastConvertPointID = GetterConvertPoint.getLastConvertPointID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlConvertPoint + lastConvertPointID);
    }

    @Step("Convert Point berhasil dihapus")
    public void convertPointDeleted() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_CONVERT_POINT_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("Successfully Delete Data ConvertPoint")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
