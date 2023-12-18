package starter.utils;

import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONArray;
import org.json.JSONObject;

import static starter.URL.urlCashier;
import static starter.URL.urlGetAllAdmin;

public class GetterCashier {
    public static int getLastCashierID() {
        String tokenSA = GenerateTokenAdmin.generateTokenAdmin();

        ResponseBody AdminResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenSA)
                .get(urlCashier);

        JSONObject productsResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = productsResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(adminData.length() - 1);
        return lastAdmin.getInt("id");
    }

    public static String getLastCashierUname() {
        String tokenSA = GenerateTokenAdmin.generateTokenAdmin();

        ResponseBody AdminResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenSA)
                .get(urlCashier);

        JSONObject productsResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = productsResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(adminData.length() - 1);
        return lastAdmin.getString("username");
    }
}
