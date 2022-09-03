package com.abcenterprises.inventoryrecords.controllers;

import com.abcenterprises.inventoryrecords.*;
import com.abcenterprises.inventoryrecords.controllers.manufacturers.ManufacturersController;
import com.abcenterprises.inventoryrecords.controllers.products.ProductsController;
import com.abcenterprises.inventoryrecords.dataStorage.Database;
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
    BorderPane mainView;
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
    }

    public void productBtn() throws IOException {

        System.out.println("Redirect Products");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/products/products-view.fxml"));
        Pane view = fxmlLoader.load();

        ProductsController productsController = fxmlLoader.getController();
        productsController.loadProducts(this.database);

        mainView.setCenter(view);
    }
    public void transactionBtn() throws IOException {
        System.out.println("Redirect Transactions");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/transactions-view.fxml"));
        Pane view = fxmlLoader.load();

        mainView.setCenter(view);
    }

    public void manufacturerBtn() throws IOException {

        System.out.println("Redirect Manufacturer");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/manufacturers/manufacturers-view.fxml"));
        Pane view = fxmlLoader.load();

        ManufacturersController manufacturersController = fxmlLoader.getController();
        manufacturersController.loadManufacturers(this.database);

        mainView.setCenter(view);
    }



}
