@GetAdminByUsername
Feature: Get Admin By Username
  
  Background: 
    Given Super Admin mengakses API Endpoint untuk mendapatkan data admin berdasarkan username

  Scenario: Super Admin berhasil mendapatkan informasi admin berdasarkan username
    When Super Admin mengakses endpoint untuk mendapatkan informasi admin berdasarkan username yang ada
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya menampilkan informasi lengkap tentang admin dengan username tersebut

  Scenario: Super Admin mencoba mendapatkan informasi admin dengan username yang tidak ada
    When Super Admin mengakses endpoint untuk mendapatkan informasi admin dengan username yang tidak ada
    Then sistem seharusnya memberikan respons dengan status kode 404
    And sistem seharusnya menampilkan pesan kesalahan untuk admin yang tidak ditemukan

