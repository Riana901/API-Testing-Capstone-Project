package starter.ProductTypeStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ProductType.AddProductType;

public class AddProductTypeSteps {

    @Steps
    AddProductType addProductType;


    @Given("Admin mengatur API Endpoint untuk menambah tipe produk baru")
    public void apiAddProdType(){
        addProductType.apiEndAddProductType();
    }

    @When("Admin mengirimkan permintaan untuk menambahkan tipe produk baru dengan data valid")
    public void reqAddProductType(){
        addProductType.requestAddProductType();
    }

    @And("sistem seharusnya menampilkan pesan sukses untuk Product Type berhasil ditambahkan")
    public void messageAddProductType(){
        addProductType.msgAddProductType();
    }

        /*
    Add Product Type tanpa data yang diperlukan
     */
    @When("Admin mengirimkan permintaan untuk menambahkan tipe produk baru tanpa memasukkan data yang diperlukan")
    public void addPTNoData(){
        addProductType.reqAddProductTypeWithNoData();
        }

    @And("sistem seharusnya menampilkan pesan kesalahan untuk Data yang diperlukan tidak lengkap")
    public void errorMessageNoData(){
        addProductType.msgErrorNoDataAddPT();
    }


    /*
    Add Product Type dengan data yang sudah ada
     */
    @Given("Admin mengirimkan permintaan untuk menambahkan tipe produk baru dengan typeName yang sudah ada")
    public void requestAddedData(){
        addProductType.reqAddProductTypeWithAddedData();
    }

    @When("sistem seharusnya menampilkan pesan kesalahan untuk Product Type dengan typeName tersebut sudah ada")
    public void messageErrorAddedData(){
        addProductType.msgErrorAddedDataAddPT();
    }
}
