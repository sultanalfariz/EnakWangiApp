package dev.app.enak.Presenter;

public interface TambahLaporanPresenter {
    void TambahLaporn(Integer id_ternak, Integer id_pemilik, String keterangan);
    void LoadTernak(String id);
}
