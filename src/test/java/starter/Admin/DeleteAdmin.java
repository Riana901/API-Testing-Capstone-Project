package starter.Admin;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static starter.URL.urlAdmin;

public class DeleteAdmin {
    @Step("Super Admin mengakses Endpoint API untuk menghapus akun admin")
    public String apiEndDeleteAdmin(){
        return urlAdmin;
    }

    @Step("Super Admin menghapus akun admin")
    public void requestDeleteAdmin(){
        int lastAdminID = GetterAdmin.getLastAdminID();
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlAdmin + lastAdminID);
    }

    @Step("Admin menerima pesan akun sudah terhapus")
    public void msgDeletedAdmin(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SUPER_ADMIN_INVALID_USERNAME_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("succesfully delete data admin")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*
    Delete admin dengan id yang tidak ada
     */

    @Step("Super Admin menghapus akun admin dengan ID yang tidak valid")
    public void requestDeleteAdminInvalidID(){
        int lastAdminID = GetterAdmin.getLastAdminID()+1;
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .delete(urlAdmin + lastAdminID);
    }


}
