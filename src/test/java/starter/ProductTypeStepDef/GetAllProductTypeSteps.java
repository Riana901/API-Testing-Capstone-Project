package starter.ProductTypeStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ProductType.GetAllProductType;

public class GetAllProductTypeSteps {

    @Steps
    GetAllProductType getAllProductType;

    @Given("Admin mengatur API Endpoint untuk mendapatkan semua data tipe produk")
    public void getAllPT(){
        getAllProductType.apiEndGetAllProductType();
    }

    @When("Admin mengakses endpoint untuk mendapatkan semua tipe produk")
    public void accessAPIGetAllPT(){
        getAllProductType.requestGetAllProductType();
    }

    @And("sistem seharusnya menampilkan daftar semua tipe produk yang tersedia")
    public void messageGetAll(){
        getAllProductType.msgGetAllProductType();
    }

}
