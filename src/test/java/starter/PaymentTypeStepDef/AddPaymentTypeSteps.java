package starter.PaymentTypeStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PaymentType.AddPaymentType;


public class AddPaymentTypeSteps {
    @Steps
    AddPaymentType addPaymentType;


    @Given("Admin mengatur API Endpoint untuk menambah tipe pembayaran baru")
    public void apiAddPaymentType(){addPaymentType.apiEndAddPaymentType();
    }

    @When("Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru dengan data valid")
    public void reqAddPaymentType(){
        addPaymentType.requestAddPaymentType();
    }

    @And("sistem seharusnya menampilkan pesan sukses untuk Payment Type berhasil ditambahkan")
    public void messageAddPaymentType(){
        addPaymentType.msgAddPaymentType();
    }


    @When("Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru tanpa memasukkan data yang diperlukan")
    public void addPaymentTypeNoData(){
        addPaymentType.reqAddPaymentTypeWithNoData();
    }

    @And("sistem seharusnya menampilkan pesan kesalahan untuk Data tipe pembayaran yang diperlukan tidak lengkap")
    public void responseNoDataPaymentType(){
        addPaymentType.msgErrorNoDataAddPT();
    }



    @Given("Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru dengan typeName yang sudah ada")
    public void requestAddedDataPaymentType(){
        addPaymentType.reqAddPaymentTypeWithAddedData();
    }

    @When("sistem seharusnya menampilkan pesan kesalahan untuk Payment Type dengan typeName tersebut sudah ada")
    public void responseErrorAddedDataPaymentType(){
        addPaymentType.msgErrorAddPaymentType();
    }
}
