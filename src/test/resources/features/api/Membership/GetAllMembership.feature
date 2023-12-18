@GetAllMembership
  Feature: Get All Membership

    Scenario: User meminta seluruh data membership dengan endpoint yang valid
      Given User mengatur endpoint yang valid untuk melihat seluruh data membership
      When User mengirimkan request untuk meminta seluruh data membership
      Then sistem seharusnya memberikan respons dengan status kode 200
      And Seluruh data membership ditampilkan sebagai respon

