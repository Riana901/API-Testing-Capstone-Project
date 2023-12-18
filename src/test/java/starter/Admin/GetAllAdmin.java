package starter.Admin;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlGetAllAdmin;

public class GetAllAdmin {

    @Step("Admin mengakses API Endpoint untuk login")
    public String apiGetAllAdmin() {
        return urlGetAllAdmin;
    }

    @Step("Super Admin mengirim request untuk mendapatkan semua Admin")
    public void getAllAdmin(){
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(apiGetAllAdmin());
    }

    @Step("sistem seharusnya menampilkan daftar semua akun admin yang tersedia")
    public void allAdminData(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_ADMIN_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'superAdminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    @Step("Super Admin mencoba mengakses endpoint untuk mendapatkan semua akun admin tanpa token")
    public void noTokenGetAllAdmin(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .get(apiGetAllAdmin());
    }

    @Step("Super Admin menerima pesan kesalahan untuk token")
    public void noTokenErrorGetAllAdmin(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.NO_TOKEN_RESPONSE_SCHEMA);
        restAssuredThat(response -> response.body("'message'", is("missing or malformed jwt")));
        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @When("Super Admin mencoba mengakses endpoint untuk mendapatkan semua akun admin tanpa token")
    public void errorNoTokenGetAllAdmin(){
        noTokenGetAllAdmin();
    }

    @And("Super Admin menerima pesan kesalahan untuk token")
    public void errorMsgGetAllAdmin(){
        noTokenErrorGetAllAdmin();
    }



}
