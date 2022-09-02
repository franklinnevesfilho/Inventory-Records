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

public class EditProductsController {
    ProductsController productsController;
    Database database;
    Product editProduct;
    @FXML
    TextField productName;
    @FXML
    TextField productUnits;
    @FXML
    TextField productUnitPrice;
    @FXML
    Button doneBtn;
    @FXML
    ChoiceBox<Manufacturer> manufacturerChoiceBox;

    public void loadWindow(Product product,ProductsController productsController) {
        // sets the database and the current product being edited
        this.editProduct = product;
        this.productsController = productsController;
        this.database = productsController.getDatabase();

        // These lines set the values for the textFields at the start
        manufacturerChoiceBox.setValue(product.getManufacturer());
        manufacturerChoiceBox.getItems().addAll(database.getManufacturersList());

        productName.setText(product.getName());
        productUnits.setText(String.valueOf(product.getUnits()));
        productUnitPrice.setText(String.valueOf(product.getUnitPrice()));
    }

    // Checks if any attribute has been changed, if yes updates those values
    // then closes the window.
    @FXML
    public void doneBtnClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        if(editProduct.getManufacturer() != manufacturerChoiceBox.getValue() // is manufacturer the same
                || !editProduct.getName().equals(productName.getText())     // is name the same
                || editProduct.getUnits()!= Long.parseLong(productUnits.getText()) // is unit the same
                || editProduct.getUnitPrice()!= Double.parseDouble(productUnitPrice.getText())){ // is unit price the same

            // if either of those are not the same, it will update the values.
            editProduct.setName(productName.getText());
            editProduct.setUnits(Long.parseLong(productUnits.getText()));
            editProduct.setUnitPrice(Double.parseDouble(productUnitPrice.getText()));
            editProduct.setManufacturer(manufacturerChoiceBox.getValue());
        }

        //Refresh teh table
        productsController.refreshTable();

        // close the window
        thisStage.hide();
    }


}
