package gui;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.cell.PropertyValueFactory;
import data_mahasiswa.mahasiswa;
import data_mahasiswa.MahasiswaData;
import data_mahasiswa.MahasiswaFileUtil;

public class DataView {

    public VBox getView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label title = new Label("Data Mahasiswa");

        // Tombol untuk refresh data dari database
        HBox buttonBox = new HBox(10);
        Button btnRefresh = new Button("Refresh dari Database");
        Button btnDelete = new Button("Hapus Terpilih");
        
        TableView<mahasiswa> table = new TableView<>();
        table.setItems(MahasiswaData.listMahasiswa);

        TableColumn<mahasiswa, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<mahasiswa, String> colNim = new TableColumn<>("NIM");
        colNim.setCellValueFactory(new PropertyValueFactory<>("nim"));

        TableColumn<mahasiswa, Integer> colUmur = new TableColumn<>("Umur");
        colUmur.setCellValueFactory(new PropertyValueFactory<>("umur"));

        table.getColumns().addAll(colNama, colNim, colUmur);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Action untuk tombol Refresh
        btnRefresh.setOnAction(e -> {
            MahasiswaFileUtil.loadDariDatabase();
            table.refresh();
        });

        // Action untuk tombol Delete
        btnDelete.setOnAction(e -> {
            mahasiswa selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                MahasiswaFileUtil.deleteMahasiswa(selected.getNim());
                table.refresh();
            }
        });

        buttonBox.getChildren().addAll(btnRefresh, btnDelete);
        layout.getChildren().addAll(title, buttonBox, table);
        return layout;
    }
}