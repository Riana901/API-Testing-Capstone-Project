package starter.CashierStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Cashier.UpdateCashier;

public class UpdateCashierSteps {

    @Steps
    UpdateCashier updateCashier;

    @Given("Kasir mengatur endpoint API untuk update kasir")
    public void endUpdate(){
        updateCashier.apiEndUpdateCashier();
    }

    @When("Kasir mengakses endpoint untuk memperbarui data akun dengan data yang valid")
    public void reqUpdate(){
        updateCashier.requestUpdateCashier();
    }

    @And("sistem seharusnya memberikan respon yang memuat data akun yang telah diupdate")
    public void updateData(){
        updateCashier.updateCashierDataByID();
    }

    /*
    Kasir mengupdate data akun dengan data yang tidak valid
     */

    @When("Kasir mengakses endpoint untuk memperbarui data akun Admin dengan data yang tidak valid")
    public void invalidData(){
        updateCashier.requestUpdateCashierInvalidData();
    }

        /*
    Kasir Update dengan ID yang tidak valid
     */
    @When("Kasir mengakses endpoint untuk mengupdate data akun dengan ID yang tidak valid")
    public void endInvalid(){
        updateCashier.requestUpdateCashierInvalidID();
    }
}
