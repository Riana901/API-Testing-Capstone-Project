package starter.ProductType;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlProductType;

public class GetProductTypeByID {
    @Step("Admin mengatur API Endpoint untuk mendapatkan tipe produk berdasarkan id")
    public String apiEndGetProductTypeID(){
        return urlProductType;
    }

    @Step("Admin mengakses endpoint untuk mendapatkan informasi tipe produk berdasarkan ID")
    public void requestGetProductTypeByID(){
        int lastProductTypeID = GetterProductType.getLastProductTypeID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlProductType + lastProductTypeID);
    }

    @Step("sistem seharusnya menampilkan informasi lengkap tentang tipe produk tersebut")
    public void getProductTypeID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PRODUCT_TYPE_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeDescription'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



    /*
    Get Product By Invalid ID
     */

    @Step("Admin mengakses endpoint untuk mendapatkan informasi tipe produk dengan ID yang tidak tersedia")
    public void requestGetProductTypeInvalidID(){
        int lastProductTypeID = GetterProductType.getLastProductTypeID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlProductType + lastProductTypeID+1);
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan untuk produk yang tidak ditemukan")
    public void productTypeNotFound(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



}
