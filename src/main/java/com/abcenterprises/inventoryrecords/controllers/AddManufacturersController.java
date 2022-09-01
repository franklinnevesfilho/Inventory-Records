package com.abcenterprises.inventoryrecords.controllers;

import com.abcenterprises.inventoryrecords.Address;
import com.abcenterprises.inventoryrecords.Database;
import com.abcenterprises.inventoryrecords.Manufacturer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


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

    public void addBtnClicked(){
        if(userInputIsBlank()){

        }else {
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
