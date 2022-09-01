package com.abcenterprises.inventoryrecords;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    // I use a date formatter to be able to format the current date
    // this formatter will always be the same thus can be static and final.
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    // For storage, I will be using ObservableList as they are useful for the GUI.
    private ObservableList<Manufacturer> manufacturers = FXCollections.observableArrayList();
    private ObservableList<Product> activeProducts = FXCollections.observableArrayList();
    private ObservableList<Product> inactiveProducts = FXCollections.observableArrayList();

    // The constructor is blank as all the values of this class will be created after the fact.
    public Database() {}


    public void addManufacturer(Manufacturer manufacturer) {
        this.manufacturers.add(manufacturer);
    }

    public void addProduct(Product product) {
        // get the current date and establishes the purchase date for product
        Date date = new Date();
        product.setPurchaseDate(dateFormatter.format(date));
        this.activeProducts.add(product);
    }

    // will check if the product exists within the list and delete it.
    public void removeProduct(Product product) {
        int index = this.activeProducts.indexOf(product);
        if(index != -1){
            this.activeProducts.remove(product);
            this.inactiveProducts.add(product);
            // will add to inactiveProducts
        }
    }

    // will check if the manufacturer exists within the list and delete it.
    public void removeProduct(Manufacturer manufacturer) {
        int index = this.manufacturers.indexOf(manufacturer);
        if(index != -1){
            this.manufacturers.remove(manufacturer);
        }
    }

    public ObservableList<Manufacturer> getManufacturersList() {
        return manufacturers;
    }
    public ObservableList<Product> getProductsList() {
        return activeProducts;
    }
    public ObservableList<Product> getDeletedProducts() {
        return inactiveProducts;
    }
}
