import java.time.LocalDate;
import java.util.List;

public class Normal extends User {
    private List<Kuitansi> historyPemesanan;

    public void pesanTravel(Vehicle kendaraan,int lamaSewa) {
        Kuitansi kuitansi = new Kuitansi();
        kuitansi.kendaraan = kendaraan;
        kuitansi.tanggalPesan = LocalDate.now();
        kuitansi.lamaPesan = lamaSewa;
    }
}
