package domain;

public abstract class Customer {
    private String name;
    private String id;
    private int balance;

    protected Customer(String name, String id, int balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBalance(int balance) { this.balance = balance; }

    public int getBalance() { return balance; }

    public abstract void addToCart();
    
    public abstract void makeOrder();

}
