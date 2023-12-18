package starter.ConvertPointStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ConvertPoint.GetConvertPointByID;

public class GetConvertPointByIDSteps {
    @Steps
    GetConvertPointByID getConvertPointByID;

    @Given("Admin memasukkan endpoint yang valid untuk Get All Convert Point by ID")
    public void apiGetAllConvertPointById() {
        getConvertPointByID.apiGetAllConvertPointById();
    }

    @When("Admin send request dengan ID yang valid untuk Get All Convert Point by ID")
    public void sendRequestGetAllConvertPointById() {
        getConvertPointByID.sendRequestGetAllConvertPointById();
    }

    @And("Convert Point sesuai ID berhasil ditampilkan")
    public void convertPointByIdDisplayed() {
        getConvertPointByID.convertPointByIdDisplayed();
    }

    /*
    Get Convert Point By ID invalid
     */

    @When("Admin send request dengan ID yang invalid untuk Get Convert Point by ID")
    public void sendRequestConvertPointInvalidId() {
        getConvertPointByID.sendRequestConvertPointInvalidId();
    }

    @And("convert point sesuai ID tidak ditemukan")
    public void invalidIdConvertPoint() {
        getConvertPointByID.invalidIdConvertPoint();
    }
}
