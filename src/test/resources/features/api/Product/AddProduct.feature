Feature: Add Product

  Background:
    Given Admin mengakses endpoint untuk menambahkan produk

Scenario: Admin berhasil menambahkan produk
  When Admin send request dengan method PUT untuk menambahkan produk
  Then Muncul status code 400
  And Produk berhasil ditambahkan
