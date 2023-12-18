package starter.PaymentTypeStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentType.DeletePaymentType;


public class DeletePaymentTypeSteps {
    @Steps
    DeletePaymentType deletePaymentType;


    @Given("Admin mengatur API Endpoint untuk menghapus data tipe pembayaran")
    public void apiEndDelPayType(){
        deletePaymentType.apiDeletePaymentTypeID();
    }

    @When("Admin mengakses endpoint untuk menghapus Payment Type dengan ID tersebut")
    public void sendDelPaymentType(){
        deletePaymentType.requestDeletePaymentTypeByID();
    }

    @Then("Admin mendapatkan pesan untuk tipe pembayaran yang sudah terhapus")
    public void responseDelPaymentType(){
        deletePaymentType.deletePaymentTypeByID();
    }

    @When("Admin mengakses endpoint untuk menghapus Payment Type dengan ID yang tidak valid")
    public void delInvalidPaymentTypeID(){
        deletePaymentType.requestDeletePaymentTypeInvalidID();
    }
}
