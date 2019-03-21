package NBPApp;

import NBPApp.View.InfoAboutCurrencyView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    InfoAboutCurrencyView infoCurrenctView;


    @Override
    public void start(Stage primaryStage) throws Exception{
        infoCurrenctView = new InfoAboutCurrencyView(primaryStage);
        infoCurrenctView.loadView();
    }


    public static void main(String[] args) {
        launch(args);

    }

}
