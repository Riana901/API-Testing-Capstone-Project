package starter.SuperAdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.SuperAdmin.GetAllSuperAdmin;

public class GetAllSuperAdminSteps {

    @Steps
    GetAllSuperAdmin getAllSuperAdmin;

    @Given("Super Admin mengakses endpoint untuk mendapatkan semua Super Admin")
    public void allSuperAdmin(){
        getAllSuperAdmin.apiGetAllSuperAdmin();
    }

    @When("Super Admin mengirim request untuk mendapatkan semua Super Admin")
    public void requestGetAllSuperAdmin(){
        getAllSuperAdmin.requestAllSuperadmin();
    }

    @And("sistem seharusnya menampilkan daftar semua Super Admin yang tersedia")
    public void getAlSuperAdminData(){
        getAllSuperAdmin.receiveAllSuperAdminData();
    }
}
