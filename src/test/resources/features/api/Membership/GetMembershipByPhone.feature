@GetMembershipByPhone
Feature: Get Membership By Number Phone

Background:
Given User mengatur endpoint untuk meminta membership berdasarkan nomor telepon

  Scenario: User meminta data membership berdasarkan nomor telepon yang tersedia
    When User mengirim request untuk mendapatkan data membership berdasarkan nomor telepon yang valid
    Then sistem seharusnya memberikan respons dengan status kode 200
    And Data membership dengan nomor telepon yang diminta ditampilkan sebagai respon


  Scenario: User tidak dapat meminta data membership dengan nomor telepon yang tidak valid
    When User mengirimkan request untuk mendapatkan data membership berdasarkan nomor telepon yang invalid
    Then sistem seharusnya memberikan respons dengan status kode 404
