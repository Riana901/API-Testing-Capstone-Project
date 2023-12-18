@GetAllProductType
Feature: Get All Product Type

  Background:
Given Admin mengatur API Endpoint untuk mendapatkan semua data tipe produk

Scenario: Admin berhasil mendapatkan semua tipe produk
When Admin mengakses endpoint untuk mendapatkan semua tipe produk
Then sistem seharusnya memberikan respons dengan status kode 200
And sistem seharusnya menampilkan daftar semua tipe produk yang tersedia

