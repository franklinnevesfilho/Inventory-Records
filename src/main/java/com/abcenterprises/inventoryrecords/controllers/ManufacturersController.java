package com.abcenterprises.inventoryrecords.controllers;

import com.abcenterprises.inventoryrecords.Manufacturer;
import com.abcenterprises.inventoryrecords.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManufacturersController {
    @FXML
    TableView<Manufacturer> manufacturerTable;

    public void updateTable(ObservableList<Manufacturer> manufacturers){
        // Product Name
        TableColumn<Manufacturer, String> companyColumn = new TableColumn<>("Company Name");
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

        // Purchase Date
        TableColumn<Manufacturer, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        manufacturerTable.getColumns().clear();
        manufacturerTable.getColumns().addAll(
                companyColumn,
                addressColumn
        );
        manufacturerTable.setItems(manufacturers);
    }
}
