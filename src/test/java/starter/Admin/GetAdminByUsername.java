package starter.Admin;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlAdmin;

public class GetAdminByUsername {
    @Step("Super Admin mengakses API Endpoint untuk mendapatkan data admin berdasarkan username")
    public String apiEndGetAdminUname(){
        return urlAdmin;
    }

    @Step("Super Admin mengakses endpoint untuk mendapatkan informasi admin berdasarkan username yang ada")
    public void requestGetAdminByUname(){
        String firstAdminUname = GetterAdmin.getFirstAdminUname() ;
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlAdmin + "username/" + firstAdminUname);
    }


    @Step("sistem seharusnya menampilkan informasi lengkap tentang admin dengan username tersebut")
    public void getAdminDataByUname(){
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
Get Admin dengan username yang tidak ada
 */
    @Step("Super Admin mengakses endpoint untuk mendapatkan informasi admin berdasarkan username yang ada")
    public void requestGetAdminByInvalidUname() {
        String lastAdminUname = GetterAdmin.getLastAdminUname();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlAdmin + "username/" + lastAdminUname + "Cahyono");

    }


}
