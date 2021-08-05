package dev.app.enak.test.integrasi;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.File;

import dev.app.enak.BuildConfig;
import dev.app.enak.View.MvpView.TambahTernakMvp;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16, manifest = "src/main/AndroidManifest.xml", packageName = "dev.app.enak")
public class IntegrasiTestMethodTest {

    @Mock
    TambahTernakMvp tambahTernakMvp;

    @Mock
    IntegrasiTestMethod integrasiTestMethod;

    @Mock
    File file;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        integrasiTestMethod = new IntegrasiTestMethod(tambahTernakMvp);
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

        integrasiTestMethod.TambahTernak(id_pemilik, namaTernak, bangsa, jenisKel, kodeInduk, kodePejantan,
                alamat, rt, rw, kel, kec,tglLahir, file);

        Mockito.verify(tambahTernakMvp).PostSuccess();
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

        integrasiTestMethod.TambahTernak(id_pemilik, namaTernak, bangsa, jenisKel, kodeInduk, kodePejantan,
                alamat, rt, rw, kel, kec,tglLahir, file);

        Mockito.verify(tambahTernakMvp).PostFailed();
    }
}
