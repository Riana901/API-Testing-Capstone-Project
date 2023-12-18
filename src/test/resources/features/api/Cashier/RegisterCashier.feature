@ManageCashierAccount
Feature: Register Cashier

  Background:
    Given Kasir mengatur endpoint API untuk register

  Scenario: Kasir berhasil mendaftarkan akun dengan data yang valid
    When Kasir mendaftarkan akun dengan data yang valid
    Then sistem seharusnya memberikan respons dengan status kode 201
    And sistem seharusnya menyertakan informasi akun Kasir yang baru terdaftar

  Scenario: Pendaftaran Kasir tanpa username yang diperlukan
    When Kasir mencoba mendaftarkan akun Kasir tanpa menyertakan username
    Then sistem seharusnya memberikan respons dengan status kode 500
    And sistem seharusnya menyertakan pesan error untuk sign up error

  Scenario: Pendaftaran Kasir dengan Username yang tidak valid
    When Kasir mencoba mendaftarkan akun Kasir dengan Username yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 409
    And sistem seharusnya menyertakan pesan error untuk sign up error username

  Scenario: Pendaftaran Kasir dengan AdminID yang tidak valid
    When Kasir mencoba mendaftarkan akun Kasir dengan AdminID yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 500
    And sistem seharusnya menyertakan pesan error untuk sign up error

  Scenario: Pendaftaran Kasir dengan username yang sudah digunakan
    When Kasir mencoba mendaftarkan akun Kasir dengan username yang sudah digunakan
    Then sistem seharusnya memberikan respons dengan status kode 409
    And sistem seharusnya menyertakan pesan error untuk sign up error karena username


