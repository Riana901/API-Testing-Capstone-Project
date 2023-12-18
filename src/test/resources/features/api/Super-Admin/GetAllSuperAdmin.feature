@GetAllSuperAdmin
Feature: Get All Super Admin

Scenario: Super Admin berhasil mendapatkan semua Super Admin
Given Super Admin mengakses endpoint untuk mendapatkan semua Super Admin
When Super Admin mengirim request untuk mendapatkan semua Super Admin
Then sistem seharusnya memberikan respons dengan status kode 200
And sistem seharusnya menampilkan daftar semua Super Admin yang tersedia

