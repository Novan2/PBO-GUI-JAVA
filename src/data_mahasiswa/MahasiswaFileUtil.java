package data_mahasiswa;

import java.io.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import Database.Koneksi;

public class MahasiswaFileUtil {

    private static final String FILE_PATH = "data_mahasiswa.csv";

    public static void simpanKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (mahasiswa m : MahasiswaData.listMahasiswa) {
                writer.write(m.getNama() + "," + m.getNim() + "," + m.getUmur());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    public static void loadDariFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            MahasiswaData.listMahasiswa.clear();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String nama = data[0];
                String nim = data[1];
                int umur = Integer.parseInt(data[2]);
                MahasiswaData.listMahasiswa.add(new mahasiswa(nama, nim, umur));
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }

    // Method untuk menambah data mahasiswa ke database
    public static boolean tambahMahasiswa(mahasiswa m) {
        boolean berhasil = Koneksi.insertMahasiswa(m);
        if (berhasil) {
            MahasiswaData.listMahasiswa.add(m);
            simpanKeFile();
            System.out.println("Data mahasiswa berhasil ditambahkan");
        } else {
            System.out.println("Gagal menambahkan data mahasiswa ke database");
        }
        return berhasil;
    }

    // Method untuk memuat semua data dari database
    public static void loadDariDatabase() {
        try {
            MahasiswaData.listMahasiswa.clear();
            ResultSet rs = Koneksi.getAllMahasiswa();
            
            if (rs != null) {
                while (rs.next()) {
                    String nama = rs.getString("nama");
                    String nim = rs.getString("nim");
                    int umur = rs.getInt("umur");
                    MahasiswaData.listMahasiswa.add(new mahasiswa(nama, nim, umur));
                }
                rs.getStatement().close();
                System.out.println("Data berhasil dimuat dari database");
            }
        } catch (SQLException e) {
            System.err.println("Gagal load data dari database: " + e.getMessage());
        }
    }

    // Method untuk update data mahasiswa di database
    public static boolean updateMahasiswa(String nimLama, mahasiswa m) {
        boolean berhasil = Koneksi.updateMahasiswa(nimLama, m);
        if (berhasil) {
            // Update di list juga
            for (mahasiswa mhs : MahasiswaData.listMahasiswa) {
                if (mhs.getNim().equals(nimLama)) {
                    mhs.setNama(m.getNama());
                    mhs.setUmur(m.getUmur());
                    mhs.setNim(m.getNim());
                    break;
                }
            }
            simpanKeFile();
            System.out.println("Data mahasiswa berhasil diupdate");
        } else {
            System.out.println("Gagal mengupdate data mahasiswa");
        }
        return berhasil;
    }

    // Method untuk delete data mahasiswa dari database
    public static boolean deleteMahasiswa(String nim) {
        boolean berhasil = Koneksi.deleteMahasiswa(nim);
        if (berhasil) {
            MahasiswaData.listMahasiswa.removeIf(m -> m.getNim().equals(nim));
            simpanKeFile();
            System.out.println("Data mahasiswa berhasil dihapus");
        } else {
            System.out.println("Gagal menghapus data mahasiswa");
        }
        return berhasil;
    }
}