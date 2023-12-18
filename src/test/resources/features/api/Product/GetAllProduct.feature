Feature: Get All product

  Background:
    Given Admin memasukkan endpoint untuk Get All Product

Scenario: Admin mencoba Get All Product dengan valid
  When Admin send request untuk Get All Product
  Then Muncul status code 200
  And Semua data produk berhasil ditampilkan
