@UpdateMembership
  Feature: Update Membership
  Background:
  Given User mengatur endpoint dengan valid untuk melakukan update data membership

    Scenario: User dapat melakukan update data membership
      When User mengirimkan request untuk melakukan update data membership dengan data yang valid
      Then sistem seharusnya memberikan respons dengan status kode 200
      And Sistem memberikan data membership yang telah di update sebagai respon

    Scenario: User tidak dapat melakukan update data membership dengan salah satu input tidak diisi
      When User mengirim request untuk update data membership tanpa mengisi data nama dan number phone
      Then sistem seharusnya memberikan respons dengan status kode 400

    Scenario: User tidak dapat update data membership dengan ID yang invalid
      When User mengirimkan request untuk melakukan update data membership dengan invalid ID
      Then sistem seharusnya memberikan respons dengan status kode 404