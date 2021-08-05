package dev.app.enak.test.unit;

import android.content.Context;

import java.io.File;

public class UnitTestMethod {

    public String tambahTernak(Integer id_pemilik, String namaTernak, String tglLahir, String alamat, String bangsa, String jenisKel,
                               String kodeInduk, String KodePejantan, String rt, String rw, String kel, String kec, File file) {

        if (namaTernak.equals("") || tglLahir.equals("") || alamat.equals("") || bangsa.equals("") || jenisKel.equals("") ||
            rt.equals("") || rw.equals("") || kel.equals("") || kec.equals("") || file == null){
            return "Mohon lengkapi kolom";
        } else{
            return "Berhasil ditambahkan";
        }
    }

    public String tanggapiTernak(String id_ternak, String tglTinjau) {

        if (tglTinjau.equals("")){
            return "Mohon lengkapi kolom";
        } else{
            return "Berhasil tanggapi ternak";
        }
    }

    public String verifikasiTernak(Integer id_pemilik, String namaTernak, String tglLahir, String alamat, String bangsa, String jenisKel,
                                   String kodeInduk, String KodePejantan, String rt, String rw, String kel, String kec) {

        if (namaTernak.equals("") || tglLahir.equals("") || alamat.equals("") || bangsa.equals("") || jenisKel.equals("") ||
                rt.equals("") || rw.equals("") || kel.equals("") || kec.equals("")){
            return "Mohon lengkapi kolom";
        } else{
            return "Berhasil verifikasi ternak";
        }
    }
}
