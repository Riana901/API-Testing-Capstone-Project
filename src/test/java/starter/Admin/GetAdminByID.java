package starter.Admin;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.GenerateTokenSuperAdmin;
import starter.utils.GetterAdmin;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlAdmin;

public class GetAdminByID {

    @Step("Super Admin mengakses API Endpoint untuk mendapatkan data admin by ID")
    public String apiEndGetAdminID(){
        return urlAdmin;
    }

    @Step("Super Admin mengirim request untuk mendapatkan Admin by id")
    public void requestGetAdminByID(){
        int lastAdminID = GetterAdmin.getLastAdminID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlAdmin + lastAdminID);
    }

    @Step("sistem seharusnya menampilkan informasi lengkap tentang Admin tersebut")
    public void getAdminDataByID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ADMIN_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'superAdminId'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Get Admin dengan invalid ID
     */
    @Step("Super Admin mengakses endpoint untuk mendapatkan informasi dengan ID yang tidak valid")
    public void invalidIDReq(){
        int invalidAdminID = GetterAdmin.getLastAdminID()+1;
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get(urlAdmin + invalidAdminID+3);
    }

    @Step("sistem seharusnya menampilkan pesan kesalahan untuk admin yang tidak ditemukan")
    public void dataAdminNotFound(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("admin not found")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



}
