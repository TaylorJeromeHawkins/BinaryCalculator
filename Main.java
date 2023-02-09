package meTaylor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
//import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class Main extends Application {

    private static Stage historyStage = null;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/calculator.fxml"));
        Image image = new Image("meTaylor/Pictures/calculator.png");    
        stage.getIcons().add(image);

        stage.setTitle("CALCULATOR");
        stage.setScene(new Scene(root, 400, 525));
        stage.setResizable(false);
        stage.show();

        createHistoryStage();
    }

    private void createHistoryStage() {
        historyStage = new Stage();
        historyStage.setTitle("HISTORY");
        //historyStage.setAlwaysOnTop(true);
        historyStage.setResizable(false);
        historyStage.initModality(Modality.NONE);

        Image image = new Image("meTaylor/Pictures/calculator.png");    
        historyStage.getIcons().add(image);
    }

    public static Stage getHistoryStage() {
        return historyStage;
    }
}