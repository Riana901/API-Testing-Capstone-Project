@GetAllAdmin
Feature: Get All Admin

  Background:
    Given Super Admin mengakses API Endpoint untuk mengakses semua akun admin

Scenario: Super Admin berhasil mendapatkan semua akun admin
When Super Admin mengirim request untuk mendapatkan semua Admin
Then sistem seharusnya memberikan respons dengan status kode 200
Then sistem seharusnya menampilkan daftar semua akun admin yang tersedia

Scenario: Super Admin Unauthorized access tanpa token
When Super Admin mencoba mengakses endpoint untuk mendapatkan semua akun admin tanpa token
Then sistem seharusnya memberikan respons dengan status kode 401
And Super Admin menerima pesan kesalahan untuk token
