public class Vehicle {
//    kayaknya id gausah soalnya plat udah unique
//    String id;
    String nama;
    String platKendaraan;
    boolean isRent;
    int kapasitas;

    String jenis;

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

    public int getKapasitas() {
        return kapasitas;
    }
}
