package starter.PaymentType;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.*;

public class GetAllPaymentType {

    @Step("Admin mengatur API Endpoint untuk mendapatkan semua data Payment Type")
    public String apiEndGetAllPaymentType(){
        return urlPaymentType;
    }

    @Step("Admin mengakses endpoint untuk mendapatkan semua Payment Type")
    public void requestGetAllPaymentType(){
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(apiEndGetAllPaymentType());
    }

    @Step("sistem seharusnya menampilkan daftar semua Payment Type yang tersedia")
    public void msgGetAllPaymentType(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_PAYMENT_TYPE_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


}