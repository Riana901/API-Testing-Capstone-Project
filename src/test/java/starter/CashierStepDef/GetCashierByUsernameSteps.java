package starter.CashierStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Cashier.GetCashierByUsername;

public class GetCashierByUsernameSteps {
    @Steps
    GetCashierByUsername getCashierByUsername;

    @Given("Admin mengakses endpoint untuk mendapatkan informasi kasir berdasarkan username")
    public void urlGetUname(){
        getCashierByUsername.apiEndGetCashierUname();
    }

    @When("Admin mengirim request untuk mendapatkan kasir berdasarkan Username")
    public void reqGetUname(){
        getCashierByUsername.requestGetCashierByUname();
    }

    @And("sistem seharusnya menampilkan informasi lengkap tentang kasir tersebut")
    public void cashierUname(){
        getCashierByUsername.getCashierDataByUname();
    }

        /*
    Admin mencoba mendapatkan informasi dengan username yang tidak valid
     */

    @When("Kasir mengakses endpoint untuk mendapatkan informasi dengan username yang tidak valid")
    public void invalidUname(){
        getCashierByUsername.requestGetCashierByInvUname();
    }

}
