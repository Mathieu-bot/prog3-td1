package org.example.service;

import org.example.database.DBConnection;
import org.example.model.Category;
import org.example.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    private final DBConnection dbConnection;

    public DataRetriever(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private void mapResultSetToProducts(List<Product> products, PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Category category = new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
            );
            Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getTimestamp("creation_datetime"),
                    category
            );
            products.add(product);
        }
    }


    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM product_category";

        try {
            PreparedStatement stmt = dbConnection.getDBConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                categories.add(new Category(
                    rs.getInt("id"),
                    rs.getString("name"))
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public List<Product> getProductList(int page, int size) {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * size;
        String sql =
                "SELECT p.id AS product_id, p.name AS product_name, p.creation_datetime, pc.id AS category_id, pc.name AS category_name " +
                "FROM product p " +
                "LEFT JOIN product_category pc ON p.id = pc.product_id " +
                "ORDER BY p.id " +
                "LIMIT ? OFFSET ? ";

        try (
            PreparedStatement stmt = dbConnection.getDBConnection().prepareStatement(sql)) {

            stmt.setInt(1, size);
            stmt.setInt(2, offset);
            mapResultSetToProducts(products, stmt);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax) {
        return getProductsByCriteria(productName, categoryName, creationMin, creationMax, 0, 0);
    }

    public List<Product> getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax, int page, int size) {
        List<Product> products = new ArrayList<>();
        int offset = (page > 0 && size > 0) ? (page - 1) * size : 0;

        StringBuilder sql = new StringBuilder(
                "SELECT p.id AS product_id, p.name AS product_name, p.creation_datetime, " +
                        "pc.id AS category_id, pc.name AS category_name " +
                        "FROM product p " +
                        "LEFT JOIN product_category pc ON p.id = pc.product_id " +
                        "WHERE 1=1 "
        );

        if (productName != null) sql.append("AND p.name ILIKE ? ");
        if (categoryName != null) sql.append("AND pc.name ILIKE ? ");
        if (creationMin != null) sql.append("AND p.creation_datetime >= ? ");
        if (creationMax != null) sql.append("AND p.creation_datetime <= ? ");

        sql.append("ORDER BY p.id ");

        if (page > 0 && size > 0) sql.append("LIMIT ? OFFSET ?");

        try (PreparedStatement stmt = dbConnection.getDBConnection().prepareStatement(sql.toString())) {
            int index = 1;
            if (productName != null) stmt.setString(index++, "%" + productName + "%");
            if (categoryName != null) stmt.setString(index++, "%" + categoryName + "%");
            if (creationMin != null) stmt.setTimestamp(index++, Timestamp.from(creationMin));
            if (creationMax != null) stmt.setTimestamp(index++, Timestamp.from(creationMax));
            if (page > 0 && size > 0) {
                stmt.setInt(index++, size);
                stmt.setInt(index++, offset);
            }

            mapResultSetToProducts(products, stmt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


}
