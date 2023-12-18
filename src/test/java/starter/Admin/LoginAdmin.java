package starter.Admin;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.FileAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlAdmin;

public class LoginAdmin {



    @Step("Admin mengakses API Endpoint untuk login")
    public String apiAdminLogin() {
        return urlAdmin + "login";
    }

    @Step("Admin memasukkan username dan password yang valid")
    public void sendValidLoginAdmin() {
        JSONObject requestBody = FileAdmin.getLoginAdmin();

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiAdminLogin());
    }

    @Step("Admin menerima data login ")
    public void receiveValidDataForLoginAdmin(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_ADMIN_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'token'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Login dengan username Invalid
     */

    @Step("Admin memasukkan username yang tidak valid")
    public void invalidUnameLoginAdmin(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "!@#$%#!@#!@#$");
        requestBody.put("password","12345678");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiAdminLogin());
    }

    @Step("Admin menerima pesan kesalahan")
    public void errorMessageLoginAdmin() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Login dengan invalid Password
     */

    @Step("Admin memasukkan password yang tidak valid")
    public void invalidPword(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "arieflazuardi3456");
        requestBody.put("password","ahahahaha");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiAdminLogin());

    }





}
