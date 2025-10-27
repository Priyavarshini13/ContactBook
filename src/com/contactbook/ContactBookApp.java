package com.contactbook;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContactBookApp extends Application {

    private TableView<Contact> tableView;
    private ObservableList<Contact> contactList;

    @Override
    public void start(Stage primaryStage) {
        // Input fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        Label birthdateLabel = new Label("Birthday:");
        TextField birthdateField = new TextField();

        // Buttons
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button viewButton = new Button("View All");

        // Search field
        TextField searchField = new TextField();
        searchField.setPromptText("Type name to delete/view");

        // Layout for input fields
        HBox inputRow1 = new HBox(10, nameLabel, nameField);
        HBox inputRow2 = new HBox(10, emailLabel, emailField);
        HBox inputRow3 = new HBox(10, phoneLabel, phoneField);
        HBox inputRow4 = new HBox(10, addressLabel, addressField);
        HBox inputRow5 = new HBox(10, birthdateLabel, birthdateField);
        HBox buttonRow = new HBox(10, addButton, updateButton, deleteButton, viewButton);

        // TableView for contacts
        tableView = new TableView<>();
        contactList = FXCollections.observableArrayList();

        TableColumn<Contact, String> nameCol = new TableColumn<>("Name");
        TableColumn<Contact, String> emailCol = new TableColumn<>("Email");
        TableColumn<Contact, String> phoneCol = new TableColumn<>("Phone");
        TableColumn<Contact, String> addressCol = new TableColumn<>("Address");
        TableColumn<Contact, String> birthdayCol = new TableColumn<>("Birthday");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        birthdayCol.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());

        tableView.getColumns().addAll(nameCol, emailCol, phoneCol, addressCol, birthdayCol);
        tableView.setItems(contactList);

        // Add all input rows + buttons + search + table to VBox
        VBox root = new VBox(10, inputRow1, inputRow2, inputRow3, inputRow4, inputRow5, buttonRow, searchField, tableView);

        // Auto-suggest search
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            ObservableList<Contact> filteredList = FXCollections.observableArrayList();
            for(Contact c : contactList){
                if(c.getName().toLowerCase().contains(newText.toLowerCase())){
                    filteredList.add(c);
                }
            }
            tableView.setItems(filteredList);
        });

        // Delete selected contact
        deleteButton.setOnAction(e -> {
            Contact selected = tableView.getSelectionModel().getSelectedItem();
            if(selected != null){
                contactList.remove(selected);
                tableView.setItems(contactList); // refresh TableView
            }
        });

        // View all contacts
        viewButton.setOnAction(e -> {
            tableView.setItems(contactList);
        });

        // Add new contact
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String birthday = birthdateField.getText();

            if(!name.isEmpty()) {
                contactList.add(new Contact(name, email, phone, address, birthday));
                nameField.clear();
                emailField.clear();
                phoneField.clear();
                addressField.clear();
                birthdateField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Name cannot be empty!");
                alert.showAndWait();
            }
        });

        // Scene and Stage
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Contact Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
