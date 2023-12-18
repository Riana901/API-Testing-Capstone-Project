package starter.CashierStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Cashier.GetCashierByID;

public class GetCashierByIDSteps {

    @Steps
    GetCashierByID getCashierByID;

    @Given("Admin mengakses endpoint untuk mendapatkan data kasir berdasarkan ID")
    public void getCashierUrl(){
        getCashierByID.apiEndGetAdminID();
    }

    @When("Admin mengirim request untuk mendapatkan kasir berdasarkan ID")
    public void getCashierID(){
        getCashierByID.requestGetCashierByID();
    }
    @And("sistem seharusnya menampilkan informasi lengkap mengenai kasir tersebut")
    public void cashierData(){
        getCashierByID.getAdminDataByID();
    }

    /*
    Admin mencoba mendapatkan data dengan ID yang tidak valid
     */
    @When("Admin mengirim request untuk mendapatkan kasir berdasarkan ID yang tidak valid")
    public void invalidID(){
        getCashierByID.requestGetCashierByInvalidID();
    }
    @And("sistem seharusnya menampilkan pesan kesalahan untuk kasir tidak ditemukan")
    public void dataNotFound(){
        getCashierByID.cashierNotFoundData();
    }
}
