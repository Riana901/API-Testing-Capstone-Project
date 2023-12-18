@GetAdminByID
Feature: Get Admin By ID

  Background:
    Given Super Admin mengakses API Endpoint untuk mendapatkan data admin by ID

Scenario: Super Admin berhasil mendapatkan informasi diri berdasarkan ID
When Super Admin mengirim request untuk mendapatkan Admin by id
Then sistem seharusnya memberikan respons dengan status kode 200
And sistem seharusnya menampilkan informasi lengkap tentang Admin tersebut

Scenario: Super Admin mencoba mendapatkan informasi dengan ID yang tidak valid
When Super Admin mengakses endpoint untuk mendapatkan informasi dengan ID yang tidak valid
Then sistem seharusnya memberikan respons dengan status kode 404
And sistem seharusnya menampilkan pesan kesalahan untuk admin yang tidak ditemukan


