package starter.utils;

import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONArray;
import org.json.JSONObject;

import static starter.URL.*;

public class GetterPaymentMethod {
    public static int getLastPaymentMethodID() {
        String tokenSuperAdmin = GenerateTokenSuperAdmin.generateTokenSuperAdmin();

        ResponseBody AdminResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenSuperAdmin)
                .get(urlConvertPoint);

        JSONObject productsResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = productsResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(adminData.length() - 1);
        return lastAdmin.getInt("id");
    }
}