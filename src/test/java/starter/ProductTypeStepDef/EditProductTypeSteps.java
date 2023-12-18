package starter.ProductTypeStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ProductType.EditProductType;

public class EditProductTypeSteps {

    @Steps
    EditProductType editProductType;

    @Given("Admin mengatur API Endpoint untuk mengedit data tipe produk")
    public void editProductApi(){
        editProductType.apiEditProductType();
    }

    @When("Admin mengirimkan data yang valid untuk perubahan, seperti nama tipe produk yang baru")
    public void editedDataProduct(){
        editProductType.requestEditProductType();
    }

    @And("sistem seharusnya mengganti data tipe produk yang sesuai dengan permintaan Admin")
    public void typeEdited(){
        editProductType.productTypeEdited();
    }

       /*
    Admin mencoba mengubah tipe produk yang tidak ada
     */

    @When("Admin mengakses endpoint untuk mengubah data tipe produk dengan ID yang tidak valid")
    public void updateInvalidID(){
        editProductType.updateProductTypeInvalidID();
    }

        /*
    Update admin dengan data yang tidak valid
     */

    @Given("Admin mengakses endpoint untuk mengubah data tipe produk berdasarkan ID dengan typeName yang tidak valid")
    public void invalidData(){
        editProductType.updateProductTypeInvalidData();
    }

    @And("sistem seharusnya menampilkan pesan kesalahan validasi untuk typeName")
    public void typeNameInvalid(){
        editProductType.msgErrorInvalidDataPT();
    }
}
