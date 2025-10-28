package com.contactbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    public static boolean insertContact(Contact contact) {
        String query = "INSERT INTO contacts (name, email, phone, address, birthday) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhone());
            ps.setString(4, contact.getAddress());
            ps.setString(5, contact.getBirthday());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>();
        String query = "SELECT * FROM contacts";
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Contact(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("birthday")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateContact(Contact contact) {
        String query = "UPDATE contacts SET email=?, phone=?, address=?, birthday=? WHERE name=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, contact.getEmail());
            ps.setString(2, contact.getPhone());
            ps.setString(3, contact.getAddress());
            ps.setString(4, contact.getBirthday());
            ps.setString(5, contact.getName());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteContactByName(String name) {
        String query = "DELETE FROM contacts WHERE name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
