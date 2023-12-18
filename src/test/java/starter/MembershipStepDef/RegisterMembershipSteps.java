package starter.MembershipStepDef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import starter.Membership.RegisterMembership;

public class RegisterMembershipSteps {

    @Steps
    RegisterMembership registerMembership;

    @Given("User mengatur endpoint dengan valid untuk melakukan registrasi member")
    public void endRegisterMember(){registerMembership.apiMemberRegister();}
    @When("User mengisikan data dengan lengkap dan valid")
    public void inputValidDataReg(){registerMembership.validRegisterMembership();}
    @And("Data membership baru muncul sebagai respons")
    public void responseValidReg(){registerMembership.postRegisterMembership();}
    @When("User tidak melakukan input  name, telephone")
    public void regEmptyField(){registerMembership.invalidRegisterMembership();}
    @And("User mengirimkan request untuk melakukan registrasi data membership")
    public void requestInvalidReg(){registerMembership.requestInvalidRegisterMembership();}
    @When("User melakukan input data nomor telepon")
    public void regWithoutName(){registerMembership.postInvalidRegisterWithoutName();}
    @When("User melakukan input data nama")
    public void regWithoutNumPhone(){registerMembership.postInvalidRegisterWithoutPhoneNumber();}

}