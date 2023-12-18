package starter.Product;

import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import static starter.URL.urlProduct;


public class AddProduct {

    @Step("Admin mengakses endpoint untuk menambahkan produk")
    public String apiAddProduct() {
        return urlProduct;
    }

    @Step("Admin send request dengan method PUT untuk menambahkan produk")
    public void sendRequestAddProduct() {
        JSONObject requestBody = new JSONObject();

    }

    public void productAdded() {
    }

}
