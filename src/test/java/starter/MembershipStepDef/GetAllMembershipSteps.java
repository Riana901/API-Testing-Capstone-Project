package starter.MembershipStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import starter.Membership.GetAllMembership;

public class GetAllMembershipSteps {

    @Steps
    GetAllMembership getAllMembership;

    @Given("User mengatur endpoint yang valid untuk melihat seluruh data membership")
    public void apiEnd(){getAllMembership.ApiEndGetAllMembership();}

    @When("User mengirimkan request untuk meminta seluruh data membership")
    public void sendReqGetAllMember(){getAllMembership.requestGetAllMemberships();}

    @And("Seluruh data membership ditampilkan sebagai respon")
    public void responseAllMembership(){getAllMembership.getAllMemberships();}



}
