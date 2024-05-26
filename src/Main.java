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

import java.util.ArrayList;
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
        boolean checkUser = false;
        int target = 0;
        boolean checkVehicle = false;
        int targetVehicle = 0;
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
                        if (isCustomerIdExist(datas[0])) {
                            System.out.println("CREATE MEMBER FAILED: " + datas[0] + " IS EXISTS");
                            break;
                        }
                        Member member = new Member(datas[0], datas[1], datas[2], Integer.parseInt(datas[3]));
                        customers.add(member);
                        System.out.println("CREATE MEMBER SUCCESS: " + datas[0] + " " + datas[1]);
                    } else if (commands[1].equalsIgnoreCase("GUEST")) {
                        String[] datas = commands[2].split("\\|");
                        if (isCustomerIdExist(datas[0])) {
                            System.out.println("CREATE GUEST FAILED: " + datas[0] + " IS EXISTS");
                            break;
                        }
                        Guest guest = new Guest(datas[0], Integer.parseInt(datas[1]));
                        customers.add(guest);
                        System.out.println("CREATE GUEST SUCCESS: " + datas[0] + " " + datas[1]);
                    } else if (commands[1].equalsIgnoreCase("MENU")) {

                    } else if (commands[1].equalsIgnoreCase("PROMO")) {

                    }
                    // TODO Akbar yang ngerjain
                    break;
                case "ADD_TO_CART":
                    checkUser = false;
                    //ini dan bawah kubuat soalnya bingung baut dapetin index nya kalo pake method isExist
                    for (int j = 0; j < customers.size(); j++) {
                        if (commands[1].equalsIgnoreCase(customers.get(j).getId())){
                            checkUser = true;
                            target = j;
                        }
                    }
                    String[] dateTemp = commands[4].split("/");
                    checkVehicle = false;
                    for (int j = 0; j < vehicles.size(); j++) {
                        if (commands[2].equalsIgnoreCase(vehicles.get(j).getId())){
                            checkVehicle = true;
                            target = j;
                        }
                    }

                    if (checkUser&&checkVehicle){
                        boolean checkOrderExist = false;
                        int targetExist=0;
                        for (int j = 0; j < customers.get(target).orders.size(); j++) {
                            if (customers.get(target).orders.get(customers.get(target).orders.size()-1).getOrderItems().get(j).equals(commands[2])){
                                checkOrderExist = true;
                                targetExist = j;
                            }
                        }
                        if (customers.get(target).orders.isEmpty() || customers.get(target).orders.get(customers.get(target).orders.size()-1).isCheckOut()){
                            customers.get(target).makeOrder(vehicles.get(targetVehicle), Integer.parseInt(commands[3]),Integer.parseInt(dateTemp[0]),Integer.parseInt(dateTemp[1]),Integer.parseInt(dateTemp[2]));
                            if (Integer.parseInt(commands[3])==1){
                                System.out.println("ADD_TO_CART SUCCESS: "+commands[3]+" day "+vehicles.get(targetVehicle).getName()+" "+vehicles.get(targetVehicle).getPlatNumber()+ " (NEW)");
                            }else {
                                System.out.println("ADD_TO_CART SUCCESS: "+commands[3]+" days "+vehicles.get(targetVehicle).getName()+" "+vehicles.get(targetVehicle).getPlatNumber()+ " (NEW)");
                            }
                        }else if(checkOrderExist){
                            customers.get(target).orders.get(customers.get(target).orders.size()-1).getOrderItems().get(targetExist).UpdateDate(Integer.parseInt(commands[3]));
                            if (Integer.parseInt(commands[3])==1){
                                System.out.println("ADD_TO_CART SUCCESS: "+commands[3]+" day "+vehicles.get(targetVehicle).getName()+" "+vehicles.get(targetVehicle).getPlatNumber()+ " (UPDATED)");
                            }else {
                                System.out.println("ADD_TO_CART SUCCESS: "+commands[3]+" days "+vehicles.get(targetVehicle).getName()+" "+vehicles.get(targetVehicle).getPlatNumber()+ " (UPDATED)");
                            }
                        }else{
                            customers.get(target).addToCart(vehicles.get(targetVehicle), Integer.parseInt(commands[3]),Integer.parseInt(dateTemp[0]),Integer.parseInt(dateTemp[1]),Integer.parseInt(dateTemp[2]));
                            if (Integer.parseInt(commands[3])==1){
                                System.out.println("ADD_TO_CART SUCCESS: "+commands[3]+" day "+vehicles.get(targetVehicle).getName()+" "+vehicles.get(targetVehicle).getPlatNumber()+ " (NEW)");
                            }else {
                                System.out.println("ADD_TO_CART SUCCESS: "+commands[3]+" days "+vehicles.get(targetVehicle).getName()+" "+vehicles.get(targetVehicle).getPlatNumber()+ " (NEW)");
                            }
                        }
                    }else {
                        System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                    }

                    break;
                case "REMOVE_FROM_CART":
                    checkUser = false;
                    for (int j = 0; j < customers.size(); j++) {
                        if (commands[1].equalsIgnoreCase(customers.get(j).getId())){
                            checkUser = true;
                            target = j;
                        }
                    }
                    boolean checkOrderExist = false;
                    int targetExist=0;
                    for (int j = 0; j < customers.get(target).orders.size(); j++) {
                        if (customers.get(target).orders.get(customers.get(target).orders.size()-1).getOrderItems().get(j).equals(commands[2])){
                            checkOrderExist = true;
                            targetExist = j;
                        }
                    }

                    if (checkUser&&checkOrderExist){
                        customers.get(target).orders.get(customers.get(target).orders.size()-1).getOrderItems().get(targetExist).decreaseDate(Integer.parseInt(commands[3]));
                        if (customers.get(target).orders.get(customers.get(target).orders.size()-1).getOrderItems().get(targetExist).getRentalTime()<=0){
                            //Sout message hapus
                            customers.get(target).orders.get(customers.get(target).orders.size()-1).getOrderItems().remove(targetExist);
                        } else {
                            //sout message decrease
                        }

                    }else{
                        //sout message error
                    }

                    break;
                case "APPLY_PROMO":
                    // TODO Delvin yang ngerjain
                    break;
                case "TOPUP":
                    checkUser = false;
                    for (int j = 0; j < customers.size(); j++) {
                        if (commands[1].equalsIgnoreCase(customers.get(j).getId())){
                            checkUser = true;
                            target = j;
                        }
                    }
                    if (checkUser){
                        int before = customers.get(target).getBalance();
                        customers.get(target).setBalance(before+Integer.parseInt(commands[2]));
                        System.out.println("TOPUP SUCCESS: "+customers.get(target).getName()+" "+before+"=>"+customers.get(target).getBalance());
                    }else {
                        System.out.println("TOPUP FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
                case "CHECK_OUT":

                    break;
                case "PRINT":
                    // TODO Delvin yang ngerjain
                    break;
                case "PRINT_HISTORY":
                    // TODO Akbar yang ngerjain
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

}