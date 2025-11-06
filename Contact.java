package com.contactbook;

public class Contact {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String birthday;

    public Contact() {}

    public Contact(String name, String email, String phone, String address, String birthday) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Contact {" +
                "Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Phone='" + phone + '\'' +
                ", Address='" + address + '\'' +
                ", Birthday='" + birthday + '\'' +
                '}';
    }
}
