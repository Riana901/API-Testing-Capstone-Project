@LoginAdmin
Feature: Login Admin

  Background:
    Given Admin mengakses API Endpoint untuk login

  Scenario: Admin berhasil masuk dengan data login yang benar
    When Admin memasukkan username dan password yang valid
    Then sistem seharusnya memberikan respons dengan status kode 200
    And Admin menerima data login

  Scenario: Login dengan username yang tidak valid
    When Admin memasukkan username yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 400
    And Admin menerima pesan kesalahan

  Scenario: Login dengan password yang tidak valid
    When Admin memasukkan password yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 400
    And Admin menerima pesan kesalahan


