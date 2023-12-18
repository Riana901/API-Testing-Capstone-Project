@GetSuperAdminByID
Feature: Get Super Admin By ID

  Background:
    Given Super Admin mengakses endpoint untuk mendapatkan super admin dengan id


  Scenario: Super Admin berhasil mendapatkan data admin yang valid
    When Super Admin melakukan permintaan untuk mendapatkan data dirinya sendiri dengan menggunakan ID tersebut
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya menampilkan data Super Admin yang sesuai dengan ID yang diminta

  Scenario: Super Admin mencoba mendapatkan data dengan ID yang tidak ada
    When Super Admin melakukan permintaan untuk mendapatkan data Super Admin dengan menggunakan ID yang tidak ada
    Then sistem seharusnya memberikan respons dengan status kode 500
    And Super Admin menerima pesan kesalahan validasi ID