package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import data_mahasiswa.mahasiswa;
import data_mahasiswa.MahasiswaData;
import data_mahasiswa.MahasiswaFileUtil;

public class DashboardView {

    public VBox getView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label title = new Label("Input Data Mahasiswa");

        TextField tfNama = new TextField();
        tfNama.setPromptText("Nama");

        TextField tfNim = new TextField();
        tfNim.setPromptText("NIM");

        TextField tfUmur = new TextField();
        tfUmur.setPromptText("Umur");

        Button btnSimpan = new Button("Simpan Data");
        Label info = new Label();

        btnSimpan.setOnAction(e -> {
            String nama = tfNama.getText();
            String nim = tfNim.getText();
            String umurStr = tfUmur.getText();

            if (nama.isEmpty() || nim.isEmpty() || umurStr.isEmpty()) {
                info.setText("Semua field wajib diisi!");
                return;
            }

            try {
                int umur = Integer.parseInt(umurStr);
                mahasiswa mhs = new mahasiswa(nama, nim, umur);
                
                // Memasukkan data ke database dan file CSV
                if (MahasiswaFileUtil.tambahMahasiswa(mhs)) {
                    info.setText("Data berhasil disimpan ke database!");
                } else {
                    info.setText("Gagal menyimpan data ke database!");
                    return;
                }

                tfNama.clear();
                tfNim.clear();
                tfUmur.clear();
            } catch (NumberFormatException ex) {
                info.setText("Umur harus berupa angka!");
            }
        });

        layout.getChildren().addAll(title, tfNama, tfNim, tfUmur, btnSimpan, info);
        return layout;
    }
}

