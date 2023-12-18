@AddPaymentType
Feature: Add Payment Type

  Background:
    Given Admin mengatur API Endpoint untuk menambah tipe pembayaran baru

  Scenario: Admin berhasil menambahkan tipe pembayaran baru
    When Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru dengan data valid
    Then sistem seharusnya memberikan respons dengan status kode 201
    And sistem seharusnya menampilkan pesan sukses untuk Payment Type berhasil ditambahkan


  Scenario: : Admin tidak memasukkan data yang diperlukan
    When Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru tanpa memasukkan data yang diperlukan
    Then sistem seharusnya memberikan respons dengan status kode 500
    And sistem seharusnya menampilkan pesan kesalahan untuk Data tipe pembayaran yang diperlukan tidak lengkap

  Scenario: : Admin mencoba menambahkan tipe pembayaran dengan typeName yang sudah ada
    When Admin mengirimkan permintaan untuk menambahkan tipe pembayaran baru dengan typeName yang sudah ada
    Then sistem seharusnya memberikan respons dengan status kode 409
    And sistem seharusnya menampilkan pesan kesalahan untuk Payment Type dengan typeName tersebut sudah ada
