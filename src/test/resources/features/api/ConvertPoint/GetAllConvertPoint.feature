Feature: Get All Convert Point

  Background:
    Given Admin akses dengan endpoint yang valid untuk Get All Convert Point

Scenario: Admin mencoba Get All Convert Point dengan valid
  When Admin send request Get All Convert Point dengan valid
  Then Muncul status code 200
  And Semua convert point berhasil ditampilkan

