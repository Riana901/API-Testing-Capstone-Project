package starter.ProductStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Product.GetProductByID;

public class GetProductByIDSteps {
    @Steps
    GetProductByID getProductByID;

    @Given("Admin mengakses dengan endpoint yang valid untuk Get Product By ID")
    public void apiGetProductByID() {
        getProductByID.apiGetProductByID();
    }

    @When("Admin send request untuk Get Product by ID")
    public void sendRequestGetProductByID() {
        getProductByID.sendRequestGetProductByID();
    }

    @And("Data produk berdasarkan ID berhasil ditampilkan")
    public void productByIDDisplayed() {
        getProductByID.productByIDDisplayed();
    }

    /*
    Get product By ID Invalid
     */

    @When("Admin send request Get Product by ID dengan ID yang invalid")
    public void sendRequestGetProductByIDInvalid() {
        getProductByID.sendRequestGetProductByIDInvalid();
    }
    @And("Data Produk gagal ditampilkan")
    public void productByIDInvalidDisplayed(){
        getProductByID.productByIDInvalidDisplayed();
    }
}
