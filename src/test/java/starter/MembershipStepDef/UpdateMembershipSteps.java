package starter.MembershipStepDef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import starter.Membership.UpdateMembership;

public class UpdateMembershipSteps {

    @Steps
    UpdateMembership updateMembership;

    @Given("User mengatur endpoint dengan valid untuk melakukan update data membership")
    public void endValidUpdate(){updateMembership.ApiEndUpdateMembership();}
    @When("User mengirimkan request untuk melakukan update data membership dengan data yang valid")
    public void reqValidUpdate(){updateMembership.requestUpdateMembership();}
    @And("Sistem memberikan data membership yang telah di update sebagai respon")
    public void responseValidUpdate(){updateMembership.updateMembership();}
    @When("User mengirim request untuk update data membership tanpa mengisi data nama dan number phone")
    public void reqUpdateEmptyField(){updateMembership.requestInvalidBodyUpdateMembership();}
    @When("User mengirimkan request untuk melakukan update data membership dengan invalid ID")
    public void reqUpdateInvalidID(){updateMembership.requestUpdateMembershipWithInvalidID();}
}