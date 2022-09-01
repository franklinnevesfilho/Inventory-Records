package com.abcenterprises.inventoryrecords;

import com.abcenterprises.inventoryrecords.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public Database database = new Database();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainController controller = fxmlLoader.getController();
        controller.setDatabase(database);

        String css = this.getClass().getResource("css/main-view.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("ABC Enterprise Records");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}