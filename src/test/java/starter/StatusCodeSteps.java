package starter;

import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class StatusCodeSteps {
    @Steps
    StatusCode statusCode;

    @Then("sistem seharusnya memberikan respons dengan status kode 200")
    public void statusCode200(){
        statusCode.receiveStatusCode200();
    }
    @Then("sistem seharusnya memberikan respons dengan status kode 404")
    public void statusCode404(){
        statusCode.receiveStatusCode404();
    }

    @Then("sistem seharusnya memberikan respons dengan status kode 400")
    public void statusCode400(){
        statusCode.receiveStatusCode400();
    }

    @Then("sistem seharusnya memberikan respons dengan status kode 401")
    public void statusCode401(){
        statusCode.receiveStatusCode401();
    }
    @Then("sistem seharusnya memberikan respons dengan status kode 500")
    public void statusCode500(){
        statusCode.receiveStatusCode500();
    }

    @Then("sistem seharusnya memberikan respons dengan status kode 201")
    public void statusCode201(){
        statusCode.receiveStatusCode201();
    }

    @Then("sistem seharusnya memberikan respons dengan status kode 409")
    public void statusCode409(){
        statusCode.receiveStatusCode409();
    }

}
