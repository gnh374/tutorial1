# Refleksi 1
- #### Meaningful Names
Di sini saya menggunakan nama variabel dan nama function yang langsung menggambarkan variabel dan function tersebut. Seperti, getProductById() untuk mencari Produk berdasarkan idnya.

- #### Function
Saya memecah suatu tugas ke dalam beberapa function sehingga function yang saya buat hanya fokus melakukan 1 hal. Seperti membuat function getProductById() untuk memisahkannya dari function yang melakukan tugas lain.
- #### Layout dan Formatting
Saya memisahkan function atau block code yang berbeda dengan 1 buah line sehingga terlihat lebih rapih dan lebih mudah dibaca

- #### Objek and Data Structures
Saya menerapkan konsep ini pada projek ini dan menyadari bahwa pemahaman keduanya dapat membantu dalam pengembangan jika terjadi perubahan
- #### Secure coding
Saya menggunakan methode post untuk membuat produk baru melalui form


# Refleksi 2
- Setelah membuat unit tests, saya menjadi semakin yakin dengan kode yang saya buat karena unit test membantu memastikan setiap komponen dalam kode.
- Tidak ada angka pasti tentang jumlah unit test per kelas. Hal ini akan tergantung dari kompleksitas kelas, jumlah method dan lainnya. Penting untuk membuat unit test yang mengcover behavior dari kelasnya

- 100% code coverage tidak menjamin tidak adanya bugs dan error. Bisa jadi ada edge case yang belum dihandle oleh test yang dibuat

- Menurut saya kode akan menjadi kurang bersih karena pasti akan banyak kode yang berulang seperti pada bagian setup. Solusinya adalah dengan cukup membuat methode test baru pada kelas sebelumnya sehingga bisa menggunakan setup yang sama.