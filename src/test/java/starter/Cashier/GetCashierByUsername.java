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
import static starter.URL.urlAdmin;
import static starter.URL.urlCashier;

public class GetCashierByUsername {
    @Step("Admin mengakses endpoint untuk mendapatkan informasi kasir berdasarkan username")
    public String apiEndGetCashierUname(){
        return urlCashier;
    }

    @Step("Admin mengirim request untuk mendapatkan kasir berdasarkan Username")
    public void requestGetCashierByUname(){
        String lastCashierUname = GetterCashier.getLastCashierUname();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlCashier + "username/" + lastCashierUname);
    }

    @Step("sistem seharusnya menampilkan informasi lengkap tentang kasir tersebut")
    public void getCashierDataByUname(){
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
    Admin mencoba mendapatkan informasi dengan username yang tidak valid
     */

    @Step("Kasir mengakses endpoint untuk mendapatkan informasi dengan username yang tidak valid")
    public void requestGetCashierByInvUname(){
        String lastCashierUname = GetterCashier.getLastCashierUname();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlCashier + "username/" + lastCashierUname + "cahyono");
    }
}
