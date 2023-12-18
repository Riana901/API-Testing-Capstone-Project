package starter.ProductType;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GetterProductType;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlProductType;

public class EditProductType {
    @Step("Admin mengatur API Endpoint untuk mengedit data tipe produk")
    public String apiEditProductType(){
        return urlProductType;
    }

    @Step("Admin mengirimkan data yang valid untuk perubahan, seperti nama tipe produk yang baru")
    public void requestEditProductType(){
        int lastProductTypeID = GetterProductType.getLastProductTypeID();
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String mealName = faker.food().dish();

        requestBody.put("typeName", mealName);
        requestBody.put("typeDescription", mealName);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlProductType + lastProductTypeID);
    }

    @Step("sistem seharusnya mengganti data tipe produk yang sesuai dengan permintaan Admin")
    public void productTypeEdited(){
            JsonSchemaHelper helper = new JsonSchemaHelper();
            String schema = helper.getResponseSchema(JsonSchema.ADD_PRODUCT_TYPE_RESPONSE_SCHEMA);

            restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
            restAssuredThat(response -> response.body("'meta'.'message'", is("success update product type")));
            restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
            restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));
            restAssuredThat(response -> response.body("'results'.'typeDescription'", notNullValue()));

            restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
        }



    /*
    Admin mencoba mengubah tipe produk yang tidak ada
     */

    @Step("Admin mengakses endpoint untuk mengubah data tipe produk dengan ID yang tidak valid")
    public void updateProductTypeInvalidID(){
        int lastProductTypeID = GetterProductType.getLastProductTypeID();
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String mealName = faker.food().dish();

        requestBody.put("typeName", mealName);
        requestBody.put("typeDescription", mealName);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlProductType + lastProductTypeID+1);
    }


    /*
    Update admin dengan data yang tidak valid
     */
    @Step("Admin mengakses endpoint untuk mengubah data tipe produk berdasarkan ID dengan typeName yang tidak valid")
    public void updateProductTypeInvalidData(){
        int lastProductTypeID = GetterProductType.getLastProductTypeID();
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String mealName = faker.food().dish();

        requestBody.put("typeName", "");
        requestBody.put("typeDescription", mealName);

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlProductType + lastProductTypeID+1);
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan validasi untuk typeName")
    public void msgErrorInvalidDataPT(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("invalid validation")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



}


