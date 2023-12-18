package starter.utils;

import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;


public class GenerateTokenSuperAdmin {

    public static String generateTokenSuperAdmin() {
        JSONObject userData = FileSuperAdmin.getSuperAdmin();
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", userData.get("username"));
        requestBody.put("password", userData.get("password"));

        ResponseBody loginResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post("https://qbills.biz.id/api/v1/super-admin/login")
                .body();

        JSONObject loginResponseBody = new JSONObject(loginResponse.asString());
        if (loginResponseBody.has("results")) {
            JSONObject results = loginResponseBody.getJSONObject("results");

            if (results.has("token")) {
                return results.getString("token");
            } else {
                System.out.println("Token field not found in results.");
            }
        } else {
            System.out.println("Results field not found in the response.");
        }
        return null;
    }

}