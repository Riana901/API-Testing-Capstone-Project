package starter.AdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Admin.UpdateAdmin;

public class UpdateAdminSteps {
    @Steps
    UpdateAdmin updateAdmin;

    @Given("Super Admin mengakses Endpoint API untuk mengupdate data akun admin")
    public void reqUpdateAdmin(){
        updateAdmin.apiEndUpdateAdmin();
    }

    @When("Admin mengakses endpoint untuk memperbarui data akun dengan data yang valid")
    public void validUpdate(){
        updateAdmin.requestUpdateAdmin();
    }

    @And("data akun Admin seharusnya terupdate dengan data baru")
    public void validDataUpdate(){
        updateAdmin.updateAdminDataByID();
    }

        /*
    Update Admin dengan ID yang tidak valid
     */

    @When("Admin mengakses endpoint untuk memperbarui data akun Admin dengan ID yang tidak valid")
    public void idInvalidForUpdate(){
        updateAdmin.requestUpdateAdminInvalidID();
    }

          /*
    Update Admin dengan data yang tidak valid
     */


    @When("Admin mengakses endpoint untuk memperbarui data akun Admin dengan data yang tidak valid")
    public void dataInvalidForUpdate(){
        updateAdmin.requestUpdateAdminInvalidData();
    }

}
