package starter.AdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Admin.LoginAdmin;

public class LoginAdminSteps {

    @Steps
    LoginAdmin loginAdmin;

    @Given("Admin mengakses API Endpoint untuk login")
    public void setApiAdminLogin(){
        loginAdmin.apiAdminLogin();
    }

    @When("Admin memasukkan username dan password yang valid")
    public void validAdminLogin(){
        loginAdmin.sendValidLoginAdmin();
    }

    @Then("Admin menerima data login")
    public void validDataAdmin(){
        loginAdmin.receiveValidDataForLoginAdmin();
    }

    /*
    Login dengan Username yang salah
     */

    @When("Admin memasukkan username yang tidak valid")
    public void unameInvalid(){
        loginAdmin.invalidUnameLoginAdmin();
    }

    @And("Admin menerima pesan kesalahan")
    public void errorMessage(){
        loginAdmin.errorMessageLoginAdmin();
    }

    /*
    Login dengan password yang salah
     */
    @When("Admin memasukkan password yang tidak valid")
    public void pwordInvalid(){
        loginAdmin.invalidPword();
    }


}
