package starter.ProductType;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateTokenAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlProductType;

public class AddProductType {

    @Step("Admin mengatur API Endpoint untuk menambah tipe produk baru")
    public String apiEndAddProductType(){
        return urlProductType;
    }

    @Step("Admin mengirimkan permintaan untuk menambahkan tipe produk baru dengan data valid")
    public void requestAddProductType(){
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String mealName = faker.food().dish();

        requestBody.put("typeName", mealName);
        requestBody.put("typeDescription", "Meal for your lonely night at the cafe");

        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiEndAddProductType());
    }

    @Step("sistem seharusnya menampilkan pesan sukses untuk Product Type berhasil ditambahkan")
    public void msgAddProductType(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.ADD_PRODUCT_TYPE_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("success create product type")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeDescription'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Add Product Type tanpa data yang diperlukan
     */
    @Step("Admin mengirimkan permintaan untuk menambahkan tipe produk baru tanpa memasukkan data yang diperlukan")
    public void reqAddProductTypeWithNoData(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("typeName","");
        requestBody.put("typeDescription", "");
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiEndAddProductType());
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan untuk Data yang diperlukan tidak lengkap")
    public void msgErrorNoDataAddPT(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("failed to create product type")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



    /*
    Add Product Type dengan data yang sudah ada
     */
    @Step("Admin mengirimkan permintaan untuk menambahkan tipe produk baru dengan typeName yang sudah ada")
    public void reqAddProductTypeWithAddedData(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("typeName","minuman");
        requestBody.put("typeDescription", "mata air beta");
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiEndAddProductType());
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan untuk Product Type dengan typeName tersebut sudah ada")
    public void msgErrorAddedDataAddPT(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }







}
