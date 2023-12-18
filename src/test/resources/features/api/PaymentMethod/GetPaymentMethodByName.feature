Feature: Get Payment Method By Name

  Background:
    Given Admin mengakses menggunakan endpoint yang valid untuk Get Payment Method By Name

Scenario: Admin mencoba Get Payment Method By Name Valid
  When Admin send request untuk Get Payment Method by Name
  Then Muncul status code 200
  And Payment Method sesuai nama berhasil ditampilkan

Scenario: Admin mencoba Get Payment Method By Name Invalid
  When Admin send request menggunakan nama yang invalid untuk Get Payment Method By Name
  Then Muncul status code 404
  And Payment Method sesuai nama gagal ditampilkan
