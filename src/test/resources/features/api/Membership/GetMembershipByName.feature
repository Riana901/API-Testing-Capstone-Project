@GetMembershipByName
Feature: Get Membership By Name
Background:
Given User mengatur endpoint untuk meminta data membership berdasarkan nama

  Scenario: User meminta data membership berdasarkan Nama yang tersedia
    When User mengirim request untuk mendapatkan data membership berdasarkan nama yang valid
    Then sistem seharusnya memberikan respons dengan status kode 200
    And Data membership dengan nama yang diminta ditampilkan sebagai respon


  Scenario: User tidak dapat meminta data membership dengan nama yang tidak valid
    When User mengirimkan request untuk mendapatkan data membership berdasarkan nama yang invalid
    Then sistem seharusnya memberikan respons dengan status kode 404
