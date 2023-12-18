package starter.SuperAdmin;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class GetAllSuperAdmin {
    private String url = "https://qbills.biz.id/api/v1/super-admins";

    @Step("Super Admin mengakses endpoint untuk mendapatkan semua Super Admin")
    public String apiGetAllSuperAdmin() {
        return url;
    }

    @Step("Super Admin mengirim request untuk mendapatkan semua Super Admin")
    public void requestAllSuperadmin(){
    String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();

    SerenityRest.given()
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + token)
        .get(apiGetAllSuperAdmin());
    }

    @Step("sistem seharusnya menampilkan daftar semua Super Admin yang tersedia")
    public void receiveAllSuperAdminData() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_SUPER_ADMIN_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));

}
}
