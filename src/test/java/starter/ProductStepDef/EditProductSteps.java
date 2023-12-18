package starter.ProductStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Product.EditProduct;

public class EditProductSteps {
    @Steps
    EditProduct editProduct;

    @Given("Admin memasukkan endpoint yang valid untuk mengedit product")
    public void apiEditProduct() {
        editProduct.apiEditProduct();
    }

    @When("Admin send request untuk mengedit product")
    public void sendRequestEditProduct() {
        editProduct.sendRequestEditProduct();
    }

    @And("Product berhasil diedit")
    public void productEdited() {
        editProduct.productEdited();
    }

    /*
    Edit Product Invalid
     */

    @When("Admin send request untuk mengedit produk dengan ID invalid")
    public void sendRequestEditProductInvalid() {
        editProduct.sendRequestEditProductInvalid();
    }

    @And("Product gagal untuk diedit")
    public void editProductInvalid() {
        editProduct.editProductInvalid();
    }
}
