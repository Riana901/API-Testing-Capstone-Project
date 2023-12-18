@DeleteMembership
  Feature: Delete Membership

    Scenario: Pengguna berhasil menghapus membership
      Given User mengatur endpoint dengan ID yang valid untuk menghapus data membership
      When User mengirimkan request untuk menghapus data
      Then sistem seharusnya memberikan respons dengan status kode 200
      And Pengguna mendapatkan pesan untuk membership yang sudah terhapus

    Scenario: User tidak dapat menghapus data membership tanpa ID
      Given User mengatur endpoint dengan invalid ID untuk menghapus data membership
      When User mengirimkan request untuk menghapus data
      Then sistem seharusnya memberikan respons dengan status kode 404