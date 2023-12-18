package starter.ConvertPointStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ConvertPoint.UpdateConvertPoint;

public class UpdateConvertPointSteps {
    @Steps
    UpdateConvertPoint updateConvertPoint;

    @Given("Admin mengakses dengan endpoint yang valid untuk update convert point")
    public void apiUpdateConvertPoint() {
        updateConvertPoint.apiUpdateConvertPoint();
    }

    @When("Admin send request dengan valid untuk melakukan update Convert Point")
    public void sendRequestUpdateConvertPoint() {
        updateConvertPoint.sendRequestUpdateConvertPoint();
    }

    @And("Update berhasil")
    public void updateConvertPointSuccess() {
        updateConvertPoint.updateConvertPointSuccess();
    }

    /*
    Update Convert Point Invalid
     */

    @When("Admin send request dengan ID yang invalid untuk update")
    public void sendRequestInvalidUpdateConvertPoint() {
        updateConvertPoint.sendRequestInvalidUpdateConvertPoint();
    }

    @And("Update gagal dilakukan")
    public void updateConvertPointFailed() {
        updateConvertPoint.updateConvertPointFailed();
    }
}
