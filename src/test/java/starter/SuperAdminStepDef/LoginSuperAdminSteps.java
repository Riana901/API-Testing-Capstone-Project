package starter.SuperAdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.SuperAdmin.LoginSuperAdmin;

public class LoginSuperAdminSteps {
    @Steps
    LoginSuperAdmin loginSuperAdmin;

    @Given("Super Admin mengatur Endpoint API untuk login")
    public void setApiEnd(){
        loginSuperAdmin.apiSuperAdminLogin();
    }

    @When("Super Admin mengisi data yang valid untuk login")
    public void setData(){
        loginSuperAdmin.sendValidLoginSuperAdmin();
    }

    @And("Super Admin menerima data login")
    public void getData(){
        loginSuperAdmin.receiveValidDataForLogin();
    }

    /*
    Login dengan invalid Username
     */

    @When("Super Admin mengirim request dengan memasukkan username yang salah")
    public void invalidUname(){
        loginSuperAdmin.sendInvalidLoginUnameSuperadmin();
    }

    @Then("Super Admin menerima pesan kesalahan")
    public void errorMessage(){
        loginSuperAdmin.errorMessage();
    }

    /*
    Login dengan invalid Password
     */
    @When("Super Admin mengirim request dengan memasukkan password yang salah")
    public void invalidPword(){
        loginSuperAdmin.sendInvalidLoginPwordSuperadmin();
    }

    /*
    Login dengan username dan password yang kosong
     */
    @When("Super Admin tidak memasukkan username And password")
    public void emptyLogin(){
        loginSuperAdmin.sendEmptyDataLoginSuperadmin();
    }

    @And("Super Admin menerima pesan kesalahan validasi")
    public void emptyPword(){ loginSuperAdmin.errorMessageValidation();}

}
