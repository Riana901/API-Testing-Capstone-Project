package starter.PaymentType;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.*;

public class DeletePaymentType {
    @Step("Admin mengatur API Endpoint untuk menghapus data tipe pembayaran")
    public String apiDeletePaymentTypeID(){
        return urlPaymentType;
    }

    @Step("Admin mengakses endpoint untuk menghapus Payment Type dengan ID tersebut")
    public void requestDeletePaymentTypeByID(){
        int lastPaymentTypeID = GetterPaymentType.getLastPaymentTypeID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlPaymentType + lastPaymentTypeID);
    }

    @Step("Admin mendapatkan pesan untuk tipe pembayaran yang sudah terhapus")
    public void deletePaymentTypeByID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_PAYMENT_TYPE_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("successfully delete data payment type")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



    @Step("Admin mengakses endpoint untuk menghapus Payment Type dengan ID yang tidak valid")
    public void requestDeletePaymentTypeInvalidID(){
        int lastPaymentTypeID = GetterPaymentType.getLastPaymentTypeID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlPaymentType + lastPaymentTypeID+1);



    }




}