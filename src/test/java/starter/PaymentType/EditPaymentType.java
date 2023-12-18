package starter.PaymentType;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlPaymentType;
import static starter.URL.urlProductType;

public class EditPaymentType {
    @Step("Admin mengatur API Endpoint untuk mengedit data tipe pembayaran")
    public String apiEditPaymentType(){return urlPaymentType;}

    @Step("Admin mengirimkan data yang valid untuk perubahan, seperti nama tipe pembayaran yang baru")
    public void requestEditPaymentType(){
        int lastPaymentTypeID = GetterPaymentType.getLastPaymentTypeID();
        JSONObject requestBody = new JSONObject();

        requestBody.put("typeName", "Dana");

        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlPaymentType + lastPaymentTypeID);
    }

    @Step("sistem seharusnya mengganti data tipe pembayaran yang sesuai dengan permintaan Admin")
    public void paymentTypeEdited(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.ADD_PAYMENT_TYPE_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("successfully updated data payment type")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'typeName'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("Admin mengakses endpoint untuk mengubah data tipe pembayaran dengan ID yang tidak valid")
    public void updatePaymentTypeInvalidID(){
        int lastPaymentTypeID = GetterPaymentType.getLastPaymentTypeID();
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String paymentName = faker.business().creditCardType();

        requestBody.put("typeName", paymentName);

        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlPaymentType + lastPaymentTypeID+100);
    }



    @Step("Admin mengakses endpoint untuk mengubah data tipe pembayaran berdasarkan ID dengan typeName yang tidak valid")
    public void updatePaymentTypeInvalidData(){
        int lastPaymentTypeID = GetterPaymentType.getLastPaymentTypeID();
        JSONObject requestBody = new JSONObject();

        requestBody.put("typeName", " ");

        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlPaymentType + lastPaymentTypeID+1);
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan")
    public void msgErrorInvalidDataPaymentType(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("payment type not found")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



}


