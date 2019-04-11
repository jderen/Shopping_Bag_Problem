package com.app;



public class Product {


    public String id;
    public String name;
    public String weight;
    public String unit;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getUnit() {
        return unit;
    }

    public Product(String id, String name, String weight, String unit) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
