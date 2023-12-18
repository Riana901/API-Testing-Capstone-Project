package starter.AdminStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Admin.RegisterAdmin;

public class RegisterAdminSteps {
    @Steps
    RegisterAdmin registerAdmin;

    @Given("Admin mengakses endpoint untuk mendaftarkan diri")
    public void regAdmin(){
        registerAdmin.apiAdminRegister();
    }

    @When("Admin mendaftarkan diri dengan data yang valid")
    public void validDataAdmin(){
        registerAdmin.validAdminRegister();
    }

    @Then("sistem seharusnya menampilkan data Admin yang baru terdaftar")
    public void adminRegOk(){
        registerAdmin.okRegisterAdmin();
    }

    /*
    Register dengan akun yang sudah ada
     */

    @When("Admin mendaftarkan diri dengan data yang memiliki username yang sudah ada")
    public void registeredAdmin(){
        registerAdmin.registeredAccount();
    }

    @And("Admin menerima pesan kesalahan untuk registrasi dengan username yang sudah ada")
    public void errorMessageUsername(){
        registerAdmin.errorMessageRegisterAdmin();
    }

    /*
    Register Admin dengan data yang invalid
     */

    @When("Admin mengakses endpoint untuk mendaftarkan Admin dengan username yang tidak valid")
    public void invalidDataRegister(){
        registerAdmin.invalidDataRegisterAdmin();
    }

    @And("sistem seharusnya menampilkan pesan kesalahan validasi")
    public void errorValidation(){
        registerAdmin.errorValidationData();
    }
}
