package starter.CashierStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import starter.Cashier.GetAllCashier;

public class GetAllCashierSteps {
    @Steps
    GetAllCashier getAllCashier;

    @Given("Admin mengakses endpoint untuk mendapatkan semua Kasir")
    public void apiAllCashier(){
        getAllCashier.apiGetAllCashier();
    }

    @When("Admin mengirim request untuk mendapatkan semua kasir")
    public void sendReq(){
        getAllCashier.getAllCashierData();
    }

    @And("sistem seharusnya menampilkan daftar semua Kasir yang tersedia")
    public void showData(){
        getAllCashier.allCashierData();
    }
}
