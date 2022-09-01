package com.abcenterprises.inventoryrecords.controllers;

import com.abcenterprises.inventoryrecords.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private Database database;

    @FXML
    BorderPane borderPane;
    @FXML
    Button homeBtn;
    @FXML
    Button productBtn;
    @FXML
    Button manufacturersBtn;
    @FXML
    Button transactionBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setDatabase(Database database) {
        this.database = database;
        Address address = new Address("street", "city", "state", "zip", "country");
        Manufacturer manufacturer = new Manufacturer("Company name", address);
        Product product = new Product("Product",0,0 ,manufacturer);
        this.database.addProduct(product);
        this.database.addManufacturer(manufacturer);
    }

    public void homeBtn(ActionEvent event) throws IOException {
        System.out.println("Redirect Home");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/home-view.fxml"));
        Pane view = fxmlLoader.load();


        borderPane.setCenter(view);
    }
    public void productBtn() throws IOException {

        System.out.println("Redirect Products");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/products-view.fxml"));
        Pane view = fxmlLoader.load();

        ProductsController productsController = fxmlLoader.getController();
        productsController.loadProducts(this.database);

        borderPane.setCenter(view);
    }
    public void transactionBtn() throws IOException {
        System.out.println("Redirect Transactions");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/transactions-view.fxml"));
        Pane view = fxmlLoader.load();

        borderPane.setCenter(view);
    }

    public void manufacturerBtn() throws IOException {

        System.out.println("Redirect Manufacturer");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/manufacturers-view.fxml"));
        Pane view = fxmlLoader.load();

        ManufacturersController manufacturersController = fxmlLoader.getController();
        manufacturersController.updateTable(this.database.getManufacturersList());

        borderPane.setCenter(view);
    }



}
