package starter.MembershipStepDef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import starter.Membership.GetMembershipByPhone;

public class GetMembershipByPhoneSteps {


@Steps
    GetMembershipByPhone getMembershipByPhone;

    @Given("User mengatur endpoint untuk meminta membership berdasarkan nomor telepon")
    public void endGetValidPhone(){getMembershipByPhone.ApiEndGetMembershipByPhone();}
    @When("User mengirim request untuk mendapatkan data membership berdasarkan nomor telepon yang valid")
    public void reqGetValidPhone(){getMembershipByPhone.requestGetMembershipByPhone();}
    @And("Data membership dengan nomor telepon yang diminta ditampilkan sebagai respon")
    public void responseValidPhone(){getMembershipByPhone.getMembershipByPhone();}
    @When("User mengirimkan request untuk mendapatkan data membership berdasarkan nomor telepon yang invalid")
    public void reqGetInvalidPhone(){getMembershipByPhone.getMembershipByInvalidPhone();}

}