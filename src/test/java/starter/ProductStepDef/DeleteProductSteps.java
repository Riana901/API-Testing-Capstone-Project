package starter.ProductStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Product.DeleteProduct;

public class DeleteProductSteps {
    @Steps
    DeleteProduct deleteProduct;

    @Given("Admin memasukkan endpoint yang valid untuk menghapus produk")
    public void setApiEndpointDeleteProduct() {
        deleteProduct.setApiEndpointDeleteProduct();
    }

    @When("Admin send request untuk menghapus product")
    public void sendRequestDeleteProduct() {
        deleteProduct.sendRequestDeleteProduct();
    }

    @And("Produk berhasil dihapus")
    public void productDeleted() {
        deleteProduct.productDeleted();
    }

    /*
    Delete invalid
     */

    @When("Admin send request untuk menghapus product dengan ID invalid")
    public void sendRequestDeleteProductInvalid() {
        deleteProduct.sendRequestDeleteProductInvalid();
    }

    @And("Product gagal dihapus")
    public void productInvalidDelete() {
        deleteProduct.productInvalidDelete();
    }
}
