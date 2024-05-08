package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import domain.Customer;

public class Member extends Customer {

    LocalDate dateCreated;

    @Override
    public void makeOrder(ArrayList<Vehicle> vehicleList, int longTime, int choose) {

    }
}
