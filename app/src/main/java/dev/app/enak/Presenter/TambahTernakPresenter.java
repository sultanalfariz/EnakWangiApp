package dev.app.enak.Presenter;

import java.io.File;

public interface TambahTernakPresenter {
    void TambahTernak(Integer id_pemilik, String nama_ternak, String jenis_ternak, String jenis_kelamin, String kode_induk, String kode_pejantan,
                      String alamat_kandang, String kel_kandang, String kec_kandang, String rt, String rw, String tgl_lahir, File gambar_depan);
}
