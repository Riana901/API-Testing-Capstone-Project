package starter.PaymentMethod;

import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GetterPaymentMethod;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static starter.URL.urlPaymentMethod;

public class CreatePaymentMethod {
    @Step("Admin mengakses menggunakan endpoint yang valid untuk create payment method")
    public String apiCreatePaymentMethod() {
        return urlPaymentMethod;
    }

    @Step("Admin send request untuk membuat payment method baru")
    public void sendRequestCreatePaymentMethod() {
        String token = GenerateTokenAdmin.generateTokenAdmin();
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String name = faker.name().name();

        requestBody.put("paymentTyoeID", 2);
        requestBody.put("name", name);

        int lastPaymentMethodID = GetterPaymentMethod.getLastPaymentMethodID();
        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(apiCreatePaymentMethod());
    }

    @Step("Payment method baru telah dibuat")
    public void paymentMethodCreated() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.CREATE_PAYMENT_METHOD_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("success create payment method")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentTypeId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Create Payment Method Invalid
     */

    @Step("Admin send request dengan data yang invalid untuk membuat payment method baru")
    public void sendRequestCreatePaymentMethodInvalid() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("paymentTyoeID","");
        requestBody.put("name","");

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiCreatePaymentMethod());
    }

    @Step("Payment Method gagal dibuat")
    public void invalidCreatePaymentMethod() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema =  helper.getResponseSchema(JsonSchema.CREATE_PAYMENT_METHOD_INVALID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("invalid input")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));

    }
}
