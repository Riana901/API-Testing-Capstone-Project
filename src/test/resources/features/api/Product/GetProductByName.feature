Feature: Get Product By Name

  Background:
    Given Admin memasukkan endpoint yang valid untuk Get Product By Name
    Given Admin memasukkan endpoint yang valid untuk Get Product By Name Invalid

Scenario: Admin Get Product by Name dengan valid
  When Admin send request untuk Get Product by Name dengan valid
  Then Muncul status code 404
  And Informasi produk sesuai nama berhasil ditampilkan

Scenario: Admin akses dengan nama yang salah
  When Admin send request untuk Get Product by Name dengan nama yang salah
  Then Muncul status code 404
  And Informasi produk sesuai nama gagal ditampilkan