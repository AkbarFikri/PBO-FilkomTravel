import java.util.List;

public class Normal extends User{
    private List<Kuitansi> historyPemesanan;

    public void pesanTravel() {
        // TODO Implement me!
    }

    public void batalkanPesanan() {
        // TODO Implement me!
    }

    public List<Kuitansi> getHistoryPemesanan() {
        return historyPemesanan;
    }

    public void setHistoryPemesanan(List<Kuitansi> historyPemesanan) {
        this.historyPemesanan = historyPemesanan;
    }
}
