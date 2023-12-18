package starter.PaymentMethod;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.GetterPaymentMethod;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static starter.URL.urlPaymentMethod;

public class DeletePaymentMethod {

    @Step("Admin mengakses menggunakan endpoint yang valid untuk menghapus payment method")
    public String apiDeletePaymentMethod() {
        return urlPaymentMethod;
    }

    @Step("Admin send request untuk menghapus payment method")
    public void sendRequestDeletePaymentMethod() {
        int lastPaymentMethodID = GetterPaymentMethod.getLastPaymentMethodID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlPaymentMethod + lastPaymentMethodID);
    }

    @Step("Payment Method berhasil dihapus")
    public void deletePaymentMethodSuccess() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_PAYMENT_METHOD_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("successfully delete data payment method")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
