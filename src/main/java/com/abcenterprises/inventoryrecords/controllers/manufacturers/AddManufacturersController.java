package com.abcenterprises.inventoryrecords.controllers.manufacturers;

import com.abcenterprises.inventoryrecords.dataStorage.Address;
import com.abcenterprises.inventoryrecords.dataStorage.Database;
import com.abcenterprises.inventoryrecords.dataStorage.Manufacturer;
import com.abcenterprises.inventoryrecords.dataStorage.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddManufacturersController{
    Database database;
    @FXML
    private TextField newCompanyName;
    @FXML
    private TextField addressStreet;
    @FXML
    private TextField addressCity;
    @FXML
    private TextField addressState;
    @FXML
    private TextField addressZip;
    @FXML
    private TextField addressCountry;

    @FXML
    private Button doneBtn;

    public void loadWindow(Database database){
        this.database = database;
    }

    @FXML
    public void addBtnClicked(){

        if(!userInputIsBlank()){
            // add products
            database.addManufacturer(
                    new Manufacturer(newCompanyName.getText(),
                            new Address(
                                    addressStreet.getText(),
                                    addressCity.getText(),
                                    addressState.getText(),
                                    addressZip.getText(),
                                    addressCountry.getText()
                            )
                    ));

            // clear text fields
            newCompanyName.setText("");
            addressStreet.setText("");
            addressCity.setText("");
            addressState.setText("");
            addressZip.setText("");
            addressCountry.setText("");

        }
    }
    @FXML
    public void doneBtnClicked(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        thisStage.hide();
    }

    boolean userInputIsBlank(){
        boolean result = false;

        if(newCompanyName.getText().isEmpty()
            || addressStreet.getText().isEmpty()
            || addressCity.getText().isEmpty()
            || addressState.getText().isEmpty()
            || addressZip.getText().isEmpty()
            || addressCountry.getText().isEmpty()){
            result = true;
        }

        return result;
    }
}
