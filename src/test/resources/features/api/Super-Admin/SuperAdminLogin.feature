@LoginSuperAdmin
Feature: Super Admin Login


  Background:
    Given Super Admin mengatur Endpoint API untuk login

  Scenario: Super Admin dapat login dengan data yang valid
    When Super Admin mengisi data yang valid untuk login
    Then sistem seharusnya memberikan respons dengan status kode 200
    And Super Admin menerima data login

  Scenario: Super Admin memasukkan username yang salah
    When Super Admin mengirim request dengan memasukkan username yang salah
    Then sistem seharusnya memberikan respons dengan status kode 400
    And Super Admin menerima pesan kesalahan

  Scenario: Super Admin memasukkan password yang salah
    When Super Admin mengirim request dengan memasukkan password yang salah
    Then  sistem seharusnya memberikan respons dengan status kode 400
    And Super Admin menerima pesan kesalahan

  Scenario: Super Admin tidak mengisi username dan password
    When Super Admin tidak memasukkan username And password
    Then sistem seharusnya memberikan respons dengan status kode 400
    And Super Admin menerima pesan kesalahan validasi

