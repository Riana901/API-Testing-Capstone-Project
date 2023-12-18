package starter.Membership;


import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlmembership;

public class DeleteMembership {

    /*CasePositive*/
    @Step("User mengatur endpoint dengan ID yang valid untuk menghapus data membership")
    public String ApiEndDeleteMember(){return urlmembership;}

    @Step("User mengirimkan request untuk menghapus data")
    public void requestDeleteMembershipByID(){
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlmembership + 51); /*Diganti sebelum run test*/
    }

    @Step("Pengguna mendapatkan pesan untuk membership yang sudah terhapus")
    public void deleteMembershipByID(){
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_MEMBERSHIP_BY_ID_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'meta'.'success'", is(true)));
        restAssuredThat(response -> response.body("'meta'.'message'", is("successfully delete data membership")));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }


    /*Negative Case*/

    @Step("User mengatur endpoint dengan invalid ID untuk menghapus data membership")
    public void  requestDeleteMembershipInvalidID(){
        String token = GenerateTokenAdmin.generateTokenAdmin();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(urlmembership); 
    }


}
