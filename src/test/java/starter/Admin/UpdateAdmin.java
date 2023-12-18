package starter.Admin;

import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static starter.URL.urlAdmin;

public class UpdateAdmin {

    @Step("Super Admin mengakses Endpoint API untuk mengupdate data akun admin")
    public String apiEndUpdateAdmin(){
        return urlAdmin;
    }

    @Step("Admin mengakses endpoint untuk memperbarui data akun dengan data yang valid")
    public void requestUpdateAdmin(){
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String fullname = faker.name().lastName();
        String username = faker.name().lastName();
        String password = faker.internet().password();

        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        int lastAdminID = GetterAdmin.getLastAdminID();
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .put(urlAdmin + lastAdminID);
    }

    @Step("data akun Admin seharusnya terupdate dengan data baru")
    public void updateAdminDataByID(){
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
    Update Admin dengan ID yang tidak valid
     */
    @Step("Admin mengakses endpoint untuk memperbarui data akun Admin dengan ID yang tidak valid")
    public void requestUpdateAdminInvalidID(){

        JSONObject requestBody = new JSONObject();

        Faker faker = new Faker();
        String fullname = faker.name().lastName();
        String username = faker.name().lastName();
        String password = faker.internet().password();
        
        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        int lastAdminID = GetterAdmin.getLastAdminID()+1;
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .put(urlAdmin + lastAdminID);
    }


      /*
    Update Admin dengan data yang tidak valid
     */
      @Step("Admin mengakses endpoint untuk memperbarui data akun Admin dengan data yang tidak valid")
      public void requestUpdateAdminInvalidData(){

          JSONObject requestBody = new JSONObject();
          
          requestBody.put("fullname", "#@#@#@");
          requestBody.put("username", ")!@(&#@*!@)!@(");
          requestBody.put("password", "@(!*&#@&*!@()!@");

          int lastAdminID = GetterAdmin.getLastAdminID();
          String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();
          SerenityRest.given()
                  .header("Content-Type", "application/json")
                  .header("Authorization", "Bearer " + token)
                  .body(requestBody.toString())
                  .put(urlAdmin + lastAdminID);
      }

}
