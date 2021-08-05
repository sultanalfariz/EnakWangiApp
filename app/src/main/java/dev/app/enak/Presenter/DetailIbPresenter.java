package dev.app.enak.Presenter;

public interface DetailIbPresenter {
    void DetailIb(String id_permintaan_ib);
    void ubahStatusIbHamil(String id_permintaan_ib, String hamil);
    void ubahStatusIbLahir(String id_permintaan_ib, String lahir);
    void inputTglLahir(String id_permintaan_ib, String tgl_lahir);
}
