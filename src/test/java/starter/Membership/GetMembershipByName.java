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

public class GetMembershipByName {

    @Step("User mengatur endpoint untuk meminta data membership berdasarkan nama")
    public String ApiEndGetMembershipByName(){return urlmembership;
    }

    @Step("User mengirim request untuk mendapatkan data membership berdasarkan nama yang valid")
    public void requestMembershipByName(){
        String linkName = "https://qbills.biz.id/api/v1/membership/name/";
        String token = GenerateTokenCashier.generateTokenCashier();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(linkName + "Fanti");
    }
    @Step("Data membership dengan nama yang diminta ditampilkan sebagai respon")
    public void getMembershipByName(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_MEMBERSHIP_BY_ID_RESPONSE_SCHEMA);
        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("succesfully get membership data by name")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'chasierId'", nullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'codeMember'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'totalPoint'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'phoneNumber'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*Negative Case*/
    @Step("User mengirimkan request untuk mendapatkan data membership berdasarkan nama yang invalid")
    public void requestMembershipByInvalidName(){
        String linkname = "https://qbills.biz.id/api/v1/membership/name/";
        String token = GenerateTokenCashier.generateTokenCashier();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(linkname + "HALAHRAONOK" );
    }

}
