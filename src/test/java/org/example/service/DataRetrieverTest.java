package org.example.service;

import org.example.database.DBConnection;
import org.example.model.Category;
import org.example.model.Product;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataRetrieverTest {

    private final DataRetriever dataRetriever = new DataRetriever(new DBConnection());


    @Test
    void getAllCategories() {
        List<Category> categories = dataRetriever.getAllCategories();

        assertNotNull(categories);
        assertFalse(categories.isEmpty());

        for (Category c : categories) {
            assertTrue(c.getId() > 0);
            assertNotNull(c.getName());
        }
    }


    @Test
    void getProductList() {
        List<Product> page1_10 = dataRetriever.getProductList(1, 10);
        List<Product> page1_5  = dataRetriever.getProductList(1, 5);
        List<Product> page1_3  = dataRetriever.getProductList(1, 3);
        List<Product> page2_2  = dataRetriever.getProductList(2, 2);

        assertNotNull(page1_10);
        assertNotNull(page1_5);
        assertNotNull(page1_3);
        assertNotNull(page2_2);

        assertTrue(page1_10.size() <= 10);
        assertTrue(page1_5.size()  <= 5);
        assertTrue(page1_3.size()  <= 3);
        assertTrue(page2_2.size()  <= 2);

        for (Product p : page1_10) {
            assertTrue(p.getId() > 0);
            assertNotNull(p.getName());
            assertNotNull(p.getCategory());
        }
    }


    @Test
    void getProductsByCriteria() {

        List<Product> byName = dataRetriever.getProductsByCriteria("Dell", null, null, null);
        List<Product> byCategory = dataRetriever.getProductsByCriteria(null, "info", null, null);
        List<Product> byBoth = dataRetriever.getProductsByCriteria("iPhone", "mobile", null, null);
        List<Product> byCategoryAndDate = dataRetriever.getProductsByCriteria(null, "audio", Instant.parse("2024-01-01T00:00:00Z"), Instant.parse("2024-03-01T00:00:00Z"));
        List<Product> byDateOnly = dataRetriever.getProductsByCriteria(null, null, Instant.parse("2024-02-01T00:00:00Z"), Instant.parse("2024-03-01T00:00:00Z"));
        List<Product> all = dataRetriever.getProductsByCriteria(null, null, null, null);


        for (Product p : byName) {
            assertTrue(p.getName().toLowerCase().contains("dell"));
        }

        for (Product p : byCategory) {
            assertTrue(p.getCategoryName().toLowerCase().contains("info"));
        }

        for (Product p : byBoth) {
            assertTrue(p.getName().toLowerCase().contains("iphone"));
            assertTrue(p.getCategoryName().toLowerCase().contains("mobile"));
        }

        for (Product p : byCategoryAndDate) {
            assertTrue(p.getCategoryName().toLowerCase().contains("audio"));
            assertTrue(p.getCreationDatetime().toInstant().isAfter(Instant.parse("2024-01-01T00:00:00Z")));
            assertTrue(p.getCreationDatetime().toInstant().isBefore(Instant.parse("2024-03-01T00:00:00Z")));
        }

        for (Product p : byDateOnly) {
            Instant d = p.getCreationDatetime().toInstant();
            assertFalse(d.isBefore(Instant.parse("2024-02-01T00:00:00Z")));
            assertFalse(d.isAfter(Instant.parse("2024-03-01T00:00:00Z")));
        }

        assertNotNull(all);
        assertFalse(all.isEmpty());
    }

    @Test
    void getProductsByCriteriaWithPageAndSize() {
        List<Product> paginated = dataRetriever.getProductsByCriteria(null, null, null, null, 1, 10);
        List<Product> paginatedByName = dataRetriever.getProductsByCriteria("Dell", null, null, null, 1, 10);
        List<Product> paginatedByCategory = dataRetriever.getProductsByCriteria(null, "informatique", null, null, 1, 10);

        assertTrue(paginated.size() <= 10);

        for (Product p : paginatedByName) {
            assertTrue(p.getName().toLowerCase().contains("dell"));
        }

        for (Product p : paginatedByCategory) {
            assertTrue(p.getCategoryName().toLowerCase().contains("informatique"));
        }
    }
}
