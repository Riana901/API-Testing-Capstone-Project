package starter.MembershipStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import starter.Membership.GetMembershipByID;

public class GetMembershipByIDSteps {

@Steps
    GetMembershipByID getMembershipByID;

    @Given("User mengatur endpoint untuk mendapatkan data membership berdasarkan ID")
    public void endGetMemberID(){getMembershipByID.ApiEndGetMemberByID();}

    @When("User mengirim request untuk mendapatkan data membership berdasarkan ID yang valid")
    public void reqValidMemberID(){getMembershipByID.requestGetMembershipByID();}
    @And("Data membership dengan ID yang diminta ditampilkan sebagai respon")
    public void responseMemberValidID(){getMembershipByID.getMemberByID();}
    @When("User mengirimkan request untuk mendapatkan data membership berdasarkan ID yang tidak valid")
    public void reqInvalidMemberID(){getMembershipByID.requestGetMemberInvalidID();}
}