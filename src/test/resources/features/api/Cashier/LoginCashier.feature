@ManageCashierAccount
Feature: Login Kasir

  Background:
    Given Kasir mengatur Endpoint untuk login


  Scenario: Kasir berhasil masuk dengan username dan password yang benar
    When Kasir memasukkan username yang benar dan password yang benar
    Then sistem seharusnya memberikan respons dengan status kode 200
    And Kasir menerima data yang valid

  Scenario: Kasir memasukkan username yang salah untuk login
    When Kasir memasukkan username yang salah
    Then sistem seharusnya memberikan respons dengan status kode 400
    And Kasir menerima pesan gagal login

  Scenario: Kasir memasukkan password yang salah untuk login
    When Kasir memasukkan password yang salah
    Then sistem seharusnya memberikan respons dengan status kode 400
    And Kasir menerima pesan gagal login




