#  Tucil3_13522009
Word ladder (juga dikenal sebagai Doublets, word-links, change-the-word puzzles,
paragrams, laddergrams, atau word golf) adalah salah satu permainan kata yang terkenal
bagi seluruh kalangan. Word ladder ditemukan oleh Lewis Carroll, seorang penulis dan
matematikawan, pada tahun 1877. Pada permainan ini, pemain diberikan dua kata yang
disebut sebagai start word dan end word. Untuk memenangkan permainan, pemain harus
menemukan rantai kata yang dapat menghubungkan antara start word dan end word.
Banyaknya huruf pada start word dan end word selalu sama. Tiap kata yang berdekatan
dalam rantai kata tersebut hanya boleh berbeda satu huruf saja. Pada resitory ini,
dibuatkan program untuk pencarian solusi optimal yang meminimalkan banyaknya kata yang dimasukkan
pada rantai kata dengan pendekatan algoritma UCS, Greedy Best First Search, dan A*.
## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Usage](#usage)
* [Contact](#contact)
<!-- * [License](#license) -->


## General Information
- Projek ini mencari solusi teroptimal yang dapat ditempuh dalam suatu rute yang dapat memberikan rantai kata terdikit.
- Algoritma A* memberikan solusi teroptimal secara efisien, di sisi lain UCS memberikan solusi teroptimal tapi tidak efisien. Sementara, GBFS memiliki waktu eksekusi paling cepat, namun tidak memberikan solusi optimal.
<!-- You don't have to answer all the questions - just the ones relevant to your project. -->


## Tech Stacks
- Java - version 17.0.8.1


## Features
Program menggunakan Command Line Interface(CLI):
- Manual input; User memasukkan jenis algoritma, startword, targetword, lalu akan dicari solusi optimalnya.
<img width="213" alt="Screenshot 2024-05-06 211154" src="https://github.com/cupski/Tucil1_13522009/assets/118907510/56d1a4a8-009f-44b6-ac54-d53e3bc94d41">

- GUI input; User memasukkan startword, targetword, jenis algoritma, lalu akan dicari solusi optimalnya.
<img width="290" alt="Screenshot 2024-05-06 211319" src="https://github.com/cupski/Tucil1_13522009/assets/118907510/88678926-afd7-45eb-9cd9-048350ac2a89">


## Setup
1. Install Java


## Usage
```shell
git clone https://github.com/cupski/Tucil3_13522009.git
```
pindahkan direktori folder yang berisi kode program
```shell
cd Tucil3_13522009
```
Compile Program(Optional)
```shell
javac -d bin src/*.java
```
Run
```shell
java -cp bin Main
```

## Project Status
Project ini : Selesai.



## Contact
Created by (cupski) Muhammad Yusuf Rafi/13522009.


<!-- Optional -->
<!-- ## License -->
<!-- This project is open source and available under the [... License](). -->

<!-- You don't have to include all sections - just the one's relevant to your project -->
