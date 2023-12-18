package starter.Membership;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.*;

public class UpdateMembership {

    @Step("User mengatur endpoint dengan valid untuk melakukan update data membership")
    public String ApiEndUpdateMembership(){return urlmembership;}

    @Step("User mengirimkan request untuk melakukan update data membership dengan data yang valid")
    public void requestUpdateMembership(){
        String token = GenerateTokenAdmin.generateTokenAdmin();
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String name = faker.name().name();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        requestBody.put("name", name);
        requestBody.put("phoneNumber", "088989089878"); /*Ganti sebelum test run*/

        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlmembership + 50);
    }

    @Step("Sistem memberikan data membership yang telah di update sebagai respon")
    public void updateMembership(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_MEMBERSHIP_BY_ID_RESPONSE_SCHEMA);
        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'cashierId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'codeMember'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'totalPoint'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'phoneNumber'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
    @Step("User mengirim request untuk update data membership tanpa mengisi data nama dan number phone")
    public void requestInvalidBodyUpdateMembership(){
        String token = GenerateTokenAdmin.generateTokenAdmin();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", " ");
        requestBody.put("phoneNumber", " ");


        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlmembership + 30);
    }
    @Step("User mengirimkan request untuk melakukan update data membership dengan invalid ID")
    public void requestUpdateMembershipWithInvalidID(){
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String name = faker.name().name();
        String phoneNumber = faker.phoneNumber().phoneNumber();

        requestBody.put("name", name);
        requestBody.put("phoneNumber", "998877665544");

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlmembership + 100);

    }
}
