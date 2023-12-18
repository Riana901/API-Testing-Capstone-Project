package starter.MembershipStepDef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import starter.Membership.GetMembershipByName;

public class GetMembershipByNameSteps {

@Steps
    GetMembershipByName getMembershipByName;

    @Given("User mengatur endpoint untuk meminta data membership berdasarkan nama")
    public void endGetMemberByName(){getMembershipByName.ApiEndGetMembershipByName();}

    @When("User mengirim request untuk mendapatkan data membership berdasarkan nama yang valid")
    public void reqGetValidName(){getMembershipByName.requestMembershipByName();}
    @And("Data membership dengan nama yang diminta ditampilkan sebagai respon")
    public void responseValidName(){getMembershipByName.getMembershipByName();}
    @When("User mengirimkan request untuk mendapatkan data membership berdasarkan nama yang invalid")
    public void reqGetInvalidName(){getMembershipByName.requestMembershipByInvalidName();}

}