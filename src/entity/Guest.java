package entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;
import domain.Customer;

public class Guest extends Customer {
    Stack<Order> orderList = new Stack<>();

    @Override
    public void makeOrder(ArrayList<Vehicle> vehicleList, int longTime, int choose) {
        int iterator = 1;
        Vehicle vehicle = null;
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getIsRent()){
                iterator++;
                continue;
            }else{
                if (iterator >= choose){
                    vehicle = vehicleList.get(iterator - 1);
                    vehicleList.get(iterator - 1).setRent(true);
                    break;
                }
                iterator++;
            }
        }
        orderList.add(new Order(vehicle, longTime, this.getLastOrderNo()));
    }

    public Guest(String firtsName, String lastName) {
        super();
        this.setFirstName(firtsName);
        this.setLastName(lastName);
    }

    public int getLastOrderNo() {
        if (orderList.isEmpty()){
            return 0;
        }
        return orderList.peek().getOrderNo();
    }
}
