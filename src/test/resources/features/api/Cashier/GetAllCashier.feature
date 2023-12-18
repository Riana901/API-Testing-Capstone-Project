@ManageCashierAccount
Feature:  Get All Cashier

  Scenario: Sebagai admin saya ingin mendapatkan semua data Kasir
    Given Admin mengakses endpoint untuk mendapatkan semua Kasir
    When Admin mengirim request untuk mendapatkan semua kasir
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya menampilkan daftar semua Kasir yang tersedia


