package org.example;

import org.example.database.DBConnection;
import org.example.service.DataRetriever;

public class Main {
    public static void main(String[] args) {
        DataRetriever dataRetriever = new DataRetriever(new DBConnection());

        System.out.println(dataRetriever.getAllCategories());
        System.out.println(dataRetriever.getProductList(1, 10));
    }
}