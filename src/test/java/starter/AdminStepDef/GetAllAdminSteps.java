package starter.AdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Admin.GetAllAdmin;

public class GetAllAdminSteps {

    @Steps
    GetAllAdmin getAllAdmin;

    @Given("Super Admin mengakses API Endpoint untuk mengakses semua akun admin")
    public void setApiAdminLogin(){
        getAllAdmin.apiGetAllAdmin();
    }

    @When("Super Admin mengirim request untuk mendapatkan semua Admin")
    public void requestGetAllAdmin(){
        getAllAdmin.getAllAdmin();
    }

    @And("sistem seharusnya menampilkan daftar semua akun admin yang tersedia")
    public void getAllAdminData(){
        getAllAdmin.allAdminData();
    }

}
