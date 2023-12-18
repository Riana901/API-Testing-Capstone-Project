package starter.PaymentType;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.*;

public class GetPaymentTypeByID {

    @Step("Admin mengatur API Endpoint untuk mendapatkan Payment Type berdasarkan id")
    public String apiEndGetPaymentTypeID(){
        return urlPaymentType;
    }

    @Step("Admin mengakses endpoint untuk mendapatkan informasi Payment Type berdasarkan ID")
    public void requestGetPaymentTypeByID(){
        int lastPaymentTypeID = GetterPaymentType.getLastPaymentTypeID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlPaymentType + lastPaymentTypeID);
    }

    @Step("sistem seharusnya menampilkan informasi lengkap tentang Payment Type tersebut")
    public void getPaymentTypeID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PAYMENT_TYPE_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    @Step("Admin mengakses endpoint untuk mendapatkan informasi Payment Type dengan ID yang tidak tersedia")
    public void requestGetPaymentTypeInvalidID(){
        int lastPaymentTypeID = GetterPaymentType.getLastPaymentTypeID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlPaymentType + lastPaymentTypeID+ 1);
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan untuk Payment Type yang tidak ditemukan")
    public void paymentTypeNotFound(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


}