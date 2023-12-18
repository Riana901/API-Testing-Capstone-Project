package starter.SuperAdmin;

import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;
import starter.utils.FileSuperAdmin;

public class Test {
    public static String performLoginAndGetToken() {
        JSONObject userData = FileSuperAdmin.getSuperAdmin();
        JSONObject requestBody = new JSONObject();

        requestBody.put("username", "arief");
        requestBody.put("password", "12345678");

        ResponseBody loginResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post("https://qbills.biz.id/api/v1/super-admin/login")
                .body();

        JSONObject loginResponseBody = new JSONObject(loginResponse.asString());

        if (loginResponseBody.has("results")) {
            return loginResponseBody.getJSONObject("results").getString("token").toString();
        } else {
            throw new RuntimeException("Token not found in the response");
        }
    }

    public static void main(String[] args) {
        // Example of how to use the method
        String token = performLoginAndGetToken();
        System.out.println("Token: " + token);
    }
}
