Feature: Create Convert Point

  Background:
    Given Admin mengakses endpoint untuk membuat convert point dengan valid

Scenario: Admin berhasil membuat convert point
  When Admin send request untuk membuat convert point dengan valid
  Then Muncul status code 201
  And Convert Point baru berhasil dibuat
