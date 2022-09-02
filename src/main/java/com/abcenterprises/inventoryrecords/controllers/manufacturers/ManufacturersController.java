package com.abcenterprises.inventoryrecords.controllers.manufacturers;

import com.abcenterprises.inventoryrecords.Application;
import com.abcenterprises.inventoryrecords.Database;
import com.abcenterprises.inventoryrecords.Manufacturer;
import com.abcenterprises.inventoryrecords.controllers.manufacturers.AddManufacturersController;
import com.abcenterprises.inventoryrecords.controllers.products.EditProductsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManufacturersController implements Initializable {

    Database database;
    Manufacturer manufacturerSelected;
    @FXML
    Button addManufacturerBtn;
    @FXML
    Button deleteBtn;
    @FXML
    Button editBtn;
    @FXML
    TableView<Manufacturer> manufacturerTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Product Name
        TableColumn<Manufacturer, String> companyColumn = new TableColumn<>("Company Name");
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

        // Purchase Date
        TableColumn<Manufacturer, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        manufacturerTable.getColumns().clear();
        manufacturerTable.getColumns().addAll(
                companyColumn,
                addressColumn
        );

        // disable delete button at start
        deleteBtn.setDisable(true);
        editBtn.setDisable(true);

        manufacturerTable.getSelectionModel().selectedIndexProperty().addListener(((observableValue, manufacturer, t1) ->{

            deleteBtn.setDisable(false);
            editBtn.setDisable(false);

            manufacturerSelected = manufacturerTable.getSelectionModel().getSelectedItem();

            deleteBtn.setOnAction(actionEvent -> deleteManufacturer(manufacturerSelected));
        } ));

    }

    private void deleteManufacturer(Manufacturer manufacturerSelected) {
        database.removeManufacturer(manufacturerSelected);
    }

    public void loadManufacturers(Database database){
        this.database = database;
        manufacturerTable.setItems(database.getManufacturersList());
    }

    public void addManufacturerBtn() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/manufacturers/addManufacturers-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("ABC add Manufacturer");
            Scene scene = new Scene(root);
            stage.setScene(scene);

            AddManufacturersController addManufacturersController = fxmlLoader.getController();
            addManufacturersController.loadWindow(this.database);

            stage.setResizable(false);
            stage.show();

        }catch(Exception e){

        }
    }

    public void editManufacturerBtn() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/manufacturers/editManufacturers-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("ABC edit Manufacturer");
            Scene scene = new Scene(root);
            stage.setScene(scene);

            EditManufacturersController editManufacturer = fxmlLoader.getController();
            editManufacturer.loadWindow(manufacturerSelected,this);

            stage.setResizable(false);
            stage.show();

        }catch(Exception e){

        }
    }

    public Database getDatabase(){
        return this.database;
    }

    // This method is called to refresh the table once it has been modified
    public void refreshTable(){
        manufacturerTable.refresh();
    }
}
