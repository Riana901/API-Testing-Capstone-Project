@UpdateAdmin
Feature: Update Admin

  Background:
    Given Super Admin mengakses Endpoint API untuk mengupdate data akun admin

  Scenario: Admin berhasil memperbarui data akun
    When Admin mengakses endpoint untuk memperbarui data akun dengan data yang valid
    Then sistem seharusnya memberikan respons dengan status kode 200
    And data akun Admin seharusnya terupdate dengan data baru

  Scenario: Mengupdate data akun Admin dengan ID yang tidak valid
    When Admin mengakses endpoint untuk memperbarui data akun Admin dengan ID yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 404
    And sistem seharusnya menampilkan pesan kesalahan untuk admin yang tidak ditemukan

  Scenario: Mengupdate data akun Admin dengan data yang tidak valid
    When Admin mengakses endpoint untuk memperbarui data akun Admin dengan data yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 400
    And sistem seharusnya menampilkan pesan kesalahan validasi


