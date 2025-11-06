package com.contactbook;

import java.util.List;
import java.util.Scanner;

public class ContactBookApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactDAO dao = new ContactDAO();

        while (true) {
            System.out.println("\n===== CONTACT BOOK MANAGEMENT =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter Birthday (YYYY-MM-DD): ");
                    String birthday = sc.nextLine();

                    Contact contact = new Contact(name, email, phone, address, birthday);
                    if (ContactDAO.insertContact(contact))
                        System.out.println("✅ Contact added successfully!");
                    else
                        System.out.println("❌ Failed to add contact.");
                    break;

                case 2:
                    List<Contact> list = ContactDAO.getAllContacts();
                    if (list.isEmpty()) {
                        System.out.println("📭 No contacts found.");
                    } else {
                        System.out.println("\n📋 All Contacts:");
                        for (Contact c : list) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter the Name of Contact to Update: ");
                    String updateName = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();
                    System.out.print("Enter New Phone: ");
                    String newPhone = sc.nextLine();
                    System.out.print("Enter New Address: ");
                    String newAddress = sc.nextLine();
                    System.out.print("Enter New Birthday (YYYY-MM-DD): ");
                    String newBirthday = sc.nextLine();

                    Contact updatedContact = new Contact(updateName, newEmail, newPhone, newAddress, newBirthday);
                    if (ContactDAO.updateContact(updatedContact))
                        System.out.println("✅ Contact updated successfully!");
                    else
                        System.out.println("⚠️ Contact not found or update failed.");
                    break;

                case 4:
                    System.out.print("Enter the Name of Contact to Delete: ");
                    String deleteName = sc.nextLine();
                    if (ContactDAO.deleteContactByName(deleteName))
                        System.out.println("🗑️ Contact deleted successfully!");
                    else
                        System.out.println("⚠️ No contact found with that name.");
                    break;

                case 5:
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    DBUtil.closeConnection();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select 1–5.");
            }
        }
    }
}
