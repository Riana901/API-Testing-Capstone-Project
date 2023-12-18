package starter.ProductTypeStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ProductType.DeleteProductType;

public class DeleteProductTypeSteps {

    @Steps
    DeleteProductType deleteProductType;


    @Given("Admin mengatur API Endpoint untuk menghapus data tipe produk")
    public void apiEnd(){
        deleteProductType.apiDeleteProductTypeID();
    }

    @When("pengguna mengakses endpoint untuk menghapus Product Type dengan ID tersebut")
    public void sendDeleteProduct(){
        deleteProductType.requestDeleteProductTypeByID();
    }

    @Then("Pengguna mendapatkan pesan untuk produk yang sudah terhapus")
    public void deleteMsg(){
        deleteProductType.deleteProductTypeByID();
    }


    /*
    Delete Product Type dengan id yang tidak valid
    */

    @When("pengguna mengakses endpoint untuk menghapus Product Type dengan ID yang tidak valid")
    public void deleteInvalidID(){
        deleteProductType.requestDeleteProductTypeInvalidID();
    }
}
