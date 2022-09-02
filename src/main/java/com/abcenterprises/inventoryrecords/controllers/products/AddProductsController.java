package com.abcenterprises.inventoryrecords.controllers.products;

import com.abcenterprises.inventoryrecords.dataStorage.Database;
import com.abcenterprises.inventoryrecords.dataStorage.Manufacturer;
import com.abcenterprises.inventoryrecords.dataStorage.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddProductsController{

    Database database;

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

       manufacturerChoiceBox.setValue(Database.noManufacturer);
       manufacturerChoiceBox.getItems().addAll(database.getManufacturersList());

    }
    @FXML
    public void addBtnClicked(ActionEvent event){
        if(!userInputIsBlank()){
            // add products
            database.addProduct(
                    new Product(newProductName.getText(),
                            Double.parseDouble(newProductUnitPrice.getText()),
                            Long.parseLong(newProductUnits.getText()),
                            manufacturerChoiceBox.getValue()
                    ));

            this.clearFields();
        }
    }
    @FXML
    public void doneBtnClicked(ActionEvent event){

        // these two lines retrieves the current stage
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        // Closes window
        thisStage.hide();
    }

    private void clearFields(){
        newProductName.setText("");
        newProductUnits.setText("");
        newProductUnitPrice.setText("");
    }
    boolean userInputIsBlank(){
        boolean result = false;

        if(newProductName.getText().isBlank()
          || newProductUnitPrice.getText().isBlank()
          || newProductUnits.getText().isBlank()){

            result = true;
        }

        return result;
    }
}
