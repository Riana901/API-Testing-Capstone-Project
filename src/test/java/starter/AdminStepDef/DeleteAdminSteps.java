package starter.AdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Admin.DeleteAdmin;

public class DeleteAdminSteps {
    @Steps
    DeleteAdmin deleteAdmin;

    @Given("Super Admin mengakses Endpoint API untuk menghapus akun admin")
    public void deleteAdminEndpoint(){
        deleteAdmin.apiEndDeleteAdmin();
    }

    @When("Super Admin menghapus akun admin")
    public void reqDeleteAdmin(){
        deleteAdmin.requestDeleteAdmin();
    }

    @And("Admin menerima pesan akun sudah terhapus")
    public void receiveMsg(){
        deleteAdmin.msgDeletedAdmin();
    }

        /*
    Delete admin dengan id yang tidak ada
     */
    @Given("Super Admin menghapus akun admin dengan ID yang tidak valid")
    public void invalidIDDeleteAdmin(){
            deleteAdmin.requestDeleteAdminInvalidID();
        }

}
