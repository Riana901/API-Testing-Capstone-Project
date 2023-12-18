package starter.PaymentTypeStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentType.EditPaymentType;


public class EditPaymentTypeSteps {
    @Steps
    EditPaymentType editPaymentType;

    @Given("Admin mengatur API Endpoint untuk mengedit data tipe pembayaran")
    public void SetEndPaymentType(){
        editPaymentType.apiEditPaymentType();
    }

    @When("Admin mengirimkan data yang valid untuk perubahan, seperti nama tipe pembayaran yang baru")
    public void updatePaymentType(){
        editPaymentType.requestEditPaymentType();
    }

    @And("sistem seharusnya mengganti data tipe pembayaran yang sesuai dengan permintaan Admin")
    public void responseUpdatePaymentType(){
        editPaymentType.paymentTypeEdited();
    }

    @When("Admin mengakses endpoint untuk mengubah data tipe pembayaran dengan ID yang tidak valid")
    public void updateInvalidPaymentType(){
        editPaymentType.updatePaymentTypeInvalidID();
    }

    @Given("Admin mengakses endpoint untuk mengubah data tipe pembayaran berdasarkan ID dengan typeName yang tidak valid")
    public void invalidUpdatePaymentType(){
        editPaymentType.updatePaymentTypeInvalidData();
    }

    @And("sistem seharusnya menampilkan pesan kesalahan")
    public void updateTypeNameInvalid(){
        editPaymentType.msgErrorInvalidDataPaymentType();
    }
}
