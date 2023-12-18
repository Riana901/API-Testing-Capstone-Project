package starter.CashierStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Cashier.RegisterCashier;

public class RegisterCashierSteps {

    @Steps
    RegisterCashier registerCashier;

    @Given("Kasir mengatur endpoint API untuk register")
    public void regCashierAPI(){
        registerCashier.apiCashierRegister();
    }

    @When("Kasir mendaftarkan akun dengan data yang valid")
    public void validDataReg(){
        registerCashier.validCashierRegister();
    }

    @And("sistem seharusnya menyertakan informasi akun Kasir yang baru terdaftar")
    public void validMsg(){
        registerCashier.okRegisterCashier();
    }

       /*
    Pendaftaran Kasir tanpa username yang diperlukan
     */

    @When("Kasir mencoba mendaftarkan akun Kasir tanpa menyertakan username")
    public void cashierNoUname(){
        registerCashier.invalidUnameRegister();
    }

    @And("sistem seharusnya menyertakan pesan error untuk sign up error")
    public void errorSignUp(){
        registerCashier.errorMessageRegisterCashier();
    }

        /*
    Pendaftaran Kasir dengan AdminID yang tidak valid
     */

    @When("Kasir mencoba mendaftarkan akun Kasir dengan AdminID yang tidak valid")
    public void invalidAdminID(){
        registerCashier.invalidAdminIDRegister();
    }

       /*
    Kasir mencoba mendaftarkan akun Kasir dengan Username yang tidak valid
     */
    @When("Kasir mencoba mendaftarkan akun Kasir dengan Username yang tidak valid")
    public void unameFreak(){
        registerCashier.freakRegister();
       }

    @And("sistem seharusnya menyertakan pesan error untuk sign up error username")
    public void invUname(){registerCashier.errorMessageInvUname();}

        /*
Pendaftaran Kasir dengan username yang sudah digunakan
     */
    @When("Kasir mencoba mendaftarkan akun Kasir dengan username yang sudah digunakan")
    public void regUsedUname(){
        registerCashier.alreadyReg();
        }

    @And("sistem seharusnya menyertakan pesan error untuk sign up error karena username")
    public void errorRegUsedUname(){
        registerCashier.errorMsgUnameUsed();
    }
}
