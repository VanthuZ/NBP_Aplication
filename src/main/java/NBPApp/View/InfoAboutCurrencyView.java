package NBPApp.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoAboutCurrencyView {
    private Stage primaryStage;

    public InfoAboutCurrencyView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void loadView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/InfoAboutCurrency.fxml"));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        primaryStage.setTitle("NBP Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
