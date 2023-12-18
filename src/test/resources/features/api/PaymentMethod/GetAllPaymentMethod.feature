Feature: Get All Payment Method

  Background:
    Given Admin mengakses menggunakan endpoint yang valid untuk Get All Payment Method

Scenario: Admin mencoba Get All Payment Method dengan valid
  When Admin send request untuk Get All Payment Method
  Then Muncul status code 200
  And Semua Payment Method berhasil ditampilkan



