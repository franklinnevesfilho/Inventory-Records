package com.abcenterprises.inventoryrecords;

import com.abcenterprises.inventoryrecords.controllers.MainController;
import com.abcenterprises.inventoryrecords.dataStorage.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Author: Franklin Neves Filho
 * Student ID: 6208239
 *
 * Title: Inventory Records
 *
 * Description:
 *      This program initializes a new Database object
 *      and through the GUI allows the user to update and
 *      modify the database.
 *
 *      For the GUI I have used JavaFX, as it allows for
 *      css styling throughout
 *
 *      the controllers package contains the controllers for the
 *      fxml files of the gui
 *
 *      This program as of now does not contain the ability to track sales,
 *      but provides a base for further development.
 *
 *      The application was created in IntelliJ IDE
 *      if used in Eclipse the Javafx sdk must be added 
 *      to the build path of the project 
 */

public class Application extends javafx.application.Application {
    // Once the application starts a new database is created.
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