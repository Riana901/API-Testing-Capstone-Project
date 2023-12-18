@EditPaymentType
Feature: Edit Payment Type

  Background:
    Given Admin mengatur API Endpoint untuk mengedit data tipe pembayaran

  Scenario: : Admin berhasil mengubah data tipe pembayaran
    When Admin mengirimkan data yang valid untuk perubahan, seperti nama tipe pembayaran yang baru
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya mengganti data tipe pembayaran yang sesuai dengan permintaan Admin

  Scenario: Admin mencoba mengubah tipe pembayaran yang tidak ada
    When Admin mengakses endpoint untuk mengubah data tipe pembayaran dengan ID yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 404


  Scenario: Admin mencoba mengubah tipe pembayaran dengan data yang tidak valid
    When Admin mengakses endpoint untuk mengubah data tipe pembayaran berdasarkan ID dengan typeName yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 404
    And sistem seharusnya menampilkan pesan kesalahan


