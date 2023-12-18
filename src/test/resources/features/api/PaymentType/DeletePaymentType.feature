@DeletePaymentType
Feature: Delete Payment Type

  Background:
    Given Admin mengatur API Endpoint untuk menghapus data tipe pembayaran

  Scenario: Payment Type berhasil dihapus
    When Admin mengakses endpoint untuk menghapus Payment Type dengan ID tersebut
    Then sistem seharusnya memberikan respons dengan status kode 200
    And Admin mendapatkan pesan untuk tipe pembayaran yang sudah terhapus


  Scenario: Menghapus Payment Type dengan ID yang tidak valid
    When Admin mengakses endpoint untuk menghapus Payment Type dengan ID yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 404


