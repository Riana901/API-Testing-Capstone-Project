package starter.PaymentMethodStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentMethod.GetPaymentMethodByID;

public class GetPaymentMethodByIDSteps {
    @Steps
    GetPaymentMethodByID getPaymentMethodByID;

    @Given("Admin mengakses menggunakan endpoint yang valid untuk Get Payment Method By ID")
    public void apiGetPaymentMethodByID() {
        getPaymentMethodByID.apiGetPaymentMethodByID();
    }

    @When("Admin send request untuk Get All Payment Method By ID")
    public void sendRequestGetAllPaymentMethodByID() {
        getPaymentMethodByID.sendRequestGetAllPaymentMethodByID();
    }

    @And("Payment Method sesuai ID berhasil ditampilkan")
    public void paymentMethodByIDDisplayed() {
        getPaymentMethodByID.paymentMethodByIDDisplayed();
    }

    /*
    Get Payment Method By ID Invalid
     */

    @When("Admin send request menggunakan ID yang salah pada Get Payment Method by ID")
    public void sendRequestInvalidIDPaymentMethod() {
        getPaymentMethodByID.sendRequestInvalidIDPaymentMethod();
    }

    @And("Payment Method sesuai ID gagal ditampilkan")
    public void paymentMethodByIDNotDisplayed() {
        getPaymentMethodByID.paymentMethodByIDNotDisplayed();
    }
}
