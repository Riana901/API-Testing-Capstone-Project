package starter.hooks;

import io.cucumber.java.Before;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterAdmin {
    @Before(value = "@RegisterAdmin")
    public static void beforeTest() {

        Integer superAdminID = 1;
        String fullname = "Raka";
        String username = "QECapstone";
        String password = "12345678";

        JSONObject requestBody = new JSONObject();

        requestBody.put("superAdminID", superAdminID);
        requestBody.put("fullname", fullname);
        requestBody.put("username", username);
        requestBody.put("password", password);

        String filePath = "src/test/resources/sample/RegisterAdmin.json";

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
