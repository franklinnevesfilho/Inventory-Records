package com.abcenterprises.inventoryrecords.controllers.products;

import com.abcenterprises.inventoryrecords.*;
import com.abcenterprises.inventoryrecords.dataStorage.Database;
import com.abcenterprises.inventoryrecords.dataStorage.Manufacturer;
import com.abcenterprises.inventoryrecords.dataStorage.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;


public class ProductsController implements Initializable {
    //price formatter
    public static final DecimalFormat decimalFormatter = new DecimalFormat( "$0.00" );

    // current database and item selected by user
    Database database;
    Product productSelected;
    // ======================================================
    // These are all the objects within the FXML file
    // ======================================================
    @FXML
    TableView<Product> productsTable;
    @FXML
    Button deleteProduct;
    @FXML
    Button editProductBtn;

    @FXML
    Button addProductBtn;

    @FXML
    TextField searchBar;
    // ===============================================================================================
    // The Initialize method is run once the class is initialized. so when the screen pops up.
    // It establishes the columns and rows for the table, as well as the method in which the user
    //                              has selected an item
    // ===============================================================================================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //===============================================================================================
        // Create the table and columns for the table
        //===============================================================================================

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
        TableColumn<Product, String> priceColumn = new TableColumn<>("Price $");
        priceColumn.setMinWidth(10);
        priceColumn.setCellValueFactory(cellData ->{
            String formattedCost = decimalFormatter.format(cellData.getValue().getUnitPrice());
            return new SimpleStringProperty(formattedCost);
        });

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
        //============================================================================================
        //This section sets the Edit Button and Delete button to disabled and ensures that they are
        //                  only clickable after an item has been selected
        //============================================================================================
        // disable delete button at start
        deleteProduct.setDisable(true);
        editProductBtn.setDisable(true);

        // When item is selected
        productsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, product, t1) -> {
            // sets current product selected
            productSelected = productsTable.getSelectionModel().getSelectedItem();

            if(productsTable.getItems().equals(this.database.getProductsList())){

                // enable delete and edit buttons only if active products are visible
                deleteProduct.setDisable(false);
                editProductBtn.setDisable(false);

                // action listener will delete the item selected
                deleteProduct.setOnAction(actionEvent -> deleteProduct(productSelected));

            }else if(productsTable.getItems().equals(this.database.getDeletedProducts())){
                // deleteBtn becomes restore button
                deleteProduct.setDisable(false);
                deleteProduct.setOnAction(actionEvent -> undoDelete(productSelected));
            }
        });

    }

    // This method is called once the user wants to look at the screen.
    // It ensures that the same database is being used throughout.
    public void loadProducts(Database database){
        this.database = database;
        productsTable.setItems(database.getProductsList());
    }

    // Shows the activeProducts List
    public void showActiveProducts(){
        deleteProduct.setText("Delete Product");
        deleteProduct.setDisable(true);
        editProductBtn.setDisable(true);
        productsTable.setItems(database.getProductsList());
    }
    // Shows the InactiveProducts List
    public void showInactiveProducts(){
        deleteProduct.setText("Restore Product");
        deleteProduct.setDisable(true);
        editProductBtn.setDisable(true);
        productsTable.setItems(database.getDeletedProducts());
    }

    @FXML
    public void searchBar(ActionEvent event){
        if(!searchBar.getText().isBlank()){
            productsTable.setItems(database.searchProduct(searchBar.getText()));
        }else{
            this.showActiveProducts();
        }
    }

    // Deletes the product
    public void deleteProduct(Product product){
        this.database.removeProduct(product);
    }

    //restores deleted product
    public void undoDelete(Product product){
        this.database.undoDeleteProduct(product);
    }


    // Opens new window to type in information about new product (see AddProductsController)
    // adds the product to the database
    public void addProductBtn(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/products/addProducts-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("ABC add Product");
            Scene scene = new Scene(root);

            scene.getStylesheets().add(Application.styleSheet);

            stage.setScene(scene);

            AddProductsController addProducts = fxmlLoader.getController();
            addProducts.loadWindow(this.database);

            stage.setResizable(false);
            stage.show();

        }catch(Exception ignored){

        }
    }

    // Opens new window to change information about the selected product (see EditProductsController)
    //  edits the product in the database
    public void editProductBtn() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/products/editProducts-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("ABC edit Product");
            Scene scene = new Scene(root);

            scene.getStylesheets().add(Application.styleSheet);

            stage.setScene(scene);

            EditProductsController editProducts = fxmlLoader.getController();
            editProducts.loadWindow(productSelected,this);

            stage.setResizable(false);
            stage.show();

        }catch(Exception e){

        }
    }

    // This method is called to refresh the table once it has been modified
    public void refreshTable(){
        productsTable.refresh();
    }
    public Database getDatabase(){
        return this.database;
    }

}
