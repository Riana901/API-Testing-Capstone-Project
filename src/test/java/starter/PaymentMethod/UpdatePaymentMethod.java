package starter.PaymentMethod;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GetterPaymentMethod;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlPaymentMethod;

public class UpdatePaymentMethod {

    @Step("Admin mengakses menggunakan endpoint yang valid untuk Update Payment Method")
    public String apiUpdatePaymentMethod() {
        return urlPaymentMethod;
    }

    @Step("Admin send request untuk Update Payment Method")
    public void sendRequestUpdatePaymentMethod() {
        String token = GenerateTokenAdmin.generateTokenAdmin();

        JSONObject requestBody = new JSONObject();
        int lastPaymentMethodID = GetterPaymentMethod.getLastPaymentMethodID();
        requestBody.put("point", 5);
        requestBody.put("valuePoint", 5000);

        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlPaymentMethod + lastPaymentMethodID);
    }

    @Step("Update Payment Method berhasil")
    public void updatePaymentMethodSuccess() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.UPDATE_PAYMENT_METHOD_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentTypeId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'paymentType'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Update Convert Point Invalid
     */

    @Step("Admin send request untuk update payment method menggunakan ID yang invalid")
    public void sendRequestInvalidUpdatePaymentMethod() {
        String token = GenerateTokenAdmin.generateTokenAdmin();

        JSONObject requestBody = new JSONObject();
        int lastPaymentMethodID = GetterPaymentMethod.getLastPaymentMethodID();
        requestBody.put("point", 5);
        requestBody.put("valuePoint", 5000);

        SerenityRest.given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlPaymentMethod + lastPaymentMethodID+1);
    }

    @Step("Update Payment Method gagal")
    public void updatePaymentMethodFailed() {
    }
}
