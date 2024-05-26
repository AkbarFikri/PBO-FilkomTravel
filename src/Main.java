/*
* NIM DAN NAMA ANGGOTA KELOMPOK :
* 1. 235150201111058 Akbar Fikri Abdillah
* 2. 235150201111 (NAMA KALIAN BROK) <-- NIMNYA LOM LENGKAP YAH
* 3. 235150201111 (NAMA KALIAN BROK) <-- NIMNYA LOM LENGKAP YAH
*/

//import domain.Customer;
//import domain.Promotion;
//import domain.Vehicle;
//gatau knp gabisa import ntar coba di cek di IDE kalian
import domain.*;
import entity.Guest;
import entity.Member;
import entity.Order;
import entity.OrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
* NOTE DARI AKBAR
* JANGAN LUPA TERAPIN PER PBO AN YAK GES JADI BANYAK BANYAK GUNAIN METHOD METHOD DI DALEM CLASS,
* BTW ITU KALIAN TETEP BOLEH BIKIN METHOD METHOD YANG DI PERLUIN YAKK
* */
public class Main {
    static Scanner keyboard = new Scanner(System.in);
    static int nomorPesanan = 1;
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Promotion> promotions = new ArrayList<>();
    static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<String> inputs = new ArrayList<>();

        while (keyboard.hasNextLine()) {
            String temp = keyboard.nextLine();
            if (temp.isEmpty()) {
                break;
            }
            inputs.add(temp);
        }

