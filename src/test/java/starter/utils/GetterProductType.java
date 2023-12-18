package starter.utils;

import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONArray;
import org.json.JSONObject;

import static starter.URL.urlGetAllAdmin;
import static starter.URL.urlProductType;

public class GetterProductType {
    public static int getLastProductTypeID() {
        String tokenAdmin = GenerateTokenAdmin.generateTokenAdmin();

        ResponseBody AdminResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenAdmin)
                .get(urlProductType);

        JSONObject productsResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = productsResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(adminData.length() - 1);
        return lastAdmin.getInt("id");
    }
}
