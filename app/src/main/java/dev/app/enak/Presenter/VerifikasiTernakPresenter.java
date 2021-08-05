package dev.app.enak.Presenter;

import java.io.File;

public interface VerifikasiTernakPresenter {
    void verifyTernak(String id_ternak, String nama_ternak, String jenis_ternak, String jenis_kelamin, String kode_induk, String kode_pejantan,
                      String tgl_lahir);
    void changeImg(Integer id_ternak, File gambar_depan);
    void detailTernak(String id_ternak);
}
