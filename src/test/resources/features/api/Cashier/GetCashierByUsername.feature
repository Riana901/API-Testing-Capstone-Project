@ManageCashierAccount
Feature: Get Cashier By Username

  Background:
  Given Admin mengakses endpoint untuk mendapatkan informasi kasir berdasarkan username

  Scenario: Admin berhasil mendapatkan informasi kasir berdasarkan username
    When Admin mengirim request untuk mendapatkan kasir berdasarkan Username
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya menampilkan informasi lengkap tentang kasir tersebut

  Scenario: Admin mencoba mendapatkan informasi dengan username yang tidak valid
    When Kasir mengakses endpoint untuk mendapatkan informasi dengan username yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 404
    And sistem seharusnya menampilkan pesan kesalahan untuk kasir tidak ditemukan


