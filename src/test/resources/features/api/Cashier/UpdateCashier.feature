@ManageCashierAccount
Feature: : Update Data Cashier

  Background:
    Given Kasir mengatur endpoint API untuk update kasir

  Scenario: Kasir berhasil mengupdate data akun
    When Kasir mengakses endpoint untuk memperbarui data akun dengan data yang valid
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya memberikan respon yang memuat data akun yang telah diupdate

  Scenario: Kasir mengupdate data akun dengan data yang tidak valid
    When Kasir mengakses endpoint untuk memperbarui data akun Admin dengan data yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 400
    And sistem seharusnya menampilkan pesan kesalahan validasi

  Scenario: Kasir mencoba mengupdate data akun yang tidak ada
    When Kasir mengakses endpoint untuk mengupdate data akun dengan ID yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 400
    And sistem seharusnya menampilkan pesan kesalahan untuk kasir tidak ditemukan

