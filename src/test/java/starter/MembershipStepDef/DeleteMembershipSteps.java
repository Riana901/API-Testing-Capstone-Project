package starter.MembershipStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Membership.DeleteMembership;

public class DeleteMembershipSteps {
    @Steps
    DeleteMembership deleteMembership;

    @Given("User mengatur endpoint dengan ID yang valid untuk menghapus data membership")
    public void apiEnd(){
        deleteMembership.ApiEndDeleteMember();
    }

    @When("User mengirimkan request untuk menghapus data")
    public void sendDeleteMembership(){
        deleteMembership.requestDeleteMembershipByID();
    }

    @And("Pengguna mendapatkan pesan untuk membership yang sudah terhapus")
    public void deleteMsg(){
        deleteMembership.deleteMembershipByID();
    }


    /*
    Negative Case
    */
    @Given("User mengatur endpoint dengan invalid ID untuk menghapus data membership")
    public void deleteInvalidID(){
        deleteMembership.requestDeleteMembershipInvalidID();
    }
}


