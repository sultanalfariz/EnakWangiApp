package dev.app.enak.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.app.enak.Implementation.TambahIbImpl;
import dev.app.enak.Implementation.TambahLaporanImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.ListTernakResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.TambahIbPresenter;
import dev.app.enak.Presenter.TambahLaporanPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.TambahIbMvp;

public class TambahIbActivity extends AppCompatActivity implements TambahIbMvp {

    EditText edtKeterangan;
    TextView edtTernak;
    RelativeLayout btnTambahkan;

    TambahIbPresenter tambahIbPresenter;
    GetUserResource getUserResource;

    Integer id_pemilik, id_ternak;
    String ternak, ket;


    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ib);

        tambahIbPresenter = new TambahIbImpl(this);

        sharedPreferences = this.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(this, Prefs.USER_SESSION);

        id_pemilik = getUserResource.getId();

        edtTernak = findViewById(R.id.form_pilih_ternak);
        edtKeterangan = findViewById(R.id.form_keterangan);
        btnTambahkan = findViewById(R.id.btn_tambahkan);

        tambahIbPresenter.LoadTernak(id_pemilik.toString());
    }

    @Override
    public void LoadDataTernak(List<ListTernakResource> listTernakResources) {
        Log.d("dataaaaaaaaaa","masuk sini");
        final String[] dataTernak = new String[listTernakResources.size()];
        for (int i=0;i<listTernakResources.size();i++){
            dataTernak[i] = listTernakResources.get(i).getNamaTernak().toString();
        }
        edtTernak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dataaaaaaaaaa","ditekan");
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahIbActivity.this);
                builder.setTitle("Pilih Ternak anda")

                        .setItems(dataTernak, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtTernak.setText(listTernakResources.get(which).getNamaTernak());
                                id_ternak = listTernakResources.get(which).getIdTernak();
                            }
                        });

                builder.setNegativeButton("Cancel",null);
                AlertDialog alertDialog =builder.create();
                alertDialog.show();
            }
        });

        btnTambahkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ternak = edtTernak.getText().toString();
                ket = edtKeterangan.getText().toString();

                if (ternak.length() == 0 || ket.length() == 0){
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi formulir tambah laporan anda",
                            Toast.LENGTH_LONG).show();
                } else {
                    pDialog = new ProgressDialog(TambahIbActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    tambahIbPresenter.TambahIb(id_ternak, id_pemilik, ket);
                }
            }
        });
    }

    @Override
    public void DataEmpty(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(TambahIbActivity.this);
        builder.setMessage("Laporan anda berhasil ditambahkan, mohon tunggu petugas kami untuk melakukan verifikasi dan peninjauan.")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TambahIbActivity.this, MainActivity.class);
                        intent.putExtra("posisi", "2");
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void PostFailed() {
        pDialog.dismiss();
    }
}