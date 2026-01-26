package gui;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
// import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
import javafx.scene.control.cell.PropertyValueFactory;
import data_mahasiswa.mahasiswa;
import data_mahasiswa.MahasiswaData;
// import data_mahasiswa.MahasiswaFileUtil;
import Database.Koneksi;

public class DataView {

    public VBox getView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label title = new Label("Data Mahasiswa");

        // Tombol untuk refresh data dari database
        HBox buttonBox = new HBox(10);
        Button btnRefresh = new Button("Refresh dari Database");

        TableView<mahasiswa> table = new TableView<>();
        table.setItems(MahasiswaData.listMahasiswa);

        TableColumn<mahasiswa, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNama.setStyle("-fx-alignment: CENTER-LEFT;");

        TableColumn<mahasiswa, String> colNim = new TableColumn<>("NIM");
        colNim.setCellValueFactory(new PropertyValueFactory<>("nim"));
        colNim.setStyle("-fx-alignment: CENTER-LEFT;");

        TableColumn<mahasiswa, Integer> colUmur = new TableColumn<>("Umur");
        colUmur.setCellValueFactory(new PropertyValueFactory<>("umur"));
        colUmur.setStyle("-fx-alignment: CENTER-LEFT;");

        TableColumn<mahasiswa, Void> colSettings = new TableColumn<>("Settings");
        colSettings.setCellFactory(col -> new javafx.scene.control.TableCell<>() {
            private final Button btnEdit = new Button("Edit");
            private final Button btnDelete = new Button("Hapus");
            private final HBox box = new HBox(8, btnEdit, btnDelete);

            {
                // center buttons inside cell
                box.setAlignment(Pos.CENTER);

                btnEdit.setOnAction(e -> {
                    mahasiswa m = getTableView().getItems().get(getIndex());
                    if (m == null)
                        return;
                    String oldNim = m.getNim();

                    Dialog<mahasiswa> dialog = new Dialog<>();
                    dialog.setTitle("Edit Mahasiswa");
                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(20);
                    grid.setVgap(40);

                    TextField tfNama = new TextField(m.getNama());
                    TextField tfNim = new TextField(m.getNim());
                    TextField tfUmur = new TextField(String.valueOf(m.getUmur()));

                    grid.add(new javafx.scene.control.Label("Nama:"), 0, 0);
                    grid.add(tfNama, 1, 0);
                    grid.add(new javafx.scene.control.Label("NIM:"), 0, 1);
                    grid.add(tfNim, 1, 1);
                    grid.add(new javafx.scene.control.Label("Umur:"), 0, 2);
                    grid.add(tfUmur, 1, 2);

                    dialog.getDialogPane().setContent(grid);

                    dialog.setResultConverter(btn -> {
                        if (btn == ButtonType.OK) {
                            try {
                                int newUmur = Integer.parseInt(tfUmur.getText());
                                return new mahasiswa(tfNama.getText(), tfNim.getText(), newUmur);
                            } catch (NumberFormatException ex) {
                                return null;
                            }
                        }
                        return null;
                    });

                    dialog.showAndWait().ifPresent(updated -> {
                        Koneksi.updateMahasiswa(oldNim, updated);
                        getTableView().getItems().set(getIndex(), updated);
                        getTableView().refresh();
                    });
                });

                btnDelete.setOnAction(e -> {
                    mahasiswa m = getTableView().getItems().get(getIndex());
                    if (m == null)
                        return;
                    Koneksi.deleteMahasiswa(m.getNim());
                    getTableView().getItems().remove(m);
                });
            }

            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });

        table.getColumns().addAll(colNama, colNim, colUmur, colSettings);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Action untuk tombol Refresh
        btnRefresh.setOnAction(e -> {
            Koneksi.loadDariDatabase();
            table.refresh();
        });

        // Action untuk tombol Delete
        // btnDelete.setOnAction(e -> {
        // mahasiswa selected = table.getSelectionModel().getSelectedItem();
        // if (selected != null) {
        // MahasiswaFileUtil.deleteMahasiswa(selected.getNim());
        // table.refresh();
        // }
        // });

        buttonBox.getChildren().addAll(btnRefresh);

        layout.getChildren().addAll(title, buttonBox, table);
        return layout;
    }
}