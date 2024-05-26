package entity;
import java.util.Date;
import domain.*;
import entity.*;

public class OrderItem {
    Vehicle vehicle;
    int rentalTime;
    int lastOrderNo;
    Date date;
    Date end;

    public OrderItem(Vehicle vehicle, int qty, int year, int month, int date){
        this.vehicle = vehicle;
        this.rentalTime = qty;
        this.date = new Date(year, month, date);
        end = new Date(year, month, date+rentalTime);
    }
    public void increaseDate(int qty){
        rentalTime = qty;
        end = new Date(date.getYear(), date.getMonth(), date.getDate()+qty);
    }

    public void decreaseDate(int qty){
        rentalTime = rentalTime-qty;
        end = new Date(date.getYear(), date.getMonth(), date.getDate()-qty);
    }

    public int getRentalTime() {
        return rentalTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getDate() {
        return date;
    }

    public Date getEnd() {
        return end;
    }
}
