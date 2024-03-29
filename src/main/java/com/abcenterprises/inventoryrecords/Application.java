package com.abcenterprises.inventoryrecords;

import com.abcenterprises.inventoryrecords.controllers.LockscreenController;
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
 *      The dataStorage package contains the java classes used
 *      for the storage system.
 *
 *
 *      The controllers package contains the controllers for the
 *      fxml files of the gui.
 *
 *      This program as of now does not contain any transactions,
 *      but provides a base for further development.
 *
 *      The application was created in IntelliJ IDE
 *      if used in Eclipse the Javafx sdk must be added 
 *      to the build path of the project 
 */

public class Application extends javafx.application.Application {
    public static final String styleSheet = Application.class.getResource("css/lockscreen.css").toExternalForm();

    // Once the application starts a new database is created.
    public Database database = new Database();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/lockscreen-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        LockscreenController controller = fxmlLoader.getController();
        //controller.setDatabase(database);

        scene.getStylesheets().add(styleSheet);

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