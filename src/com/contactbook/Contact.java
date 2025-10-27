package com.contactbook;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty address;
    private SimpleStringProperty birthday;

    public Contact(String name, String email, String phone, String address, String birthday) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.birthday = new SimpleStringProperty(birthday);
    }

    // Getters
    public String getName() { return name.get(); }
    public String getEmail() { return email.get(); }
    public String getPhone() { return phone.get(); }
    public String getAddress() { return address.get(); }
    public String getBirthday() { return birthday.get(); }

    // Setters
    public void setName(String name) { this.name.set(name); }
    public void setEmail(String email) { this.email.set(email); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public void setAddress(String address) { this.address.set(address); }
    public void setBirthday(String birthday) { this.birthday.set(birthday); }

    // Properties for TableView
    public SimpleStringProperty nameProperty() { return name; }
    public SimpleStringProperty emailProperty() { return email; }
    public SimpleStringProperty phoneProperty() { return phone; }
    public SimpleStringProperty addressProperty() { return address; }
    public SimpleStringProperty birthdayProperty() { return birthday; }
}
