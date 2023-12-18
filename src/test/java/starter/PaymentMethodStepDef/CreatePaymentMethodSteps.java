package starter.PaymentMethodStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentMethod.CreatePaymentMethod;

public class CreatePaymentMethodSteps {
    @Steps
    CreatePaymentMethod createPaymentMethod;

    @Given("Admin mengakses menggunakan endpoint yang valid untuk create payment method")
    public void apiCreatePaymentMethod() {
        createPaymentMethod.apiCreatePaymentMethod();
    }

    @When("Admin send request untuk membuat payment method baru")
    public void sendRequestCreatePaymentMethod() {
        createPaymentMethod.sendRequestCreatePaymentMethod();
    }

    @And("Payment method baru telah dibuat")
    public void paymentMethodCreated() {
        createPaymentMethod.paymentMethodCreated();
    }

    /*
    Create Payment Method Invalid
     */

    @When("Admin send request dengan data yang invalid untuk membuat payment method baru")
    public void sendRequestCreatePaymentMethodInvalid() {
        createPaymentMethod.sendRequestCreatePaymentMethodInvalid();
    }
    @And("Payment Method gagal dibuat")
    public void invalidCreatePaymentMethod() {
        createPaymentMethod.invalidCreatePaymentMethod();
    }
}