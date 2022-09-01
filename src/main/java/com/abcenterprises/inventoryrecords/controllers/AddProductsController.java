package com.abcenterprises.inventoryrecords.controllers;

import com.abcenterprises.inventoryrecords.Address;
import com.abcenterprises.inventoryrecords.Database;
import com.abcenterprises.inventoryrecords.Manufacturer;
import com.abcenterprises.inventoryrecords.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductsController{

    Database database;

    private Address noAddress = new Address("street name", "city", "state", "zipcode","country");
    private Manufacturer noManufacturer = new Manufacturer("none", noAddress);

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

      // add products
      database.addProduct(
              new Product(newProductName.getText(),
                      Double.valueOf(newProductUnitPrice.getText()),
                      Long.valueOf(newProductUnits.getText()),
                      manufacturerChoiceBox.getValue()
                      ));

      // clear text fields
      newProductName.setText("");
      newProductUnits.setText("");
      newProductUnitPrice.setText("");
    }
}