        for (int i = 0; i < inputs.size(); i++) {
            String[] commands = inputs.get(i).split(" ");

            switch (commands[0]) {
                case "CREATE":
                    if (commands[1].equalsIgnoreCase("MEMBER")) {
                        String[] datas = commands[2].split("\\|");
                        createMember(datas);
                    } else if (commands[1].equalsIgnoreCase("GUEST")) {
                        String[] datas = commands[2].split("\\|");
                        createGuest(datas);
                    } else if (commands[1].equalsIgnoreCase("MENU")) {

                    } else if (commands[1].equalsIgnoreCase("PROMO")) {

                    }
                    // TODO Akbar yang ngerjain
                    break;
                case "ADD_TO_CART":
                    try {
                        Customer dumpUser = getCustomerById(commands[1]);
                        String[] dateTemp = commands[4].split("/");
                        Vehicle vehicle = getVehicleById(commands[2]);

                        boolean checkOrderItemExist = dumpUser.isOrderItemExistInLastOrder(commands[2]);

                        if (dumpUser.orders.isEmpty() || dumpUser.getLastOrder().isCheckOut()) {
                            dumpUser.makeOrder(vehicle,
                                    Integer.parseInt(commands[3]),
                                    Integer.parseInt(dateTemp[0]),
                                    Integer.parseInt(dateTemp[1]),
                                    Integer.parseInt(dateTemp[2]));
                        } else if (checkOrderItemExist) {
                            dumpUser.getLastOrder()
                                    .getOrderItemById(commands[2])
                                    .increaseDate(Integer.parseInt(commands[3]));
                            if (Integer.parseInt(commands[3]) == 1) {
                                System.out.println("ADD_TO_CART SUCCESS: " + commands[3] + " day "
                                        + vehicle.getName() + " "
                                        + vehicle.getPlatNumber() + " (UPDATED)");
                            } else {
                                System.out.println("ADD_TO_CART SUCCESS: " + commands[3] + " days "
                                        + vehicle.getName() + " "
                                        + vehicle.getPlatNumber() + " (UPDATED)");
                            }
                        } else {
                            dumpUser.addToCart(vehicle,
                                    Integer.parseInt(commands[3]),
                                    Integer.parseInt(dateTemp[0]),
                                    Integer.parseInt(dateTemp[1]),
                                    Integer.parseInt(dateTemp[2]));
                        }
                    } catch (NullPointerException e) {
                        System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                    }

                    break;
                case "REMOVE_FROM_CART":
                    try {
                        Customer dumpUser2 = getCustomerById(commands[1]);
                        OrderItem dumpOrderItem = dumpUser2.getLastOrder().getOrderItemById(commands[2]);
                        dumpOrderItem.decreaseDate(Integer.parseInt(commands[3]));
                        if (dumpOrderItem.getRentalTime() <= 0) {
                            // Sout message hapus
                            dumpUser2.getLastOrder().deleteItemById(dumpOrderItem.getVehicle().getId());
                        } else {
                            // sout message decrease
                        }
                    } catch (NullPointerException e) {
                        System.out.println("KENAK ANJAY");
                    }

                    break;
                case "APPLY_PROMO":
                    Customer dumpUser3 = getCustomerById(commands[1]);
                    Promotion dumpPromo = getPromotionById(commands[2]);
                    if (dumpUser3!=null && dumpPromo!=null){
                        if (dumpPromo.isCustomerEligible(dumpUser3)){
                            Date currentDate = new Date();
                            if (!currentDate.after(dumpPromo.getEnd()) || !currentDate.before(dumpPromo.getBegin())){
                                dumpUser3.getLastOrder().setPromotion(dumpPromo);
                            }else{
                                //exception expired
                            }
                        }else {
                            //exception apply promo failed kodepromo
                            return;
                        }
                    }
                    break;
                case "TOPUP":
                    try {
                        Customer dumpUser4 = getCustomerById(commands[1]);
                        int before = dumpUser4.getBalance();
                        dumpUser4.setBalance(before + Integer.parseInt(commands[2]));
                        System.out.println("TOPUP SUCCESS: " + dumpUser4.getName() + " " + before + "=>" + dumpUser4.getBalance());
                    } catch (NullPointerException e) {
                        System.out.println("TOPUP FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
                case "CHECK_OUT":
                        Customer dumpuser5 = getCustomerById(commands[1]);
                        if (dumpuser5!= null){
                            if (dumpuser5.checkOut()){
                                //message success
                            }else{
                                //insufficient balance
                            }
                        }else{
                            //sout non existent
                        }
                    break;
                case "PRINT":
                    Customer dumpUser5 = getCustomerById(commands[1]);
                    if (dumpUser5 instanceof Guest) {
                        ((Guest) dumpUser5).print();
                    } else if (dumpUser5 instanceof Member) {
                        ((Member) dumpUser5).print();
                    }
                    break;
                case "PRINT_HISTORY":
                    Customer dumpUser6 = getCustomerById(commands[1]);
                    if (dumpUser6 instanceof Guest) {
                        ((Guest) dumpUser6).printHistory();
                    } else if (dumpUser6 instanceof Member) {
                        ((Member) dumpUser6).printHistory();
                    }
                    break;
            }
        }
    }

    public static boolean isCustomerIdExist(String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Customer getCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public static Promotion getPromotionById(String id) {
        for (Promotion c : promotions) {
            if (c.getPromoCode().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public static void createMember(String[] datas) {
        if (isCustomerIdExist(datas[0])) {
            System.out.println("CREATE MEMBER FAILED: " + datas[0] + " IS EXISTS");
            return;
        }
        Member member = new Member(datas[0], datas[1], datas[2], Integer.parseInt(datas[3]));
        customers.add(member);
        System.out.println("CREATE MEMBER SUCCESS: " + datas[0] + " " + datas[1]);
    }

    public static void createGuest(String[] datas) {
        if (isCustomerIdExist(datas[0])) {
            System.out.println("CREATE GUEST FAILED: " + datas[0] + " IS EXISTS");
            return;
        }
        Guest guest = new Guest(datas[0], Integer.parseInt(datas[1]));
        customers.add(guest);
        System.out.println("CREATE GUEST SUCCESS: " + datas[0] + " " + datas[1]);
    }

    public static Vehicle getVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

}