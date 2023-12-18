@AddProductType
Feature: Add Product Type


  Background:
    Given Admin mengatur API Endpoint untuk menambah tipe produk baru

  Scenario: Admin berhasil menambahkan tipe produk baru
    When Admin mengirimkan permintaan untuk menambahkan tipe produk baru dengan data valid
    Then sistem seharusnya memberikan respons dengan status kode 201
    And sistem seharusnya menampilkan pesan sukses untuk Product Type berhasil ditambahkan

  Scenario: Admin tidak memasukkan data yang diperlukan
    When Admin mengirimkan permintaan untuk menambahkan tipe produk baru tanpa memasukkan data yang diperlukan
    Then sistem seharusnya memberikan respons dengan status kode 500
    And sistem seharusnya menampilkan pesan kesalahan untuk Data yang diperlukan tidak lengkap

  Scenario: Admin mencoba menambahkan tipe produk dengan typeName yang sudah ada
    When Admin mengirimkan permintaan untuk menambahkan tipe produk baru dengan typeName yang sudah ada
    Then sistem seharusnya memberikan respons dengan status kode 409
    And sistem seharusnya menampilkan pesan kesalahan untuk Product Type dengan typeName tersebut sudah ada

