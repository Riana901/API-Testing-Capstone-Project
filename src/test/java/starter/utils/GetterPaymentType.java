package starter.utils;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONArray;
import org.json.JSONObject;

import static starter.URL.*;

public class GetterPaymentType {
    public static int getLastPaymentTypeID() {
        String tokenSuperAdmin = GenerateTokenSuperAdmin.generateTokenSuperAdmin();

        ResponseBody<Response> AdminResponse = SerenityRest.given()
               .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenSuperAdmin)
                .get(urlPaymentType);

        JSONObject paymentResponseBody = new JSONObject(AdminResponse.asString());

        JSONArray adminData = paymentResponseBody.getJSONArray("results");

        JSONObject lastAdmin = adminData.getJSONObject(adminData.length() - 1);
        return lastAdmin.getInt("id");
    }
}
