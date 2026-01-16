package gui;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import data_mahasiswa.mahasiswa;
import data_mahasiswa.MahasiswaData;

public class DataView {

    public VBox getView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label title = new Label("Data Mahasiswa");

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

        layout.getChildren().addAll(title, table);
        return layout;
    }
}