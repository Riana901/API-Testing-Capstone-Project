package starter.Product;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.*;
import static starter.URL.urlProduct;

public class GetProductByName {

    @Step("Admin memasukkan endpoint yang valid untuk Get Product By Name")
    public String apiGetProductByName() {
        return urlProduct + "kopi";
    }

    @Step("Admin send request untuk Get Product by Name dengan valid")
    public void sendRequestGetProductByName() {
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(apiGetProductByName());
    }

    @Step("Informasi produk sesuai nama berhasil ditampilkan")
    public void getProductByNameDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PRODUCT_BY_NAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'",is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'typeDescription'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'ingredients'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'image'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productDetail'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productDetail'.'id',", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productDetail'.'productId',", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productDetail'.'price',", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productDetail'.'totalStock',", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productDetail'.'size',", notNullValue()));


        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Get All Product By Name Invalid
     */

    @Step("Admin memasukkan endpoint yang valid untuk Get Product By Name")
    public String apiGetProductByNameInvalid() {
        return urlProduct + "susu";
    }
    @Step("Admin send request untuk Get Product by Name dengan nama yang salah")
    public void sendRequestGetProductByNameInvalid() {
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(apiGetProductByNameInvalid());
    }

    @Step("Informasi produk sesuai nama gagal ditampilkan")
    public void productByNameNotDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PRODUCT_BY_NAME_INVALID_RESPONSE_SCHEMA);

        restAssuredThat((response -> response.body("'meta'.'success'", is(true))));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'", nullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
