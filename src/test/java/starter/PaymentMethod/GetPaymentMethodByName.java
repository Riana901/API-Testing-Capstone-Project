package starter.PaymentMethod;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlPaymentMethod;

public class GetPaymentMethodByName {

    @Step("Admin mengakses menggunakan endpoint yang valid untuk Get Payment Method By Name")
    public String apiGetPaymentMethodByName() {
        return urlPaymentMethod + "tess";
    }

    @Step("Admin send request untuk Get Payment Method by Name")
    public void sendRequestGetPaymentMethodByID() {
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(apiGetPaymentMethodByName());
    }

    @Step("Payment Method sesuai nama berhasil ditampilkan")
    public void paymentMethodByNameDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PAYMENT_METHOD_BY_NAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productTypeId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Get Payment Method By Name Invalid Name
     */
    @Step("Admin mengakses menggunakan endpoint yang valid untuk Get Payment Method By Name")
    public String apiGetPaymentMethodByNameInvalid() {
        return urlPaymentMethod + "tessssssss";
    }
    @Step("Admin send request menggunakan nama yang invalid untuk Get Payment Method By Name")
    public void sendRequestGetPaymentMethodByName() {
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer" + token)
                .header("Content-Type", "application/json")
                .get(apiGetPaymentMethodByNameInvalid());
    }

    @Step("Payment Method sesuai nama gagal ditampilkan")
    public void paymentMethodByNameNotDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PAYMENT_METHOD_BY_NAME_INVALID_RESPONSE_SCHEMA);

        restAssuredThat((response -> response.body("'meta'.'success'", is(false))));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
