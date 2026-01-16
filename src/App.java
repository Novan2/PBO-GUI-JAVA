import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.MainView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Memanggil kelas UI Utama
        MainView mainView = new MainView();

        // Membuat Scene dengan root dari mainView
        Scene scene = new Scene(mainView.getMainLayout(), 800, 600);

<<<<<<< HEAD
=======
        // Menghubungkan file CSS
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

>>>>>>> sandi/main
        primaryStage.setTitle("Aplikasi JavaFX Rapi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}