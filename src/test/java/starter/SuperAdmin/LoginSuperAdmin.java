package starter.SuperAdmin;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.FileSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class LoginSuperAdmin {
    private static String url = "https://qbills.biz.id/api/v1/super-admin/";

    @Step("Super Admin mengatur Endpoint API untuk login")
    public String apiSuperAdminLogin() {
        return url + "login";
    }

    @Step("Super Admin mengisi data yang valid untuk login")
    public void sendValidLoginSuperAdmin() {
        JSONObject requestBody = FileSuperAdmin.getSuperAdmin();

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiSuperAdminLogin());
    }

    @Step("Super Admin menerima data login")
    public void receiveValidDataForLogin() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'role'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'token'", notNullValue()));
        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Login dengan Username yang salah
     */
    @Step("Super Admin mengirim request dengan memasukkan username yang salah")
    public void sendInvalidLoginUnameSuperadmin() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "morpheus");
        requestBody.put("password","12345678");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiSuperAdminLogin());
    }
    @Step("Super Admin menerima pesan kesalahan")
    public void errorMessage() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("invalid email or password")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Login dengan password yang salah
     */
    @Step("Super Admin mengirim request dengan memasukkan password yang salah")
    public void sendInvalidLoginPwordSuperadmin() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "arief");
        requestBody.put("password","rakahahahaha");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiSuperAdminLogin());
    }

    /*
    Login dengan username dan password yang kosong
     */
    @Step("Super Admin mengirim request dengan memasukkan password yang salah")
    public void sendEmptyDataLoginSuperadmin() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "");
        requestBody.put("password", "");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiSuperAdminLogin());
    }

    @Step("Super Admin menerima pesan kesalahan")
    public void errorMessageValidation() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("invalid validation")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

}
