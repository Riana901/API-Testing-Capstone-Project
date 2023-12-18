package starter.ConvertPointStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ConvertPoint.CreateConvertPoint;

public class CreateConvertPointSteps {
    @Steps
    CreateConvertPoint createConvertPoint;

    @Given("Admin mengakses endpoint untuk membuat convert point dengan valid")
    public void apiCreateConvertPoint() {
        createConvertPoint.apiCreateConvertPoint();
    }

    @When("Admin send request untuk membuat convert point dengan valid")
    public void sendRequestConvertPoint() {
        createConvertPoint.sendRequestConvertPoint();
    }

    @And("Convert Point baru berhasil dibuat")
    public void convertPointCreated() {
        createConvertPoint.convertPointCreated();
    }

}
