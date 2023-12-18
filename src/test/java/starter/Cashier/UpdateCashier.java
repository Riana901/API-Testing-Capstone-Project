package starter.Cashier;

import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlAdmin;
import static starter.URL.urlCashier;

public class UpdateCashier {
    @Step("Kasir mengatur endpoint API untuk update kasir")
    public String apiEndUpdateCashier(){
        return urlCashier;
    }

    @Step("Kasir mengakses endpoint untuk memperbarui data akun dengan data yang valid")
    public void requestUpdateCashier(){
        String token = GenerateTokenAdmin.generateTokenAdmin();

        JSONObject requestBody = new JSONObject();
        int lastCashierID = GetterCashier.getLastCashierID();
        int lastAdminID = GetterAdmin.getLastAdminID();

        Faker faker = new Faker();
        String fullname = faker.name().lastName();
        String username = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("AdminID", lastAdminID);
        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlCashier + lastCashierID);
    }

    @Step("sistem seharusnya memberikan respon yang memuat data akun yang telah diupdate")
    public void updateCashierDataByID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_CASHIER_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'adminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



    /*
    Kasir mengupdate data akun dengan data yang tidak valid
     */

    @Step("Kasir mengakses endpoint untuk memperbarui data akun Admin dengan data yang tidak valid")
    public void requestUpdateCashierInvalidData(){

        JSONObject requestBody = new JSONObject();
        int lastCashierID = GetterCashier.getLastCashierID();
        int lastAdminID = GetterAdmin.getLastAdminID();
        Faker faker = new Faker();
        String fullname = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("AdminID", lastAdminID);
        requestBody.put("fullname", fullname);
        requestBody.put("username", "@!#@!*#&!@)");
        requestBody.put("password", password);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlCashier + lastCashierID);
    }


    /*
    Kasir Update dengan ID yang tidak valid
     */
    @Step("Admin mengakses endpoint untuk memperbarui data akun Admin dengan ID yang tidak valid")
    public void requestUpdateCashierInvalidID(){

        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String fullname = faker.name().lastName();
        String username = faker.name().username();
        String password = faker.internet().password();
        int lastCashierID = GetterCashier.getLastCashierID();
        int lastAdminID = GetterAdmin.getLastAdminID();

        requestBody.put("adminID", lastAdminID);
        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlAdmin + lastCashierID+1);
    }

}
