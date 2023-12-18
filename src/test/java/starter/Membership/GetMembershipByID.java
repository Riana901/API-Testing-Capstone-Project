package starter.Membership;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;
import static starter.URL.urlmembership;

public class GetMembershipByID {

    /*Positive*/
    @Step("User mengatur endpoint untuk mendapatkan data membership berdasarkan ID")
    public String ApiEndGetMemberByID(){return urlmembership;}

    @Step("User mengirim request untuk mendapatkan data membership berdasarkan ID yang valid")
    public void requestGetMembershipByID(){
        String token = GenerateTokenCashier.generateTokenCashier();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlmembership + 9);
    }
    @Step("Data membership dengan ID yang diminta ditampilkan sebagai respon")
    public void getMemberByID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_MEMBERSHIP_BY_ID_RESPONSE_SCHEMA);
        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("Successfully get data membership")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'cashierId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'codeMember'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'totalPoint'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'phoneNumber'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
    /*Negative*/
    @Step("User mengirimkan request untuk mendapatkan data membership berdasarkan ID yang tidak valid")
    public void requestGetMemberInvalidID(){
        String token = GenerateTokenCashier.generateTokenCashier();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlmembership + 12345679);

    }

}