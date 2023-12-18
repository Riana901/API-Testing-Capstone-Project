@GetPaymentTypeByID
Feature: Get Payment Type By ID

  Background:
    Given Admin mengatur API Endpoint untuk mendapatkan Payment Type berdasarkan id

  Scenario: Admin berhasil mendapatkan informasi Payment Type berdasarkan ID
    When Admin mengakses endpoint untuk mendapatkan informasi Payment Type berdasarkan ID
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya menampilkan informasi lengkap tentang Payment Type tersebut


  Scenario: Admin mencoba mendapatkan Payment Type dengan ID yang tidak valid
    When Admin mengakses endpoint untuk mendapatkan informasi Payment Type dengan ID yang tidak tersedia
    Then sistem seharusnya memberikan respons dengan status kode 404
    And sistem seharusnya menampilkan pesan kesalahan untuk Payment Type yang tidak ditemukan