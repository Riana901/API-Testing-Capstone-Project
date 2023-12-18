    package starter.PaymentType;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateTokenAdmin;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlPaymentType;


public class AddPaymentType {

    @Step("Admin mengatur API Endpoint untuk menambah tipe pembayaran baru")
    public String apiEndAddPaymentType(){
        return urlPaymentType;
    }

    @Step("Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru dengan data valid")
    public void requestAddPaymentType(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("typeName", "Go-Pay");

        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiEndAddPaymentType());
    }

    @Step("sistem seharusnya menampilkan pesan sukses untuk Payment Type berhasil ditambahkan")
    public void msgAddPaymentType(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.ADD_PAYMENT_TYPE_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("successfully created payment type")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Negative
     */
    @Step("Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru tanpa memasukkan data yang diperlukan")
    public void reqAddPaymentTypeWithNoData(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("typeName","");
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiEndAddPaymentType());
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan untuk Data tipe pembayaran yang diperlukan tidak lengkap")
    public void msgErrorNoDataAddPT(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("create payment type error")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    @Step("Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru dengan typeName yang sudah ada")
    public void reqAddPaymentTypeWithAddedData(){
        JSONObject requestBody = new JSONObject();

        requestBody.put("typeName","Cash");
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(apiEndAddPaymentType());
    }

    @Step("ssistem seharusnya menampilkan pesan kesalahan untuk Payment Type dengan typeName tersebut sudah ada")
    public void msgErrorAddPaymentType(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }







}
