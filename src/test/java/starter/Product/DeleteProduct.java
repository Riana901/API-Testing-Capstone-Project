package starter.Product;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.GetterProduct;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static starter.URL.urlProduct;

public class DeleteProduct {
    @Step("Admin memasukkan endpoint yang valid untuk menghapus produk")
    public String setApiEndpointDeleteProduct() {
        return urlProduct;
    }

    @Step("Admin send request untuk menghapus product")
    public void sendRequestDeleteProduct() {
        int lastProductID = GetterProduct.getLastProductID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlProduct + lastProductID);
    }

    @Step("Produk berhasil dihapus")
    public void productDeleted() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_PRODUCT_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("succesfully delete data product")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    /*
    Delete Product Invalid
     */

    @Step("Admin send request untuk menghapus product dengan ID invalid")
    public void sendRequestDeleteProductInvalid() {
        int lastProductID = GetterProduct.getLastProductID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlProduct + lastProductID+1);
    }

    @Step("Product gagal dihapus")
    public void productInvalidDelete() {
    }
}
