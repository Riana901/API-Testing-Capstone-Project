package starter.Admin;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.FileAdmin;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlAdmin;
import static starter.utils.FileAdmin.getRegisterAdmin;

public class RegisterAdmin {

    @Step("Admin mengakses endpoint untuk mendaftarkan diri")
    public String apiAdminRegister() {
        return urlAdmin + "register";
    }

    @Step("Admin mengakses endpoint untuk mendaftarkan Admin dengan data yang valid")
    public void validAdminRegister() {
        JSONObject requestBody = new JSONObject();
        Faker faker = new Faker();
        String fullname = faker.name().lastName();
        String username = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("superAdminID", 1);
        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiAdminRegister());
    }

    @Step("sistem seharusnya menampilkan data Admin yang baru terdaftar")
    public void okRegisterAdmin() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.REGISTER_ADMIN_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'superAdminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Register dengan username yang sudah ada
     */
    @Step("Admin mendaftarkan diri dengan data yang memiliki username yang sudah ada")
    public void registeredAccount() {
        JSONObject requestBody = FileAdmin.getRegisterAdmin();

        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiAdminRegister());
    }

    @Step("Admin menerima pesan kesalahan")
    public void errorMessageRegisterAdmin() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("username already exist")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Admin mengakses endpoint untuk mendaftarkan Admin dengan username yang tidak valid
     */
    @Step("Admin mengakses endpoint untuk mendaftarkan Admin dengan username yang tidak valid")
    public void invalidDataRegisterAdmin(){
        JSONObject requestBody = new JSONObject();
        Faker faker = new Faker();
        String fullname = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("superAdminID", 1);
        requestBody.put("fullname", fullname);
        requestBody.put("username", "!@#@$!@$!%%!@#@!#!");
        requestBody.put("password", password);

        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiAdminRegister());
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan validasi")
    public void errorValidationData(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


}

