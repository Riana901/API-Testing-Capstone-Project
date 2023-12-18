@GetMembershipByID
  Feature: Get Membership By ID
    Background:
    Given User mengatur endpoint untuk mendapatkan data membership berdasarkan ID

    Scenario: User meminta data membership berdasarkan ID yang tersedia
      When User mengirim request untuk mendapatkan data membership berdasarkan ID yang valid
      Then sistem seharusnya memberikan respons dengan status kode 200
      And Data membership dengan ID yang diminta ditampilkan sebagai respon


    Scenario: User tidak dapat meminta data membership dengan ID yang tidak valid
      When User mengirimkan request untuk mendapatkan data membership berdasarkan ID yang tidak valid
      Then sistem seharusnya memberikan respons dengan status kode 404
