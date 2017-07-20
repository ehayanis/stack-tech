package com.xeigna.ms.demo.model;

import org.springframework.data.annotation.Id;

public class Product {

    @Id private String id;

    private String name;
    private String description;
    private double price;

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

}