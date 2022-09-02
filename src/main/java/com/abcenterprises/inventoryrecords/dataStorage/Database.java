package com.abcenterprises.inventoryrecords.dataStorage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    public static final Address noAddress = new Address("street name", "city", "state", "zipcode","country");
    public static final Manufacturer noManufacturer = new Manufacturer("none", noAddress);


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

    public void undoDeleteProduct(Product product) {
        int index = this.inactiveProducts.indexOf(product);
        if(index != -1){
            this.inactiveProducts.remove(product);
            this.activeProducts.add(product);
            // will add to activeProducts
        }
    }

    // will check if the manufacturer exists within the list and delete it.
    public void removeManufacturer(Manufacturer manufacturer) {
        int index = this.manufacturers.indexOf(manufacturer);
        if(index != -1){
            this.manufacturers.remove(manufacturer);
        }
    }

    public ObservableList<Product> searchProduct(String search) {
        ObservableList<Product> result = FXCollections.observableArrayList();
        for(Product product : this.activeProducts){
            if(product.getName().contains(search)){
                result.add(product);
            }
        }
        return result;
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
