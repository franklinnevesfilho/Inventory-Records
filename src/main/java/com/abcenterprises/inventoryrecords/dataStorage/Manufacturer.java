package com.abcenterprises.inventoryrecords.dataStorage;

public class Manufacturer {

    private String company;
    private Address address;

    public Manufacturer(String company, Address address) {
        this.company = company;
        this.address = address;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getState(){
        return this.address.getState();
    }

    public String toString() {
        return this.company;
    }
}
