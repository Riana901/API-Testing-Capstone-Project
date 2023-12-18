Feature: Get Convert Point By ID

  Background:
    Given Admin memasukkan endpoint yang valid untuk Get All Convert Point by ID

Scenario: Admin akses dengan ID yang valid
  When Admin send request dengan ID yang valid untuk Get All Convert Point by ID
  Then Muncul status code 200
  And Convert Point sesuai ID berhasil ditampilkan

Scenario: Admin akses dengan ID yang invalid
  When Admin send request dengan ID yang invalid untuk Get Convert Point by ID
  Then Muncul status code 404
  And convert point sesuai ID tidak ditemukan