package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import domain.Customer;

public class Member extends Customer {
    Date registrationDate;

    public Member(String id, String name, String registrationDate, int firstBalance) {
        super(name, id, firstBalance);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.registrationDate = formatter.parse(registrationDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
