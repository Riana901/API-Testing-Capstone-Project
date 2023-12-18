package starter.PaymentMethodStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentMethod.UpdatePaymentMethod;

public class UpdatePaymentMethodSteps {
    @Steps
    UpdatePaymentMethod updatePaymentMethod;

    @Given("Admin mengakses menggunakan endpoint yang valid untuk Update Payment Method")
    public void apiUpdatePaymentMethod() {
        updatePaymentMethod.apiUpdatePaymentMethod();
    }

    @When("Admin send request untuk Update Payment Method")
    public void sendRequestUpdatePaymentMethod() {
        updatePaymentMethod.sendRequestUpdatePaymentMethod();
    }

    @And("Update Payment Method berhasil")
    public void updatePaymentMethodSuccess() {
        updatePaymentMethod.updatePaymentMethodSuccess();
    }

    /*
    Update Payment Method Invalid
     */

    @When("Admin send request untuk update payment method menggunakan ID yang invalid")
    public void sendRequestInvalidUpdatePaymentMethod() {
        updatePaymentMethod.sendRequestInvalidUpdatePaymentMethod();
    }

    @And("Update Payment Method gagal")
    public void updatePaymentMethodFailed() {
        updatePaymentMethod.updatePaymentMethodFailed();
    }
}
