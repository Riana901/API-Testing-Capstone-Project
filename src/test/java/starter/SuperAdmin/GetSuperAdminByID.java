package starter.SuperAdmin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlAdmin;

public class GetSuperAdminByID {
    private String url = "https://qbills.biz.id/api/v1/super-admin/";
    @Step("Super Admin mengakses endpoint untuk mendapatkan super admin dengan id")
    public String apiGetSuperAdmin() {
        String urlByid = url ;
        return urlByid + "1" ;
    }

    @Step("Super Admin melakukan permintaan untuk mendapatkan data dirinya sendiri dengan menggunakan ID tersebut")
    public void requestGetSuperAdminByID(){
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(apiGetSuperAdmin());
    }
    @Step("sistem seharusnya menampilkan informasi lengkap tentang admin dengan username tersebut")
    public void getSuperAdminData(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_SUPER_ADMIN_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("Successfully Get Data Super Admin")));
        restAssuredThat(response -> response.body("'results'.'id'", notNullValue()));
        restAssuredThat(response -> response.body("'results'.'username'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }



    /*
    Super Admin mencoba mendapatkan data dengan ID yang tidak ada
     */
    @Step("Super Admin melakukan permintaan untuk mendapatkan data Super Admin dengan menggunakan ID yang tidak ada")
    public void requestGetSuperAdminByInvalidID(){
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(url + "2");
    }

    @Step("Super Admin menerima pesan kesalahan validasi ID")
    public void errorMessageValidation() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(false)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("Get Super Admin data error")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
