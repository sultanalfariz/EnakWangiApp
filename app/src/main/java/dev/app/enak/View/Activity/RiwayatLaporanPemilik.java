package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev.app.enak.Implementation.ListLaporanPemilikImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.ListLaporanResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.ListLaporanPemilikPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Adapter.ListLaporanPemilikAdapter;
import dev.app.enak.View.Adapter.ListLaporanSelesaiPemilikAdapter;
import dev.app.enak.View.MvpView.ListLaporanPemilikMvp;

public class RiwayatLaporanPemilik extends AppCompatActivity implements ListLaporanPemilikMvp {

    RecyclerView rvLaporan;
    TextView txtKosong;

    GetUserResource getUserResource;

    ListLaporanPemilikPresenter listLaporanPemilikPresenter;

    ListLaporanSelesaiPemilikAdapter listLaporanSelesaiPemilikAdapter;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_laporan_pemilik);

        listLaporanPemilikPresenter = new ListLaporanPemilikImpl(this);

        sharedPreferences = this.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(this, Prefs.USER_SESSION);

        String id_pemilik = getUserResource.getId().toString();

        txtKosong = findViewById(R.id.txt_info_kosong);

        pDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Mohon Tunggu...");
        pDialog.setCancelable(false);
        pDialog.show();

        rvLaporan = findViewById(R.id.rec_daftar_laporan_pemilik);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvLaporan.setLayoutManager(linearLayoutManager);

        listLaporanPemilikPresenter.listLaporanSelesai(id_pemilik);
    }

    @Override
    public void LoadLaporan(List<ListLaporanResource> listLaporanResources) {

    }

    @Override
    public void LoadLaporanSelesai(List<ListLaporanResource> listLaporanResources) {
        pDialog.dismiss();
        listLaporanSelesaiPemilikAdapter = new ListLaporanSelesaiPemilikAdapter(this, listLaporanResources, this);
        rvLaporan.setAdapter(listLaporanSelesaiPemilikAdapter);
        listLaporanSelesaiPemilikAdapter.notifyDataSetChanged();
    }

    @Override
    public void DataEmpty(String message) {
        if (message.equals("Data tidak ditemukan.")){
            pDialog.dismiss();
            txtKosong.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void DataNull() {
        pDialog.dismiss();
        onResume();
    }
}