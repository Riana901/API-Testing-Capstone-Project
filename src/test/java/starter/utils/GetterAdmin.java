package starter.utils;

import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONArray;
import org.json.JSONObject;

import static starter.URL.urlGetAllAdmin;

public class GetterAdmin {
    public static int getLastAdminID() {
        String tokenSA = GenerateTokenSuperAdmin.generateTokenSuperAdmin();

        ResponseBody AdminResponse = SerenityRest.given()
                .header("Authorization", "Bearer " + tokenSA)
                .get(urlGetAllAdmin);

        JSONObject productsResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = productsResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(adminData.length() - 1);
        return lastAdmin.getInt("id");
    }

    public static String getLastAdminUname() {
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();

        ResponseBody AdminResponse = SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlGetAllAdmin);

        JSONObject productsResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = productsResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(adminData.length() - 1);
        return lastAdmin.optString("username");
    }

    public static String getFirstAdminUname() {
        String token = GenerateTokenSuperAdmin.generateTokenSuperAdmin();

        ResponseBody AdminResponse = SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(urlGetAllAdmin);

        JSONObject productsResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = productsResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(0);
        return lastAdmin.optString("username");
    }



}
