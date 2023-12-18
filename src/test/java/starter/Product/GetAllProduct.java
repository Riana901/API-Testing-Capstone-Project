package starter.Product;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlProduct;

public class GetAllProduct {
    @Step("Admin memasukkan endpoint untuk Get All Product")
    public String apiGetAllProduct() {
        return urlProduct;
    }

    @Step("Admin send request untuk Get All Product")
    public void sendRequestGetAllProduct() {
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(apiGetAllProduct());
    }

    @Step("Semua data produk berhasil ditampilkan")
    public void allDataDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_PRODUCT_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'total'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productTypeId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'typename'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'typeDescription'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'ingredients'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'image'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productDetail'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

}
