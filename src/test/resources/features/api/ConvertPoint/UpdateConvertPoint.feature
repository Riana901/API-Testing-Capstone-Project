Feature: Update Convert Point

  Background:
    Given Admin mengakses dengan endpoint yang valid untuk update convert point

Scenario: Admin akses update convert point dengan valid
  When Admin send request dengan valid untuk melakukan update Convert Point
  Then Muncul status code 200
  And Update berhasil

Scenario: Admin akses dengan ID yang invalid
  When Admin send request dengan ID yang invalid untuk update
  Then Muncul status code 404
  And Update gagal dilakukan