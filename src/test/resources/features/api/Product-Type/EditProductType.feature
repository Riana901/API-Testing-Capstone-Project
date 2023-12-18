@EditProductType
Feature: Edit Product Type

  Background:
    Given Admin mengatur API Endpoint untuk mengedit data tipe produk

  Scenario: : Admin berhasil mengubah data tipe produk
    When Admin mengirimkan data yang valid untuk perubahan, seperti nama tipe produk yang baru
    Then sistem seharusnya memberikan respons dengan status kode 200
    And sistem seharusnya mengganti data tipe produk yang sesuai dengan permintaan Admin

  Scenario: Admin mencoba mengubah tipe produk yang tidak ada
    When Admin mengakses endpoint untuk mengubah data tipe produk dengan ID yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 404
    And sistem seharusnya menampilkan pesan kesalahan untuk produk yang tidak ditemukan

  Scenario: Admin mencoba mengubah tipe produk dengan data yang tidak valid
    When Admin mengakses endpoint untuk mengubah data tipe produk berdasarkan ID dengan typeName yang tidak valid
    Then sistem seharusnya memberikan respons dengan status kode 400
    And sistem seharusnya menampilkan pesan kesalahan validasi untuk typeName


