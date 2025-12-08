package org.example;

import org.example.database.DBConnection;
import org.example.model.Category;
import org.example.model.Product;
import org.example.service.DataRetriever;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        DataRetriever dataRetriever = new DataRetriever(new DBConnection());

        System.out.println("======== Get all categories =========");
        for (Category category : dataRetriever.getAllCategories()) {
            System.out.println(category.getId() + "|" + category.getName());
        }

        System.out.println("======== Get product List (1, 10) =========");
        for (Product product : dataRetriever.getProductList(1, 10)) {
            System.out.println(product.getName() + " " + product.getCreationDatetime());
        }

        System.out.println("======== Get product List (1, 5) =========");
        for (Product product : dataRetriever.getProductList(1, 5)) {
            System.out.println(product.getName() + " " + product.getCreationDatetime());
        }

        System.out.println("======== Get product List (1, 3) =========");
        for (Product product : dataRetriever.getProductList(1, 3)) {
            System.out.println(product.getName() + " " + product.getCreationDatetime());
        }

        System.out.println("======== Get product List (2, 2) =========");
        for (Product product : dataRetriever.getProductList(2, 2)) {
            System.out.println(product.getName() + " " + product.getCreationDatetime());
        }

        System.out.println("======== productName = 'Dell' ========");
        for (Product p : dataRetriever.getProductsByCriteria("Dell", null, null, null)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== categoryName = 'info' ========");
        for (Product p : dataRetriever.getProductsByCriteria(null, "info", null, null)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== productName = 'iPhone', categoryName = 'mobile' ========");
        for (Product p : dataRetriever.getProductsByCriteria("iPhone", "mobile", null, null)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== creationMin = 2024-02-01, creationMax = 2024-03-01 ========");
        for (Product p : dataRetriever.getProductsByCriteria(
                null,
                null,
                Instant.parse("2024-02-01T00:00:00Z"),
                Instant.parse("2024-03-01T23:59:59Z")
        )) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== productName = 'Samsung', categoryName = 'bureau' ========");
        for (Product p : dataRetriever.getProductsByCriteria("Samsung", "bureau", null, null)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== productName = 'Sony', categoryName = 'informatique' ========");
        for (Product p : dataRetriever.getProductsByCriteria("Sony", "informatique", null, null)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== categoryName = 'audio', creationMin = 2024-01-01, creationMax = 2024-12-01 ========");
        for (Product p : dataRetriever.getProductsByCriteria(
                null,
                "audio",
                Instant.parse("2024-01-01T00:00:00Z"),
                Instant.parse("2024-12-01T23:59:59Z")
        )) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== page=1, size=10 ========");
        for (Product p : dataRetriever.getProductsByCriteria(null, null, null, null, 1, 10)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== productName = 'Dell', page=1, size=5 ========");
        for (Product p : dataRetriever.getProductsByCriteria("Dell", null, null, null, 1, 5)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }

        System.out.println("======== categoryName = 'informatique', page=1, size=10 ========");
        for (Product p : dataRetriever.getProductsByCriteria(null, "informatique", null, null, 1, 10)) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName());
        }
    }
}