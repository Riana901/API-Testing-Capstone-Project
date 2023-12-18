@GetAllPaymentType
Feature: Get All Payment Type

  Background:
    Given Admin mengatur API Endpoint untuk mendapatkan semua data Payment Type

  Scenario: Admin berhasil mendapatkan semua Payment Type
    When Admin mengakses endpoint untuk mendapatkan semua Payment Type
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya menampilkan daftar semua Payment Type yang tersedia