package dev.app.enak.Presenter;

public interface TinjauLaporanPresenter {
    void tambahLayanan(String id_laporan, Integer id_ternak, Integer id_petugas, String jenis_obat, String obat, String dosis);
    void diagnosaSakit(String id_laporan, Integer id_ternak, Integer id_petugas, String diagnosa, String gejala, String morb_mort);
    void DetailLaporan(String id);
}
