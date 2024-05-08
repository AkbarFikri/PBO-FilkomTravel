package domain;

import entity.Vehicle;

import java.util.ArrayList;

public abstract class Customer {
    private String firstName;
    private String lastName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public abstract void makeOrder(ArrayList<Vehicle> vehicleList, int longTime, int choose);

    public void confirmPay(int nomorPesanan){
        return;
    }
}
