module com.abcenterprises.inventoryrecords {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.abcenterprises.inventoryrecords to javafx.fxml;
    exports com.abcenterprises.inventoryrecords;
    exports com.abcenterprises.inventoryrecords.controllers;
    opens com.abcenterprises.inventoryrecords.controllers to javafx.fxml;
}