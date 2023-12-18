package starter.hooks;

import io.cucumber.java.Before;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterCashier {

    @Before(value = "@RegisterCashier")
    public static void beforeTest() {

        Integer adminID = 1;
        String username = "RakaQE";
        String fullname = "QECapstone";
        String password = "12345678";

        JSONObject requestBody = new JSONObject();

        requestBody.put("AdminID", adminID);
        requestBody.put("username", username);
        requestBody.put("fullname", fullname);
        requestBody.put("password", password);

        String filePath = "src/test/resources/sample/Cashier.json";

        try {
            String jsonString = requestBody.toString();
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
