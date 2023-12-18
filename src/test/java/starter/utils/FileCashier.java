package starter.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCashier {
    public static JSONObject getCashier() {
        try {
            String filePath = "src/test/resources/sample/Cashier.json";

            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static JSONObject loginCashier() {
        try {
            String filePath = "src/test/resources/sample/LoginCashier.json";

            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
