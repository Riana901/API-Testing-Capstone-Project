package starter.Cashier;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlCashier;

public class RegisterCashier {

    @Step("Kasir mengatur endpoint API untuk register")
    public String apiCashierRegister() {
        return urlCashier + "register";
    }

    @Step("Kasir mendaftarkan akun dengan data yang valid")
    public void validCashierRegister() {
        JSONObject requestBody = new JSONObject();
        Faker faker = new Faker();
        int lastAdminID = GetterAdmin.getLastAdminID();

        String fullname = faker.name().lastName();
        String username = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("AdminID", lastAdminID);
        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiCashierRegister());
    }

    @Step("sistem seharusnya menampilkan data Admin yang baru terdaftar")
    public void okRegisterCashier() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.REGISTER_CASHIER_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'adminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Pendaftaran Kasir tanpa username yang diperlukan
     */
    @Step("Kasir mencoba mendaftarkan akun Kasir tanpa menyertakan username")
    public void invalidUnameRegister() {
        JSONObject requestBody = new JSONObject();
        Faker faker = new Faker();
        int lastAdminID = GetterAdmin.getLastAdminID();

        String fullname = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("AdminID", lastAdminID);
        requestBody.put("fullname", fullname);
        requestBody.put("username", "");
        requestBody.put("password", password);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiCashierRegister());
    }

    @Step("sistem seharusnya menyertakan pesan error untuk sign up error")
    public void errorMessageRegisterCashier() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("sign up error")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Pendaftaran Kasir dengan AdminID yang tidak valid
     */
    @Step("Kasir mencoba mendaftarkan akun Kasir dengan AdminID yang tidak valid")
    public void invalidAdminIDRegister() {
        JSONObject requestBody = new JSONObject();
        Faker faker = new Faker();
        int lastAdminID = GetterAdmin.getLastAdminID();

        String fullname = faker.name().lastName();
        String username = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("AdminID", lastAdminID+1);
        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiCashierRegister());
    }

    /*
    Kasir mencoba mendaftarkan akun Kasir dengan Username yang tidak valid
     */
    @Step("Kasir mencoba mendaftarkan akun Kasir dengan AdminID yang tidak valid")
    public void freakRegister() {
        JSONObject requestBody = new JSONObject();
        Faker faker = new Faker();
        int lastAdminID = GetterAdmin.getLastAdminID();

        String fullname = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("AdminID", lastAdminID+1);
        requestBody.put("fullname", fullname);
        requestBody.put("username", "!@#!@#!@$%%@#!@#!%$");
        requestBody.put("password", password);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiCashierRegister());
    }

    @Step("sistem seharusnya menyertakan pesan error untuk sign up error")
    public void errorMessageInvUname() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("username is not valid must contain only alphanumeric characters")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



    /*
Pendaftaran Kasir dengan username yang sudah digunakan
     */

    @Step("Kasir mencoba mendaftarkan akun Kasir dengan username yang sudah digunakan")
    public void alreadyReg() {
        JSONObject requestBody = FileCashier.getCashier();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiCashierRegister());
    }
    @Step("sistem seharusnya menyertakan pesan error untuk sign up error karena username")
    public void errorMsgUnameUsed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("username already exist")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


}
