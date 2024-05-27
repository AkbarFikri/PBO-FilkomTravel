package domain;

import entity.*;

public abstract class Vehicle {
    protected String id;
    protected String name;
    protected String platNumber;
    protected int price;

    protected Vehicle(String id, String name, String platNumber, int price) {
        this.id = id;
        this.name = name;
        this.platNumber = platNumber;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public int getPrice() {
        return price;
    }
}
