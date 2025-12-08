package org.example.model;

import java.sql.Timestamp;

public class Product {
    private final int id;
    private final String name;
    private final Timestamp creationDatetime;
    private final Category category;

    public Product(int id, String name, Timestamp creationDatetime, Category category) {
        this.id = id;
        this.name = name;
        this.creationDatetime = creationDatetime;
        this.category = category;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public Timestamp getCreationDatetime() {return creationDatetime;}
    public Category getCategory() {return category;}
    public String getCategoryName() {return category.getName();}

    @Override
    public String toString() {
        return "{\n"
                + "\"id\": " + id + ", \n"
                + "\"name\": \"" + name + "\", \n"
                + "\"creationDatetime\": \"" + creationDatetime + "\", \n"
                + "\"category\": " + getCategoryName() + "\n"
                + "}";
    }
}
