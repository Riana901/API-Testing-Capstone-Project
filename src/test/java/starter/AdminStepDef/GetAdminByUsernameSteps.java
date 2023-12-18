package starter.AdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Admin.GetAdminByUsername;

public class GetAdminByUsernameSteps {

    @Steps
    GetAdminByUsername getAdminByUsername;


    @Given("Super Admin mengakses API Endpoint untuk mendapatkan data admin berdasarkan username")
    public void apiEnd(){
        getAdminByUsername.apiEndGetAdminUname();
    }

    @When("Super Admin mengakses endpoint untuk mendapatkan informasi admin berdasarkan username yang ada")
    public void requestAdminByUname(){
        getAdminByUsername.requestGetAdminByUname();
    }

    @And("sistem seharusnya menampilkan informasi lengkap tentang admin dengan username tersebut")
    public void receiveDataAdmin(){
        getAdminByUsername.getAdminDataByUname();
    }

    /*
Get Admin dengan username yang tidak ada
 */
    @Given("Super Admin mengakses endpoint untuk mendapatkan informasi admin dengan username yang tidak ada")
    public void requestAdminByInvalidUname(){
        getAdminByUsername.requestGetAdminByInvalidUname();
    }

}
