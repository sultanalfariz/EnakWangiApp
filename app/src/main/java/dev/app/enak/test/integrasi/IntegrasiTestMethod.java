package dev.app.enak.test.integrasi;

import java.io.File;

import dev.app.enak.View.MvpView.TambahTernakMvp;

public class IntegrasiTestMethod implements TambahTernakPresenterTest{

    TambahTernakMvp tambahTernakMvp;

    public IntegrasiTestMethod(TambahTernakMvp tambahTernakMvp){
        this.tambahTernakMvp = tambahTernakMvp;
    }

    @Override
    public void TambahTernak(Integer id_pemilik, String nama_ternak, String jenis_ternak, String jenis_kelamin, String kode_induk,
                             String kode_pejantan, String alamat_kandang, String kel_kandang, String kec_kandang, String rt, String rw,
                             String tgl_lahir, File gambar_depan) {
        if (nama_ternak.equals("") || tgl_lahir.equals("") || alamat_kandang.equals("") || jenis_ternak.equals("") || jenis_kelamin.equals("") ||
                rt.equals("") || rw.equals("") || kel_kandang.equals("") || kec_kandang.equals("") || gambar_depan == null){
            tambahTernakMvp.PostFailed();
        } else{
            tambahTernakMvp.PostSuccess();
        }
    }
}
