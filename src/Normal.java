import java.time.LocalDate;
import java.util.List;

public class Normal extends User {
    private List<Kuitansi> historyPemesanan;

    public void pesanTravel(Vehicle kendaraan,int lamaSewa,Normal pemesan) {
        Kuitansi kuitansi = new Kuitansi(kendaraan, lamaSewa, pemesan);
        kuitansi.cetak();
    }
}
