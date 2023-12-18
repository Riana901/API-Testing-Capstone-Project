package starter.ProductTypeStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ProductType.GetProductTypeByID;

public class GetProductTypeByIDSteps {
    @Steps
    GetProductTypeByID getProductTypeByID;
    @Given("Admin mengatur API Endpoint untuk mendapatkan tipe produk berdasarkan id")
    public void getProductType() {
        getProductTypeByID.apiEndGetProductTypeID();
    }

    @When("Admin mengakses endpoint untuk mendapatkan informasi tipe produk berdasarkan ID")
    public void reqGetPTAdd(){
        getProductTypeByID.requestGetProductTypeByID();
    }

    @Then("sistem seharusnya menampilkan informasi lengkap tentang tipe produk tersebut")
    public void msgGetPTByID(){
        getProductTypeByID.getProductTypeID();
    }
        /*
    Get Product By Invalid ID
     */

    @When("Admin mengakses endpoint untuk mendapatkan informasi tipe produk dengan ID yang tidak tersedia")
    public void getInvalidIDPT(){
        getProductTypeByID.requestGetProductTypeInvalidID();
    }

    @Given("sistem seharusnya menampilkan pesan kesalahan untuk produk yang tidak ditemukan")
    public void errMsg(){
        getProductTypeByID.productTypeNotFound();
    }

}
