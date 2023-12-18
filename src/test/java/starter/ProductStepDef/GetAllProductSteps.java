package starter.ProductStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Product.GetAllProduct;

public class GetAllProductSteps {
    @Steps
    GetAllProduct getAllProduct;

    @Given("Admin memasukkan endpoint untuk Get All Product")
    public void apiGetAllProduct() {
        getAllProduct.apiGetAllProduct();
    }

    @When("Admin send request untuk Get All Product")
    public void sendRequestGetAllProduct() {
        getAllProduct.sendRequestGetAllProduct();
    }

    @And("Semua data produk berhasil ditampilkan")
    public void allDataDisplayed() {
        getAllProduct.allDataDisplayed();
    }

}
