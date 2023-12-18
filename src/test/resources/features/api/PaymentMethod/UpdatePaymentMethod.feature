Feature: Update Payment Method

  Background:
    Given Admin mengakses menggunakan endpoint yang valid untuk Update Payment Method

Scenario: Admin mencoba melakukan update Payment Method dengan valid
  When Admin send request untuk Update Payment Method
  Then Muncul status code 200
  And Update Payment Method berhasil

Scenario: Admin update payment method dengan ID Invalid
  When Admin send request untuk update payment method menggunakan ID yang invalid
  Then Muncul status code 400
  And Update Payment Method gagal