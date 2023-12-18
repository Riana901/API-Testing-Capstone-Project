package starter.utils;

import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;

import static starter.URL.urlAdmin;

public class GenerateTokenAdmin {
    public static String generateTokenAdmin(){
        JSONObject userData = FileAdmin.getLoginAdmin();
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", userData.get("username"));
        requestBody.put("password", userData.get("password"));

        ResponseBody loginResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post("https://qbills.biz.id/api/v1/admin/login")
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
