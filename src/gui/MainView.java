package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class MainView {

    private BorderPane mainLayout;

    public MainView() {
        mainLayout = new BorderPane();

        // Memanggil fungsi-fungsi untuk mengisi bagian frame
        mainLayout.setLeft(createSidebar()); // Menu samping (Panel)
        mainLayout.setCenter(createContent()); // Konten utama (Panel)
    }

    // Fungsi untuk membuat Panel Sidebar (Menu)
    private VBox createSidebar() {
        VBox sidebar = new VBox(10); // Spasi antar tombol 10px
        sidebar.setPadding(new Insets(15));
        sidebar.getStyleClass().add("sidebar"); // Menggunakan class CSS

        Label menuLabel = new Label("MENU UTAMA");
        menuLabel.getStyleClass().add("menu-label"); // Menggunakan class CSS

        Button btnHome = new Button("Dashboard");
        Button btnData = new Button("Data Mahasiswa");
        Button btnSettings = new Button("Pengaturan");

        // Mengatur agar tombol memenuhi lebar sidebar dan menambahkan class CSS
        btnHome.setMaxWidth(Double.MAX_VALUE);
        btnHome.getStyleClass().add("nav-button");
        btnHome.setOnAction(e -> mainLayout.setCenter(new DashboardView().getView()));

        btnData.setMaxWidth(Double.MAX_VALUE);
        btnData.getStyleClass().add("nav-button");
        btnData.setOnAction(e -> mainLayout.setCenter(new DataView().getView()));


        btnSettings.setMaxWidth(Double.MAX_VALUE);
        btnSettings.getStyleClass().add("nav-button");

        sidebar.getChildren().addAll(menuLabel, btnHome, btnData, btnSettings);
        return sidebar;
    }

    // Fungsi untuk membuat Panel Konten Tengah
    private StackPane createContent() {
        StackPane contentArea = new StackPane();
        contentArea.setPadding(new Insets(20));

        Label welcomeText = new Label("Selamat Datang di Aplikasi");
        welcomeText.getStyleClass().add("welcome-text"); // Menggunakan class CSS

        contentArea.getChildren().add(welcomeText);
        return contentArea;
    }

    // Getter agar bisa dipanggil oleh App.java
    public BorderPane getMainLayout() {
        return mainLayout;
    }
}
