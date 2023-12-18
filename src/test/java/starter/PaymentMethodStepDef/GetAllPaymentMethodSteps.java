package starter.PaymentMethodStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentMethod.GetAllPaymentMethod;

public class GetAllPaymentMethodSteps {
    @Steps
    GetAllPaymentMethod getAllPaymentMethod;

    @Given("Admin mengakses menggunakan endpoint yang valid untuk Get All Payment Method")
    public void apiGetAllPaymentMethod() {
        getAllPaymentMethod.apiGetAllPaymentMethod();
    }

    @When("Admin send request untuk Get All Payment Method")
    public void sendRequestGetAllPaymentMethod() {
        getAllPaymentMethod.sendRequestGetAllPaymentMethod();
    }

    @And("Semua Payment Method berhasil ditampilkan")
    public void allPaymentMethodDisplayed(){
        getAllPaymentMethod.allPaymentMethodDisplayed();
    }

}
