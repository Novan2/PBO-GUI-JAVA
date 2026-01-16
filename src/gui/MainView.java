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
        sidebar.setStyle("-fx-background-color: #2c3e50;"); // Warna gelap seperti frame profesional

        Label menuLabel = new Label("MENU UTAMA");
        menuLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Button btnHome = new Button("Dashboard");
        Button btnData = new Button("Data Mahasiswa");
        Button btnSettings = new Button("Pengaturan");

        // Mengatur agar tombol memenuhi lebar sidebar
        btnHome.setMaxWidth(Double.MAX_VALUE);
        btnData.setMaxWidth(Double.MAX_VALUE);
        btnSettings.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(menuLabel, btnHome, btnData, btnSettings);
        return sidebar;
    }

    // Fungsi untuk membuat Panel Konten Tengah
    private StackPane createContent() {
        StackPane contentArea = new StackPane();
        contentArea.setPadding(new Insets(20));

        Label welcomeText = new Label("Selamat Datang di Aplikasi");
        welcomeText.setStyle("-fx-font-size: 20px;");

        contentArea.getChildren().add(welcomeText);
        return contentArea;
    }

    // Getter agar bisa dipanggil oleh App.java
    public BorderPane getMainLayout() {
        return mainLayout;
    }
}
