package starter.PaymentTypeStepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentType.GetPaymentTypeByID;

public class GetPaymentTypeByIDSteps {
    @Steps
    GetPaymentTypeByID getPaymentTypeByID;

    @Given("Admin mengatur API Endpoint untuk mendapatkan Payment Type berdasarkan id")
    public void getPaymentType() {
        getPaymentTypeByID.apiEndGetPaymentTypeID();
    }

    @When("Admin mengakses endpoint untuk mendapatkan informasi Payment Type berdasarkan ID")
    public void reqGetPaymentTypeID(){
        getPaymentTypeByID.requestGetPaymentTypeByID();
    }

    @Then("sistem seharusnya menampilkan informasi lengkap tentang Payment Type tersebut")
    public void responseGetPayTypeID(){
        getPaymentTypeByID.getPaymentTypeID();
    }

    @When("Admin mengakses endpoint untuk mendapatkan informasi Payment Type dengan ID yang tidak tersedia")
    public void getInvalidIPayType(){
        getPaymentTypeByID.requestGetPaymentTypeInvalidID();
    }

    @And("sistem seharusnya menampilkan pesan kesalahan untuk Payment Type yang tidak ditemukan")
    public void errMsgPayType(){
        getPaymentTypeByID.paymentTypeNotFound();
    }

}