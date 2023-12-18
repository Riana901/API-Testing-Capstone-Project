Feature: Get Payment Method By ID

  Background:
    Given Admin mengakses menggunakan endpoint yang valid untuk Get Payment Method By ID

Scenario: Admin mencoba Get Payment Method By ID dengan valid
  When Admin send request untuk Get All Payment Method By ID
  Then Muncul status code 200
  And Payment Method sesuai ID berhasil ditampilkan

Scenario: Admin mencoba Invalid ID untuk mencari payment method
  When Admin send request menggunakan ID yang salah pada Get Payment Method by ID
  Then Muncul status code 404
  And Payment Method sesuai ID gagal ditampilkan