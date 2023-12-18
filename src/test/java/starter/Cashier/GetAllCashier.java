package starter.Cashier;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;

import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlCashier;


public class GetAllCashier {
    @Step("Admin mengakses endpoint untuk mendapatkan semua Kasir")
    public String apiGetAllCashier() {
        return urlCashier;
    }


    @Step("Admin mengirim request untuk mendapatkan semua kasir")
    public void getAllCashierData(){
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(apiGetAllCashier());
    }
    @Step("sistem seharusnya menampilkan daftar semua Kasir yang tersedia")
    public void allCashierData(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_CASHIER_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'total'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'adminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


}
