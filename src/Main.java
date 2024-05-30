/*
* NIM DAN NAMA ANGGOTA KELOMPOK :
* 1. 235150201111058 Akbar Fikri Abdillah
* 2. 235150201111055 Martinus Delvin
* 3. 235150201111065 Benedictus Giri Cahya Saputra
*/

import domain.*;
import entity.*;
import entity.promotion.CashbackPromo;
import entity.promotion.PercentOffPromo;
import utils.CarType;
import utils.customException.InsufficientBalanceException;
import utils.customException.InvalidApplyPromoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

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
                        if (datas.length == 2) {
                            String[] dump = new String[4];
                            datas[1] = datas[1] +  " " + commands[3].split("\\|")[0];
                            String[] dump2 = commands[3].split("\\|");
                            for (int j = 0; j < dump.length; j++) {
                                if (j == 0 || j == 1) {
                                    dump[j] = datas[j];
                                } else {
                                    if (dump2[j-1] != null) {
                                        dump[j] = dump2[j-1];
                                    }
                                }

                            }

                            datas = dump;
                        }
                        createMember(datas);
                    } else if (commands[1].equalsIgnoreCase("GUEST")) {
                        String[] datas = commands[2].split("\\|");
                        if (datas.length == 2) {
                            String[] dump = new String[4];
                            datas[1] = datas[1] +  " " + commands[3].split("\\|")[0];
                            String[] dump2 = commands[3].split("\\|");
                            for (int j = 0; j < dump.length; j++) {
                                if (j == 0 || j == 1) {
                                    dump[j] = datas[j];
                                } else {
                                    if (dump2[j-1] != null) {
                                        dump[j] = dump2[j-1];
                                    }
                                }

                            }

                            datas = dump;
                        }
                        createGuest(datas);
                    } else if (commands[1].equalsIgnoreCase("MENU")) {
                        String[] datas = commands[3].split("\\|");
                        if (datas.length == 2) {
                            String[] dump = new String[5];
                            datas[1] = datas[1] +  " " + commands[4].split("\\|")[0];
                            String[] dump2 = commands[4].split("\\|");
                            for (int j = 0; j < dump.length; j++) {
                                if (j == 0 || j == 1) {
                                    dump[j] = datas[j];
                                } else {
                                    if (j <= dump2.length && dump2[j-1] != null) {
                                        dump[j] = dump2[j-1];
                                    }
                                }

                            }

                            datas = dump;
                        }
                        if (isVehicleIdExist(datas[0])) System.out.println("CREATE MENU FAILED: " + datas[0] + " IS EXISTS");
                        if (isVehiclePlatExist(datas[2])) System.out.println("CREATE MENU FAILED: " + datas[2] + " IS EXISTS");
                        if (commands[2].equalsIgnoreCase("MOTOR")) {
                            Motorcycle motor = new Motorcycle(
                                    datas[0],
                                    datas[1],
                                    datas[2],
                                    Integer.parseInt(datas[3]));
                            vehicles.add(motor);
                            System.out.println("CREATE MENU SUCCESS: " + motor.getId() + " " + motor.getName() + " " + motor.getPlatNumber());
                        } else if (commands[2].equalsIgnoreCase("MOBIL")) {
                            Car mobil = new Car(
                                    datas[0],
                                    datas[1],
                                    datas[2],
                                    Integer.parseInt(datas[3]),
                                    datas[4].equalsIgnoreCase("L") ? CarType.LARGE : CarType.REGULER);
                            vehicles.add(mobil);
                            System.out.println("CREATE MENU SUCCESS: " + mobil.getId() + " " + mobil.getName() + " " + mobil.getPlatNumber());
                        }
                    } else if (commands[1].equalsIgnoreCase("PROMO")) {
                        String[] datas = inputs.get(i + 1).split("\\|");
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        if (isPromoCodeExist(datas[0])) {
                            System.out.println("CREATE PROMO CASHBACK FAILED: " + datas[0] + " IS EXISTS");
                            break;
                        }
                        if (commands[2].equalsIgnoreCase("CASHBACK")) {
                            String percentPromo = datas[3].split("%")[0];
                            try {
                                CashbackPromo cashbackPromo = new CashbackPromo(
                                        datas[0],
                                        formatter.parse(datas[1]),
                                        formatter.parse(datas[2]),
                                        Integer.parseInt(percentPromo),
                                        Integer.parseInt(datas[4]),
                                        Integer.parseInt(datas[5]));
                                promotions.add(cashbackPromo);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("CREATE PROMO SUCCESS: " + datas[0]);
                        } else if (commands[2].equalsIgnoreCase("DISCOUNT")) {
                            String percentPromo = datas[3].split("%")[0];
                            try {
                                PercentOffPromo discountPromo = new PercentOffPromo(
                                        datas[0],
                                        formatter.parse(datas[1]),
                                        formatter.parse(datas[2]),
                                        Integer.parseInt(percentPromo),
                                        Integer.parseInt(datas[4]),
                                        Integer.parseInt(datas[5]));
                                promotions.add(discountPromo);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("CREATE PROMO SUCCESS: " + datas[0]);
                        }
                    }
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
                            if (dumpUser.getLastOrder().getOrderItemById(commands[2]).getRentalTime() == 1) {
                                System.out.println("ADD_TO_CART SUCCESS: " + dumpUser.getLastOrder().getOrderItemById(commands[2]).getRentalTime() + " day "
                                        + vehicle.getName() + " "
                                        + vehicle.getPlatNumber() + " (UPDATED)");
                            } else {
                                System.out.println("ADD_TO_CART SUCCESS: " + dumpUser.getLastOrder().getOrderItemById(commands[2]).getRentalTime() + " days "
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
                        if (dumpOrderItem == null) throw new NullPointerException();
                        if (dumpOrderItem.getRentalTime() <= 0) {
                            dumpUser2.getLastOrder().deleteItemById(dumpOrderItem.getVehicle().getId());
                            System.out.println("REMOVE_FROM_CART SUCCESS: " + dumpOrderItem.getVehicle().getName() + " IS REMOVED");
                        } else {
                            System.out.println("REMOVE_FROM_CART SUCCESS: " + dumpOrderItem.getVehicle().getName() + " QUANTITY IS DECREMENTED");
                        }
                        break;
                    } catch (NullPointerException e) {
                        System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                    }
                    break;
                case "APPLY_PROMO":
                    try {
                        Customer dumpUser3 = getCustomerById(commands[1]);
                        Promotion dumpPromo = getPromotionByCode(commands[2]);
                        dumpUser3.getLastOrder().applyPromo(dumpPromo, dumpUser3);
                    } catch (InvalidApplyPromoException e) {
                        System.out.println("APPLY_PROMO FAILED: " + e.getMessage());
                    } catch (NullPointerException e) {
                        System.out.println("APPLY_PROMO FAILED: NON EXISTENT CUSTOMER OR PROMO");
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
                    try {
                        Customer dumpuser5 = getCustomerById(commands[1]);
                        dumpuser5.checkOut(nomorPesanan++);
                        System.out.println("CHECK_OUT SUCCESS: " + dumpuser5.getLastOrder().getOrderNum() + " " + dumpuser5.getName());
                    } catch (InsufficientBalanceException e) {
                        System.out.println("CHECK_OUT FAILED: " + e.getMessage());
                    } catch (NullPointerException e) {
                        System.out.println("CHECK_OUT FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
                case "PRINT":
                    try {
                        Customer dumpUser5 = getCustomerById(commands[1]);
                        dumpUser5.printLastOrder();
                    } catch (NullPointerException e) {
                        System.out.println("PRINT FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
                case "PRINT_HISTORY":
                    try {
                        Customer dumpUser6 = getCustomerById(commands[1]);
                        dumpUser6.printHistory();
                    } catch (NullPointerException e) {
                        System.out.println("PRINT_HISTORY FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
            }
        }
    }

    public static boolean isVehicleIdExist(String id) {
        if (vehicles.isEmpty()) return false;
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVehiclePlatExist(String plat) {
        if (vehicles.isEmpty()) return false;
        for (Vehicle v : vehicles) {
            if (v.getPlatNumber().equals(plat)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCustomerIdExist(String id) {
        if (customers.isEmpty()) return false;
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPromoCodeExist(String code) {
        for (Promotion p : promotions) {
            if (p.getPromoCode().equals(code)) {
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
        throw new NullPointerException();
    }

    public static Vehicle getVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        throw new NullPointerException();
    }

    public static Promotion getPromotionByCode (String code) {
        for (Promotion c : promotions) {
            if (c.getPromoCode().equals(code)) {
                return c;
            }
        }
        throw new NullPointerException();
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

}