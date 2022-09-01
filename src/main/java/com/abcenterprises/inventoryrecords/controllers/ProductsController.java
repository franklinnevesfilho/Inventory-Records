package com.abcenterprises.inventoryrecords.controllers;

import com.abcenterprises.inventoryrecords.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ProductsController implements Initializable {
    Database database;
    @FXML
    TableView<Product> productsTable;
    @FXML
    Button deleteProduct;

    @FXML
    Button addProductBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Product Name
        TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setMinWidth(70);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Purchase Date
        TableColumn<Product, String> purchaseDateColumn = new TableColumn<>("Purchase Date");
        purchaseDateColumn.setMinWidth(30);
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        // Quantity
        TableColumn<Product, Long> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(10);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("units"));

        // Price
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price $");
        priceColumn.setMinWidth(10);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        // Manufacturer
        TableColumn<Product, Manufacturer> manufacturerColumn = new TableColumn<>("Manufacturer");
        manufacturerColumn.setMinWidth(20);
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        // State
        TableColumn<Product, String> stateColumn = new TableColumn<>("State");
        stateColumn.setMinWidth(10);
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        productsTable.getColumns().clear();
        productsTable.getColumns().addAll(
                nameColumn,
                purchaseDateColumn,
                quantityColumn,
                priceColumn,
                manufacturerColumn,
                stateColumn
        );

        // disable delete button at start
        deleteProduct.setDisable(true);

        // When item is selected
        productsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, product, t1) -> {
            // enable delete button
            deleteProduct.setDisable(false);
            // action listener will delete the item selected
            deleteProduct.setOnAction(actionEvent -> deleteProduct(productsTable.getSelectionModel().getSelectedItem()));
        });

    }

    public void loadProducts(Database database){
        this.database = database;
        productsTable.setItems(database.getProductsList());
    }

    public void showActiveProducts(){
        productsTable.setItems(database.getProductsList());
    }
    public void showInactiveProducts(){
        productsTable.setItems(database.getDeletedProducts());
    }

    public void deleteProduct(Product product){
        this.database.removeProduct(product);
    }

    public void addProductBtn() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/addProducts-view.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("ABC add Product");
            Scene scene = new Scene(root);
            stage.setScene(scene);

            AddProductsController addProducts = fxmlLoader.getController();
            addProducts.loadWindow(this.database);

            stage.setResizable(false);
            stage.show();

        }catch(Exception e){

        }
    }

}
