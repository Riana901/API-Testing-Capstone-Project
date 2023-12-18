@RegisterAdmin
Feature: Register Admin

  Background:
    Given Admin mengakses endpoint untuk mendaftarkan diri

  Scenario: Admin berhasil terdaftar
    When Admin mendaftarkan diri dengan data yang valid
    Then sistem seharusnya memberikan respons dengan status kode 201
    And sistem seharusnya menampilkan data Admin yang baru terdaftar

  Scenario: Admin mendaftar dengan username yang sudah ada
    When Admin mendaftarkan diri dengan data yang memiliki username yang sudah ada
    Then sistem seharusnya memberikan respons dengan status kode 409
    And Admin menerima pesan kesalahan untuk registrasi dengan username yang sudah ada

  Scenario: Mendaftarkan Admin dengan username yang tidak valid
    When Admin mengakses endpoint untuk mendaftarkan Admin dengan username yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 409
    And sistem seharusnya menampilkan pesan kesalahan validasi


