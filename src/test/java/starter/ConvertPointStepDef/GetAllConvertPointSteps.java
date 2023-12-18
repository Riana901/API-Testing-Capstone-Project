package starter.ConvertPointStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ConvertPoint.GetAllConvertPoint;

public class GetAllConvertPointSteps {
    @Steps
    GetAllConvertPoint getAllConvertPoint;

    @Given("Admin akses dengan endpoint yang valid untuk Get All Convert Point")
    public void apiGetAllConvertPoint() {
        getAllConvertPoint.apiGetAllConvertPoint();
    }

    @When("Admin send request Get All Convert Point dengan valid")
    public void sendRequestGetAllConvertPoint() {
        getAllConvertPoint.sendRequestGetAllConvertPoint();
    }

    @And("Semua convert point berhasil ditampilkan")
    public void allConvertPointDisplayed() {
        getAllConvertPoint.allConvertPointDisplayed();
    }

    /*
    Get All Convert Point Invalid
     */


}
