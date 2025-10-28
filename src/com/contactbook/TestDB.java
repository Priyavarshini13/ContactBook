package com.contactbook;

public class TestDB {
    public static void main(String[] args) {
        DBUtil.getConnection();
        DBUtil.closeConnection();
    }
}
