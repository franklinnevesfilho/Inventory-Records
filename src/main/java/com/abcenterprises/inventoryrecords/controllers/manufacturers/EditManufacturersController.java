package com.abcenterprises.inventoryrecords.controllers.manufacturers;

import com.abcenterprises.inventoryrecords.dataStorage.Database;
import com.abcenterprises.inventoryrecords.dataStorage.Manufacturer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditManufacturersController {
    ManufacturersController manufacturersController;

    Database database;
    Manufacturer editManufacturer;

    @FXML
    TextField manufacturerName;
    @FXML
    TextField addressStreet;
    @FXML
    TextField addressCity;
    @FXML
    TextField addressState;
    @FXML
    TextField addressZip;
    @FXML
    TextField addressCountry;
    @FXML
    Button doneBtn;

    public void loadWindow(Manufacturer manufacturer,ManufacturersController manufacturerController){
        this.manufacturersController = manufacturerController;
        this.editManufacturer = manufacturer;
        this.database = manufacturerController.getDatabase();

        manufacturerName.setText(manufacturer.getCompany());
        addressStreet.setText(manufacturer.getAddress().getStreet());
        addressCity.setText(manufacturer.getAddress().getCity());
        addressState.setText(manufacturer.getAddress().getState());
        addressZip.setText(manufacturer.getAddress().getZip());
        addressCountry.setText(manufacturer.getAddress().getCountry());

    }

    @FXML
    public void doneBtnClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        if(!editManufacturer.getCompany().equalsIgnoreCase(this.manufacturerName.getText()) // is company name the same
                || !editManufacturer.getAddress().getStreet().equalsIgnoreCase(this.addressStreet.getText())// is street the same
                || !editManufacturer.getAddress().getCity().equalsIgnoreCase(this.addressCity.getText()) // is city the same
                || !editManufacturer.getAddress().getState().equalsIgnoreCase(this.addressState.getText()) // is state the same
                || !editManufacturer.getAddress().getZip().equalsIgnoreCase(this.addressZip.getText()) // is zip the same
                ||!editManufacturer.getAddress().getCountry().equalsIgnoreCase(this.addressCountry.getText())){ // is country the same

            // if either of those are not the same, it will update the values.
            editManufacturer.setCompany(this.manufacturerName.getText());
            editManufacturer.getAddress().setStreet(this.addressStreet.getText());
            editManufacturer.getAddress().setCity(this.addressCity.getText());
            editManufacturer.getAddress().setState(this.addressState.getText());
            editManufacturer.getAddress().setZip(this.addressZip.getText());
            editManufacturer.getAddress().setCountry(this.addressCountry.getText());
        }

        //Refresh teh table
        manufacturersController.refreshTable();

        // close the window
        thisStage.hide();
    }

}
