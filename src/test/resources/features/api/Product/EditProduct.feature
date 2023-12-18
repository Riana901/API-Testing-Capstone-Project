Feature: Edit Product

  Background:
    Given Admin memasukkan endpoint yang valid untuk mengedit product

Scenario: Admin mengedit product dengan valid
  When Admin send request untuk mengedit product
  Then Muncul status code 404
  And Product berhasil diedit

Scenario: Admin mengedit product dengan ID yang invalid
  When Admin send request untuk mengedit produk dengan ID invalid
  Then Muncul status code 404
  And Product gagal untuk diedit