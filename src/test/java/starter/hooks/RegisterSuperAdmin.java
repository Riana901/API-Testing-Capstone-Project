package starter.hooks;

import io.cucumber.java.Before;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterSuperAdmin {
    @Before(value = "@GenerateToken")
    public static void beforeTest() {
        String username = "arief";
        String password = "12345678";

        JSONObject requestBody = new JSONObject();

        requestBody.put("username", username);
        requestBody.put("password", password);

        String filePath = "src/test/resources/sample/SuperAdmin.json";

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
