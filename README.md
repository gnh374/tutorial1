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

# Refleksi 3
- ### Quality issue(s)
  - Ada break dalam for loop yang menyebabkan loop pasti hanya berjalan sekali. Solusi : menghapus break
  - Menggunakan System.out.println() untuk debug. Solusi menghapus System.out.println()

- ### Penerapa CI/CD
    - Menurut saya, proyek ini sudah menerapkan CI/CD. Hal ini karena saya menggunakan github action untuk menjalankan workflow ci.yml, scorecard.yml, dan sonarcloud.yml yang akan secara otomatis menjalankan testing jika dilakukan suatu tindakan push atau pull request ke suatu branch. Hal ini merupakan penerapan CI dalam proyek ini. Selain itu, saya menggunakan platform Koyeb yang secara otomatis akan menjalankan deployment jika ada push atau pull request pada repository Github. Hal ini merupakan penerapa CD dalam proyek ini

# Refleksi 4
### Penerapan SOLID

- #### SRP(Single Responsibility Principle)
Prinsip SRP adalah prinsip yang mengatakan bahwa sebuah kelas harus hanya bertanggung jawab pada 1 komponen dalam program. Menurut saya, code saya sudah menerapkan prinsip ini karena saya memisahkan kelas Controller menjadi 3 kelas berdasarkan halamannya yaitu CarController, HomePageController, ProductController. Saya juga menghapus extends productController pada CarController agar kelas CarController berfokus pada Car saja. Selain itu saya juga memisahkan respository menjadi CarRepository yang nantinya akan berhubungan dengan data Car dan ProductRepository yang nantinya akan berhubungan dengan data Product. Saya juga memisahkan kelas service yang menangani permintaan dari masing-masing controller sehingga setiap controller akan ditangani oleh masing-masing kelas.
- #### OCP(Open-Closed Principle)
Prinsip ini mengatakan bahwa software entities harus terbuka untuk pengembangan namun tertutup dengan modifikasi. Saya menerapkan hal ini dengan mengubah bagian method update pada kelas CarRepository yang awalnya melakukan set pada setiap atribut menjadi mengganti object yang ada pada list. Hal ini dilakukan agar jika kita membuat subclass Car atau ada attribut Car yanng kita ubah bisa memanfaatkan CarRepository ini.
- #### LSP (Liskov Subtitution Principle)
Prinsip ini mengatakan bahwa objek superclass harus bisa digantikan oleh objek dari subclass tanpa membuat error dalam artian subclass harus memiliki behaviour yang serupa dengan superclass. Prinsip ini berlaku jika kita menerapkan inheritence dan code saya tidak menerapkan prinsip inheritence ini sehingga saya simpulkan code saya tidak melanggar prinsip ini.
- #### ISP (Interface Segregation Principle)
Prinsip ini menekankan untuk memecah interface yang besar menjadi interface yang lebih kecil sehingga kelas implementasinya hanya mengambil methode yang relevan. Menurut saya, code saya sudah mengimplementasikan prinsip ini dengan memecah interface ProductService dan interface CarService.
- #### DIP (Dependency Inversion Principle)
Prinsip ini mengatakan bahwa suatu class seharusnya bergantung pada abstraksi bukan kepada kelas concretenya. Code saya sudah menerapkan prinsip ini yaitu kelas CarController dan ProductController bergantung pada interface CarService dan ProductService bukan pada kelas implementasinya

### Keuntungan menerapkan prinsip SOLID:
- Code jadi lebih mudah dimaintain, karena teradi pemisahan tujuan setiap kelas 
- Code jadi lebih mudah dibaca,  saat kita membaca kelas CarController kita tahu bahwa ini mapping yang berhubungan dengan objek Car
- Code jadi lebih mudah ditest, saat kita ingin melakukan test kelas CarController, kita bisa yakin bahwa depedencynya berhubungan dengan Car saja
- Code jadi lebih mudah dikelola, Saat kita ingin mengganti yang berhubungan dengan logic bisnis dari Car, kita hanya perlu menggantu kelas CarService

### Kerugian menerapkan prinsip SOLID:
- meningkatkan kompleksitas code, karena banyak melakukan pemisahan file
- Terdapat banyak kelas dan interface,setiap kelas serviceimplementation jadi harus memiliki interface service
- Sulit untuk dipahami, karena semakin banyak pemisahan dan semakin banyak file
- Menjadi butuh lebih banyak waktu dalam pengembangan, karena memperhatikan banyak hal tidak hanya fungsionalitas