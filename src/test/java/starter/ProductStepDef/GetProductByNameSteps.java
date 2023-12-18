package starter.ProductStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Product.GetProductByName;

public class GetProductByNameSteps {
    @Steps
    GetProductByName getProductByName;

    @Given("Admin memasukkan endpoint yang valid untuk Get Product By Name")
    public void apiGetProductByName() {
        getProductByName.apiGetProductByName();
    }

    @When("Admin send request untuk Get Product by Name dengan valid")
    public void sendRequestGetProductByName() {
        getProductByName.sendRequestGetProductByName();
    }

    @And("Informasi produk sesuai nama berhasil ditampilkan")
    public void productByNameDisplayed() {
        getProductByName.getProductByNameDisplayed();
    }

    /*
    Get Product By Name Invalid
     */

    @Given("Admin memasukkan endpoint yang valid untuk Get Product By Name Invalid")
    public void apiGetProductByNameOInvalid() {
        getProductByName.apiGetProductByNameInvalid();
    }
    @When("Admin send request untuk Get Product by Name dengan nama yang salah")
    public void sendRequestGetProductByNameInvalid() {
        getProductByName.sendRequestGetProductByNameInvalid();
    }

    @And("Informasi produk sesuai nama gagal ditampilkan")
    public void productByNameNotDisplayed() {
        getProductByName.productByNameNotDisplayed();
    }
}
