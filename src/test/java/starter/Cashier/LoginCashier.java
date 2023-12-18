package starter.Cashier;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.FileCashier;
import starter.utils.GenerateTokenAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlCashier;

public class LoginCashier {

    @Step("Kasir mengatur Endpoint untuk login")
    public String apiCashierLogin() {
        return urlCashier + "login";
    }

    @Step("Kasir memasukkan username yang benar dan password yang benar")
    public void alreadyRegLogin() {
        JSONObject requestBody = FileCashier.loginCashier();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiCashierLogin());
    }

    @Step("Kasir menerima data yang valid")
    public void receiveValidDataForLoginCashier(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_CASHIER_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'token'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Kasir memasukkan username yang salah untuk login
     */

    @Step("Kasir memasukkan username yang salah")
    public void invalidUnameLoginCashier(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "!@#$%#!@#!@#$");
        requestBody.put("password","12345678");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiCashierLogin());
    }
    @Step("Kasir menerima pesan gagal login")
    public void errorMessageLoginCashier() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

/*
Kasir memasukkan username yang salah untuk login
 */
    @Step("Kasir memasukkan password yang salah")
    public void invalidPwordLoginCashier(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "RakaQE");
        requestBody.put("password","ahahahahaha");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .post(apiCashierLogin());
    }

}
