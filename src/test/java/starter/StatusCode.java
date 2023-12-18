package starter;

import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class StatusCode {

    @Step("sistem seharusnya memberikan respons dengan status kode 201")
    public void receiveStatusCode201() {
        restAssuredThat(response -> response.statusCode(201));
    }
    @Step("sistem seharusnya memberikan respons dengan status kode 200")
    public void receiveStatusCode200() {
        restAssuredThat(response -> response.statusCode(200));
    }
    @Step("sistem seharusnya memberikan respons dengan status kode 400")
    public void receiveStatusCode400() {
        restAssuredThat(response -> response.statusCode(400));
    }
    @Step("sistem seharusnya memberikan respons dengan status kode 401")
    public void receiveStatusCode401() {
        restAssuredThat(response -> response.statusCode(401));
    }

    @Step("sistem seharusnya memberikan respons dengan status kode 409")
    public void receiveStatusCode409() {
        restAssuredThat(response -> response.statusCode(409));
    }

    @Step("sistem seharusnya memberikan respons dengan status kode 404")
    public void receiveStatusCode404() {
        restAssuredThat(response -> response.statusCode(404));
    }
    @Step("sistem seharusnya memberikan respons dengan status kode 500")
    public void receiveStatusCode500() {
        restAssuredThat(response -> response.statusCode(500));
    }


}
