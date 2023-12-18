package starter.ConvertPointStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ConvertPoint.DeleteConvertPoint;

public class DeleteConvertPointSteps {
    @Steps
    DeleteConvertPoint deleteConvertPoint;

    @Given("Admin akses dengan endpoint yang valid untuk delete convert point")
    public void apiDeleteConvertPoint() {
        deleteConvertPoint.apiDeleteConvertPoint();
    }

    @When("Admin send request untuk menghapus convert point")
    public void sendRequestConvertPoint() {
        deleteConvertPoint.sendRequestConvertPoint();
    }

    @And("Convert Point berhasil dihapus")
    public void convertPointDeleted() {
        deleteConvertPoint.convertPointDeleted();
    }
}
