Feature: Delete Product

Background:
  Given Admin memasukkan endpoint yang valid untuk menghapus produk

Scenario: Product berhasil dihapus
  When Admin send request untuk menghapus product
  Then Muncul status code 204
  And Produk berhasil dihapus

Scenario: Menghapus product dengan ID yang invalid
  When Admin send request untuk menghapus product dengan ID invalid
  Then Muncul status code 404
  And Product gagal dihapus