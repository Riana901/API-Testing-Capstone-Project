package starter.CashierStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Cashier.LoginCashier;

public class LoginCashierSteps {

    @Steps
    LoginCashier loginCashier;


    @Given("Kasir mengatur Endpoint untuk login")
    public void endLoginCashier(){
        loginCashier.apiCashierLogin();
    }

    @When("Kasir memasukkan username yang benar dan password yang benar")
    public void unamePwordOk(){
        loginCashier.alreadyRegLogin();
    }

    @And("Kasir menerima data yang valid")
    public void loginOk(){
        loginCashier.receiveValidDataForLoginCashier();
    }

    /*
    Kasir memasukkan username yang salah untuk login
     */

    @When("Kasir memasukkan username yang salah")
    public void wrongUname(){
        loginCashier.invalidUnameLoginCashier();
    }

    @And("Kasir menerima pesan gagal login")
    public void errorLogin(){
        loginCashier.errorMessageLoginCashier();
    }

    /*
Kasir memasukkan username yang salah untuk login
 */
    @When("Kasir memasukkan password yang salah")
    public void wrongPword(){
        loginCashier.invalidPwordLoginCashier();
    }
}
