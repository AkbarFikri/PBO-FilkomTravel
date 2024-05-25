package domain;

public abstract class Customer {
    private String name;
    private String id;

    protected Customer(String name, String id) {
        this.name = name;
        this.id = id;
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

    public abstract void addToCart();
    
    public abstract void makeOrder();

}
