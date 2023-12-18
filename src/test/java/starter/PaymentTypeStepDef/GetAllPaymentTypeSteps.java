package starter.PaymentTypeStepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentType.GetAllPaymentType;

public class GetAllPaymentTypeSteps {
    @Steps
    GetAllPaymentType getAllPaymentType;

    @Given("Admin mengatur API Endpoint untuk mendapatkan semua data Payment Type")
    public void getAllPayType(){
        getAllPaymentType.apiEndGetAllPaymentType();
    }

    @When("Admin mengakses endpoint untuk mendapatkan semua Payment Type")
    public void requestGetAllPayType(){
        getAllPaymentType.requestGetAllPaymentType();
    }

    @And("sistem seharusnya menampilkan daftar semua Payment Type yang tersedia")
    public void responseGetAllPayType(){
        getAllPaymentType.msgGetAllPaymentType();
    }

}