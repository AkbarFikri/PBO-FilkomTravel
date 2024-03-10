public class Vehicle {
    private String nama;
    private String platKendaraan;
    private boolean isRent;
    private int kapasitas;
    private String jenis;
    private int harga;

    public Vehicle() {
    }

    public Vehicle(String nama, String platKendaraan, int kapasitas, String jenis){
        this.nama = nama;
        this.platKendaraan = platKendaraan;
        this.kapasitas = kapasitas;
        this.jenis = jenis;
    }


    public String getNama() {
        return nama;
    }

    public String getPlatKendaraan() {
        return platKendaraan;
    }

    public boolean getIsRent() {
        return isRent;
    }

    public String getJenis() {
        return jenis;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getHarga() {
        return harga;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setPlatKendaraan(String platKendaraan) {
        this.platKendaraan = platKendaraan;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
