package domain;

public abstract class Promotion implements Applicable, Comparable<Promotion>{
    private String name;
    private String syarat;

    public Promotion(String name, String syarat) {
        this.name = name;
        this.syarat = syarat;
    }

    public String getName() {
        return name;
    }

    public String getSyarat() {
        return syarat;
    }
}
