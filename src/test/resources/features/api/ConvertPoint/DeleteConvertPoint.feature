Feature: Delete Convert Point

  Background:
    Given Admin akses dengan endpoint yang valid untuk delete convert point

Scenario: Admin menghapus convert point dengan valid
  When Admin send request untuk menghapus convert point
  Then Muncul status code 200
  And Convert Point berhasil dihapus