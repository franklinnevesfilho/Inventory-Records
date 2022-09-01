package com.abcenterprises.inventoryrecords.controllers;

import com.abcenterprises.inventoryrecords.Address;
import com.abcenterprises.inventoryrecords.Database;
import com.abcenterprises.inventoryrecords.Manufacturer;
import com.abcenterprises.inventoryrecords.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Objects;


public class AddProductsController{

    Database database;

    private final Address noAddress = new Address("street name", "city", "state", "zipcode","country");
    private final Manufacturer noManufacturer = new Manufacturer("none", noAddress);

    @FXML
    private TextField newProductName;
    @FXML
    private TextField newProductUnits;
    @FXML
    private TextField newProductUnitPrice;

    @FXML
    private Button addBtn;
    @FXML
    private ChoiceBox<Manufacturer> manufacturerChoiceBox;

    public void loadWindow(Database database){
       this.database = database;

       manufacturerChoiceBox.setValue(noManufacturer);
       manufacturerChoiceBox.getItems().addAll(database.getManufacturersList());

    }

    public void addBtnClicked(){

      if(userInputIsBlank()){

      }else{
          // add products
          database.addProduct(
                  new Product(newProductName.getText(),
                          Double.parseDouble(newProductUnitPrice.getText()),
                          Long.parseLong(newProductUnits.getText()),
                          manufacturerChoiceBox.getValue()
                  ));

          // clear text fields
          newProductName.setText("");
          newProductUnits.setText("");
          newProductUnitPrice.setText("");
      }
    }
    boolean userInputIsBlank(){
        boolean result = false;

        if(newProductName.getText().isBlank()
          || newProductName.getText().isBlank()
          || newProductUnitPrice.getText().isBlank()
          || newProductUnits.getText().isBlank()){

            result = true;
        }

        return result;
    }
}
