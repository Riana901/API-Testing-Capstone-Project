package starter.Product;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GetterProduct;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static starter.URL.urlProduct;

public class GetProductByID {

    @Step("Admin mengakses dengan endpoint yang valid untuk Get Product By ID")
    public String apiGetProductByID() {
        return urlProduct;
    }

    @Step("Admin send request untuk Get Product by ID")
    public void sendRequestGetProductByID() {
        int lastProductID = GetterProduct.getLastProductID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlProduct + lastProductID);
    }

    @Step("Data produk berdasarkan ID berhasil ditampilkan")
    public void productByIDDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PRODUCT_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productTypeId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'typename'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'productType'.'typeDescription'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'ProductDetail'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'adminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'admin'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'admin'.'superAdminID'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'admin'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'admin'.'username'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'name'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'ingredients'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'image'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
/*
Invalid Get Product By ID
 */

    @Step("Admin send request Get Product by ID dengan ID yang invalid")
    public void sendRequestGetProductByIDInvalid() {
        int invalidLastProductID = GetterProduct.getLastProductID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlProduct + invalidLastProductID+1);
    }

    @Step("Data Produk gagal ditampilkan")
    public void productByIDInvalidDisplayed() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PRODUCT_BY_WRONG_ID_RESPONSE_SCHEMA);

        restAssuredThat((response -> response.body("'meta'.'success'", is(false))));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
