package starter.utils;

import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSuperAdmin {

    public static JSONObject getSuperAdmin() {
        try {
            String filePath = "src/test/resources/sample/SuperAdmin.json";

            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
