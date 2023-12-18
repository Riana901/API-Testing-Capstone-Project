package starter.Cashier;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlCashier;

public class GetCashierByID {

    @Step("Admin mengakses endpoint untuk mendapatkan data kasir berdasarkan ID")
    public String apiEndGetAdminID(){
        return urlCashier;
    }

    @Step("Admin mengirim request untuk mendapatkan kasir berdasarkan ID")
    public void requestGetCashierByID(){
        int lastCashierID = GetterCashier.getLastCashierID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlCashier + lastCashierID);
    }
    @Step("sistem seharusnya menampilkan informasi lengkap mengenai kasir tersebut")
    public void getAdminDataByID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_CASHIER_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'adminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



    /*
    Admin mencoba mendapatkan data dengan ID yang tidak valid
     */
    @Step("Admin mengirim request untuk mendapatkan kasir berdasarkan ID yang tidak valid")
    public void requestGetCashierByInvalidID(){
        int lastCashierID = GetterCashier.getLastCashierID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlCashier + lastCashierID+1);
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan untuk kasir tidak ditemukan")
    public void cashierNotFoundData(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

}
