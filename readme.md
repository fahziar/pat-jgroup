# Member of Groups
* Fahziar Riesad Wutono / 13512012
* Luthfi Hamid Masykuri / 13512100

# How to run

 1. masuk ke directory ```bin```
 2. Jalankan program dengan mengetik ```java -jar pat-jgroup.jar```
 3. Jika berhasil akan muncul tampilan seperti ini. Dan selanjutnya tinggal pilih opsinya.

```
-------------------------------------------------------------------
                Welcome to Jgroups Simple Client
-------------------------------------------------------------------
 1. Replicated Stack
 2. Replicated Set
-------------------------------------------------------------------
 Your Choice :
 ```
 # How to test
 ## Replicated Stack
 ### How to use
 1. Pilih opsi satu pada program
 2. Untuk melakukan ```push``` ke Stack, cukup ketik ```push <somestring>```
 3. Untuk melakukan ```pop``` dari Stack, cukup ketik ```pop```
 4. Untuk melihat ```top``` dari Stack, cukup ketik ```top```
 5. Untuk keluar dari program ketik ```quit```
 
### Test Case

#### Mengecek elemen teratas stack yang telah terisi oleh client lain

 1. Jalankan 2 client
 2. Pada client 1, masukkan perintah ```push hello```
 4. Pada client 2 masukkan perintah ```top```
 5. Pada client 2 akan muncul teks ```hello```

#### Mengecek elemen teratas stack kosong

 1. Jalankan 1 client
 2. Masukkan perintah ```top```
 3. Akan muncul peringatan ```Stack empty```
 
#### Komunikasi 3 client
 
1.  Jalankan 2 client
2. Pada client 1, masukkan perintah ```push hello``` 
3. Pada client 2, masukkan perintah ```top```
4. Pada client 2 akan muncul ```hello```
5. Jalankan client ketiga
6. Pada client 3, masukkan perintah ```push hello, world```
7. Pada client 2, masukkan perintah ```pop```
8. Pada client 2 akan muncul ```Popped String : hello, world```
9. Pada client 1, masukkan perintah ```top```
10. Pada client 1 akan muncul ```hello```
11. Pada client 2, masukkan perintah ```pop```
12. Pada client 2 akan muncul ```Popped String : hello```
13. Pada client 1, masukkan perintah ```top```
14. Pada client 1 akan muncul ```Stack empty```

## Replicated Set

### How to use
 1. Pilih opsi dua pada program
 2. Untuk melakukan ```add``` ke Set, cukup ketik ```add <somestring>```
 3. Untuk melakukan ```remove``` dari Set, cukup ketik ```remove <somestring>```
 4. Untuk mengecek apakah sebuah Set ```contains``` sebuah string, cukup ketik ```contains <somestring>```
 5. Untuk keluar dari program ketik ```quit```

### Test Case

#### Mengecek apakah sebuah string terdapat di set

 1. Jalankan 2 client
 2. Pada client 1, masukkan perintah ```add hello```
 3. Pada client 2 masukkan perintah ```contains hello```
 4. Pada client 2 akan muncul teks ```hello belongs to set```

#### Menghapus member set

 1. Jalankan 2 client
 2. Pada client 1, masukkan perintah ```add hello```
 3. Pada client 2 masukkan perintah ```remove hello```
 4. Pada client 1 masukkan perintah ```contains hello```
 5. Pada client 2 akan muncul teks ```hello not belongs to set```
 
#### Komunikasi 3 client
 
1.  Jalankan 2 client
2. Pada client 1, masukkan perintah ```add hello``` 
3. Pada client 2, masukkan perintah ```contains hello```
4. Pada client 2 akan muncul ```hello belongs to set```
5. Jalankan client ketiga
6. Pada client 3, masukkan perintah ```add hello world```
7. Pada client 2, masukkan perintah ```contains hello world```
8. Pada client 2 akan muncul ```hello world belongs to set```
9. Pada client 1, masukkan perintah ```remove hello world```
10. Pada client 1 masukkan perintah ```contains hello world``` 
11. Pada client 1, akan muncul ```hello world not belongs to set```