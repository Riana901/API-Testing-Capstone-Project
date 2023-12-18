package starter.ProductType;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GetterProductType;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static starter.URL.urlProductType;

public class DeleteProductType {
    @Step("Admin mengatur API Endpoint untuk menghapus data tipe produk")
    public String apiDeleteProductTypeID(){
        return urlProductType;
    }

    @Step("pengguna mengakses endpoint untuk menghapus Product Type dengan ID tersebut")
    public void requestDeleteProductTypeByID(){
        int lastProductTypeID = GetterProductType.getLastProductTypeID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlProductType + lastProductTypeID);
    }

    @Step("Pengguna mendapatkan pesan untuk produk yang sudah terhapus")
    public void deleteProductTypeByID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_PRODUCT_TYPE_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("succesfully delete data product type")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Delete Product Type dengan id yang tidak valid
    */
    @Step("pengguna mengakses endpoint untuk menghapus Product Type dengan ID yang tidak valid")
    public void requestDeleteProductTypeInvalidID(){
        int lastProductTypeID = GetterProductType.getLastProductTypeID();
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlProductType + lastProductTypeID+1);
    }



}
