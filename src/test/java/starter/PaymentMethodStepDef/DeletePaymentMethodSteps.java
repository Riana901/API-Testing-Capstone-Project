package starter.PaymentMethodStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentMethod.DeletePaymentMethod;

public class DeletePaymentMethodSteps {
    @Steps
    DeletePaymentMethod deletePaymentMethod;

    @Given("Admin mengakses menggunakan endpoint yang valid untuk menghapus payment method")
    public void apiDeletePaymentMethod(){
        deletePaymentMethod.apiDeletePaymentMethod();
    }

    @When("Admin send request untuk menghapus payment method")
    public void sendRequestDeletePaymentMethod(){
        deletePaymentMethod.sendRequestDeletePaymentMethod();
    }

    @And("Payment Method berhasil dihapus")
    public void deletePaymentMethodSuccess() {
        deletePaymentMethod.deletePaymentMethodSuccess();
    }
}
