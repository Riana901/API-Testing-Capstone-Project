package starter.PaymentMethod;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.GetterPaymentMethod;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlPaymentMethod;

public class GetPaymentMethodByID {

    @Step("Admin mengakses menggunakan endpoint yang valid untuk Get Payment Method By ID")
    public String apiGetPaymentMethodByID() {
        return urlPaymentMethod;
    }

    @Step("Admin send request untuk Get All Payment Method By ID")
    public void sendRequestGetAllPaymentMethodByID() {
        int lastPaymentMethodID = GetterPaymentMethod.getLastPaymentMethodID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlPaymentMethod + lastPaymentMethodID);
    }

    @Step("Payment Method sesuai ID berhasil ditampilkan")
    public void paymentMethodByIDDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PAYMENT_METHOD_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentTypeId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Get Payment Method By ID Invalid ID
     */

    @Step("Admin send request menggunakan ID yang salah pada Get Payment Method by ID")
    public void sendRequestInvalidIDPaymentMethod() {
        int invalidGetPaymentMethodID = GetterPaymentMethod.getLastPaymentMethodID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlPaymentMethod + invalidGetPaymentMethodID+1);
    }

    @Step("Payment Method sesuai ID gagal ditampilkan")
    public void paymentMethodByIDNotDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PAYMENT_METHOD_INVALID_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
