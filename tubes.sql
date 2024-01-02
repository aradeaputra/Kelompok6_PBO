-- Membuat tabel admin
CREATE TABLE admin (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    nip INT(30),
    password VARCHAR(30),
    nama VARCHAR(100)
);

-- Membuat tabel dosen
CREATE TABLE dosen (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    nip INT(30),
    password VARCHAR(30),
    nama VARCHAR(100)
);

-- Membuat tabel kuis
CREATE TABLE kuis (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    kode_mk VARCHAR(3),
    nama_kuis VARCHAR(30),
    minggu_kuis INT(11)
);

-- Membuat tabel mahasiswa
CREATE TABLE mahasiswa (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    nip INT(30),
    password VARCHAR(30),
    nama VARCHAR(100)
);

-- Membuat tabel mata_kuliah
CREATE TABLE mata_kuliah (
    kode_mk VARCHAR(3),
    nama VARCHAR(30)
);

-- Membuat tabel pertanyaan
CREATE TABLE pertanyaan (
    id_kuis INT(11),
    soal VARCHAR(100),
    jawaban VARCHAR(100)
);

-- Insert data ke tabel admin
INSERT INTO admin (nip, password, nama) VALUES (123456, 'admin', 'Admin');