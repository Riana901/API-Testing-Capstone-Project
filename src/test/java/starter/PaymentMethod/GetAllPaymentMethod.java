package starter.PaymentMethod;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlPaymentMethod;

public class GetAllPaymentMethod {

    @Step("Admin mengakses menggunakan endpoint yang valid untuk Get All Payment Method")
    public String apiGetAllPaymentMethod() {
        return urlPaymentMethod;
    }

    @Step("Admin send request untuk Get All Payment Method")
    public void sendRequestGetAllPaymentMethod() {
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(apiGetAllPaymentMethod());
    }

    @Step("Semua Payment Method berhasil ditampilkan")
    public void allPaymentMethodDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_PAYMENT_METHOD_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentTypeID'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

}
