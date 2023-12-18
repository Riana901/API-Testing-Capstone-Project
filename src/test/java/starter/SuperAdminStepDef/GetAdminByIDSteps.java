package starter.SuperAdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.SuperAdmin.GetSuperAdminByID;

public class GetAdminByIDSteps {

    @Steps
    GetSuperAdminByID getSuperAdminByID;

    @Given("Super Admin mengakses endpoint untuk mendapatkan super admin dengan id")
    public void getSAByID(){
        getSuperAdminByID.apiGetSuperAdmin();
    }

    @When("Super Admin melakukan permintaan untuk mendapatkan data dirinya sendiri dengan menggunakan ID tersebut")
    public void getData(){
        getSuperAdminByID.requestGetSuperAdminByID();
    }

    @Then("sistem seharusnya menampilkan data Super Admin yang sesuai dengan ID yang diminta")
    public void msgData(){
        getSuperAdminByID.getSuperAdminData();
    }

        /*
    Super Admin mencoba mendapatkan data dengan ID yang tidak ada
     */

    @When("Super Admin melakukan permintaan untuk mendapatkan data Super Admin dengan menggunakan ID yang tidak ada")
    public void invalidIDSA(){
        getSuperAdminByID.requestGetSuperAdminByInvalidID();
    }

    @And("Super Admin menerima pesan kesalahan validasi ID")
    public void invalidID(){ getSuperAdminByID.errorMessageValidation();}

}
