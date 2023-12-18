package starter.ProductType;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlProductType;

public class GetAllProductType {
    @Step("Admin mengatur API Endpoint untuk mendapatkan semua data tipe produk")
    public String apiEndGetAllProductType(){
        return urlProductType;
    }

    @Step("Admin mengakses endpoint untuk mendapatkan semua tipe produk")
    public void requestGetAllProductType(){
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(apiEndGetAllProductType());
    }

    @Step("sistem seharusnya menampilkan pesan sukses untuk Product Type berhasil ditambahkan")
    public void msgGetAllProductType(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_PRODUCT_TYPE_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeDescription'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



}
