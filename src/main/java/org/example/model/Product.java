package org.example.model;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private Timestamp creationDatetime;
    private Category category;

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
}
