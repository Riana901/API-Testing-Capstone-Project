package starter.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAdmin {
    public static JSONObject getLoginAdmin() {
        try {
            String filePath = "src/test/resources/sample/LoginAdmin.json";

            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getRegisterAdmin() {
        try {
            String filePath = "src/test/resources/sample/RegisterAdmin.json";

            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
