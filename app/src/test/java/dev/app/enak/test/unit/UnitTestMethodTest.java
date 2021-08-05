package dev.app.enak.test.unit;


import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.File;

import dev.app.enak.BuildConfig;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16, manifest = "src/main/AndroidManifest.xml", packageName = "dev.app.enak")
public class UnitTestMethodTest {

    @Mock
    private UnitTestMethod unitTestMethod;

    @Mock
    File file;

    @Before
    public void setup(){
        unitTestMethod = new UnitTestMethod();
    }

    @Test
    public void tambahTernakTrue(){
        Integer id_pemilik = 1;
        String namaTernak = "TINUP";
        String bangsa = "Limosin";
        String jenisKel = "Jantan";
        String kodeInduk = "123";
        String kodePejantan = "222";
        String alamat = "Jl. Ikan Sadar";
        String rt = "001";
        String rw = "002";
        String kel = "Karangrejo";
        String kec = "Banyuwangi";
        String tglLahir = "1-11-2011";
        Context context = RuntimeEnvironment.application.getApplicationContext();
        file = new File(context.getCacheDir(), "cacheFileAppeal.jpg");

        assertEquals("Hasil harus benar","Berhasil ditambahkan", unitTestMethod.tambahTernak(id_pemilik,
                namaTernak, bangsa, jenisKel, kodeInduk, kodePejantan, alamat, rt, rw, kel, kec,tglLahir, file));
    }

    @Test
    public void tambahTernakFalse(){
        Integer id_pemilik = 1;
        String namaTernak = "TINUP";
        String bangsa = "";
        String jenisKel = "";
        String kodeInduk = "123";
        String kodePejantan = "222";
        String alamat = "Jl. Ikan Sadar";
        String rt = "001";
        String rw = "002";
        String kel = "Karangrejo";
        String kec = "Banyuwangi";
        String tglLahir = "1-11-2011";
        Context context = RuntimeEnvironment.application.getApplicationContext();
        file = new File(context.getCacheDir(), "cacheFileAppeal.jpg");

        assertEquals("Hasil harus benar","Mohon lengkapi kolom", unitTestMethod.tambahTernak(id_pemilik,
                namaTernak, bangsa, jenisKel, kodeInduk, kodePejantan, alamat, rt, rw, kel, kec,tglLahir, file));
    }

    @Test
    public void tanggapiTernakTrue(){
        String id_ternak = "1";
        String tglTinjau = "1-11-2011";

        assertEquals("Hasil harus benar","Berhasil tanggapi ternak", unitTestMethod.tanggapiTernak(id_ternak, tglTinjau));
    }

    @Test
    public void tanggapiTernakFalse(){
        String id_ternak = "1";
        String tglTinjau = "";

        assertEquals("Hasil harus benar","Mohon lengkapi kolom", unitTestMethod.tanggapiTernak(id_ternak, tglTinjau));
    }

    @Test
    public void verifikasiTernakTrue(){
        Integer id_pemilik = 1;
        String namaTernak = "TINUP";
        String bangsa = "Limosin";
        String jenisKel = "Jantan";
        String kodeInduk = "123";
        String kodePejantan = "222";
        String alamat = "Jl. Ikan Sadar";
        String rt = "001";
        String rw = "002";
        String kel = "Karangrejo";
        String kec = "Banyuwangi";
        String tglLahir = "1-11-2011";

        assertEquals("Hasil harus benar","Berhasil verifikasi ternak", unitTestMethod.verifikasiTernak(id_pemilik,
                namaTernak, bangsa, jenisKel, kodeInduk, kodePejantan, alamat, rt, rw, kel, kec,tglLahir));
    }

    @Test
    public void VerifikasiTernakFalse(){
        Integer id_pemilik = 1;
        String namaTernak = "TINUP";
        String bangsa = "";
        String jenisKel = "";
        String kodeInduk = "123";
        String kodePejantan = "222";
        String alamat = "Jl. Ikan Sadar";
        String rt = "001";
        String rw = "002";
        String kel = "Karangrejo";
        String kec = "Banyuwangi";
        String tglLahir = "1-11-2011";

        assertEquals("Hasil harus benar","Mohon lengkapi kolom", unitTestMethod.verifikasiTernak(id_pemilik,
                namaTernak, bangsa, jenisKel, kodeInduk, kodePejantan, alamat, rt, rw, kel, kec,tglLahir));
    }
}
