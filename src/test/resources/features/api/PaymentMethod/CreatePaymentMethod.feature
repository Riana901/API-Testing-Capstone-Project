Feature: Create Payment Method

  Background:
    Given Admin mengakses menggunakan endpoint yang valid untuk create payment method

Scenario:Admin mencoba membuat payment method baru
  When Admin send request untuk membuat payment method baru
  Then Muncul status code 201
  And Payment method baru telah dibuat

Scenario: Admin mencoba membuat payment method baru dengan data Invalid
  When Admin send request dengan data yang invalid untuk membuat payment method baru
  Then Muncul status code 400
  And Payment Method gagal dibuat