package com.abcenterprises.inventoryrecords;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Product {
    private Manufacturer manufacturer;
    private String name;
    private long units;
    private double unitPrice;
    private String purchaseDate;


    public Product(String name, double unitPrice, long units, Manufacturer manufacturer){
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUnits() {
        return this.units;
    }

    public void setUnits(long units) {
        this.units = units;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public String getState() {
        return this.manufacturer.getState();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public String getPurchaseDate() {
        return this.purchaseDate;
    }

    public String toString() {
        return this.name;
    }

}
