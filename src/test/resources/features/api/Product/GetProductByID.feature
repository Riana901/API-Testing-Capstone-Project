Feature: Get Product By ID

  Background:
    Given Admin mengakses dengan endpoint yang valid untuk Get Product By ID

Scenario: Admin mencoba mendapat get all product by Id dengan valid
  When Admin send request untuk Get Product by ID
  Then Muncul status code 200
  And Data produk berdasarkan ID berhasil ditampilkan

Scenario: Admin akses dengan ID yang invalid
  When Admin send request Get Product by ID dengan ID yang invalid
  Then Muncul status code 404
  And Data Produk gagal ditampilkan