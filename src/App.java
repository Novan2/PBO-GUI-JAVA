import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import data_mahasiswa.MahasiswaFileUtil;
import gui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primaryStage) {
        MahasiswaFileUtil.loadDariFile();

        // Memanggil kelas UI Utama
        MainView mainView = new MainView();

        // Membuat Scene dengan root dari mainView
        Scene scene = new Scene(mainView.getMainLayout(), 800, 600);

        // Menghubungkan file CSS, dengan fallback ke file system agar tidak gagal saat resource tidak ter-copy
        URL cssResource = getClass().getResource("/style.css");
        if (cssResource != null) {
            scene.getStylesheets().add(cssResource.toExternalForm());
        } else {
            Path cssPath = Paths.get("src", "style.css");
            if (!cssPath.toFile().exists()) {
                cssPath = Paths.get("style.css");
            }
            if (cssPath.toFile().exists()) {
                scene.getStylesheets().add(cssPath.toUri().toString());
            }
        }

        primaryStage.setTitle("Aplikasi Data Mahasiswa");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}