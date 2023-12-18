@RegisterMembership
  Feature: Register Membership
  Background:
  Given User mengatur endpoint dengan valid untuk melakukan registrasi member

    Scenario: User dapat melakukan registrasi membership
      When User mengisikan data dengan lengkap dan valid
      Then sistem seharusnya memberikan respons dengan status kode 201
      And Data membership baru muncul sebagai respons

    Scenario: User tidak dapat melakukan registrasi data membership tanpa mengisi seluruh data
      When User tidak melakukan input  name, telephone
      And User mengirimkan request untuk melakukan registrasi data membership
      Then sistem seharusnya memberikan respons dengan status kode 500

  #  Scenario: User yang tidak memiliki access token tidak dapat melakukan registrasi data membership
     # But Token Authorized tidak diisi
   #   When User mengisikan data dengan benar dan lengkap (ID, name, telephone)
    #  And User mengirimkan request untuk melakukan registrasi data membership
   #   Then sistem seharusnya memberikan respons dengan status kode 401

    Scenario: User tidak dapat melakukan registrasi data membership dengan mengosongkan nama
      When User melakukan input data nomor telepon
      And User mengirimkan request untuk melakukan registrasi data membership
      Then sistem seharusnya memberikan respons dengan status kode 500

    Scenario: User tidak dapat melakukan registrasi data membership dengan mengosongkan nomor telepon
      When User melakukan input data nama
      And User mengirimkan request untuk melakukan registrasi data membership
      Then sistem seharusnya memberikan respons dengan status kode 500