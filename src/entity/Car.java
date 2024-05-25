package entity;

import domain.Vehicle;
import utils.CarType;

public class Car extends Vehicle {
    private CarType type;

    public Car(String id, String name, String platKendaraan, int price, CarType type) {
        super(id, name, platKendaraan, price);
        this.type = type;
    }
}
