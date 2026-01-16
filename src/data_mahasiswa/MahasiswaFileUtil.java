package data_mahasiswa;

import java.io.*;
import java.util.*;

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
}
