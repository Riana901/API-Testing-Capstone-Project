package starter.Membership;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenCashier;
import starter.utils.GetterMembership;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.*;

public class GetAllMembership {
    @Step("User mengatur endpoint yang valid untuk melihat seluruh data membership")
    public String ApiEndGetAllMembership(){return urlMemberships;}

    @Step("User mengirimkan request untuk meminta seluruh data membership")
    public void requestGetAllMemberships(){
        String token = GenerateTokenCashier.generateTokenCashier();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlMemberships);
    }
    @Step("Seluruh data membership ditampilkan sebagai respon")
    public void getAllMemberships(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_MEMBERSHIP_RESPONSE_SCHEMA);
        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("total", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'cashierId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'codeMember'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'point'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'phoneNumber'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }






}