package starter.PaymentMethodStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentMethod.GetPaymentMethodByName;

public class GetPaymentMethodByNameSteps {
    @Steps
    GetPaymentMethodByName getPaymentMethodByName;

    @Given("Admin mengakses menggunakan endpoint yang valid untuk Get Payment Method By Name")
    public void apiGetPaymentMethodByName() {
        getPaymentMethodByName.apiGetPaymentMethodByName();
    }

    @When("Admin send request untuk Get Payment Method by Name")
    public void sendRequestGetPaymentMethodByID(){
        getPaymentMethodByName.sendRequestGetPaymentMethodByID();
    }

    @And("Payment Method sesuai nama berhasil ditampilkan")
    public void paymentMethodByNameDisplayed() {
        getPaymentMethodByName.paymentMethodByNameDisplayed();
    }
    /*
    Get Payment Method By Name Invalid
     */

    @When("Admin send request menggunakan nama yang invalid untuk Get Payment Method By Name")
    public void sendRequestGetPaymentMethodByName() {
        getPaymentMethodByName.sendRequestGetPaymentMethodByName();
    }

    @And("Payment Method sesuai nama gagal ditampilkan")
    public void paymentMethodByNameNotDisplayed() {
        getPaymentMethodByName.paymentMethodByNameNotDisplayed();
    }
}
