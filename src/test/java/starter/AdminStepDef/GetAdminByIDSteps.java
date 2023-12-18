package starter.AdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Admin.GetAdminByID;

public class GetAdminByIDSteps {
    @Steps
    GetAdminByID getAdminByID;

    @Given("Super Admin mengakses API Endpoint untuk mendapatkan data admin by ID")
    public void apiEnd(){
        getAdminByID.apiEndGetAdminID();
    }

    @When("Super Admin mengirim request untuk mendapatkan Admin by id")
    public void requestAdminByID(){
        getAdminByID.requestGetAdminByID();
    }

    @And("sistem seharusnya menampilkan informasi lengkap tentang Admin tersebut")
    public void receiveDataAdmin(){
        getAdminByID.getAdminDataByID();
    }

    /*
    Get Admin dengan invalid id
     */
    @When("Super Admin mengakses endpoint untuk mendapatkan informasi dengan ID yang tidak valid")
    public void getInvalidAdminID(){
        getAdminByID.invalidIDReq();
    }

    @And("sistem seharusnya menampilkan pesan kesalahan untuk admin yang tidak ditemukan")
    public void notFound(){
        getAdminByID.dataAdminNotFound();
    }

}
