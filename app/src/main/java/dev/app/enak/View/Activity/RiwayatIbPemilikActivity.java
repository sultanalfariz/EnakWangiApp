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

import dev.app.enak.Implementation.ListIbPemilikImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.ListIbResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.ListIbPemilikPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Adapter.ListIbPemilikWaitAdapter;
import dev.app.enak.View.Adapter.ListIbSelesaiPemilikAdapter;
import dev.app.enak.View.MvpView.ListIbPemilikMvp;

public class RiwayatIbPemilikActivity extends AppCompatActivity implements ListIbPemilikMvp {

    RecyclerView rvIb;
    TextView txtKosong;

    GetUserResource getUserResource;

    ListIbPemilikPresenter listIbPemilikPresenter;

    ListIbSelesaiPemilikAdapter listIbSelesaiPemilikAdapter;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_ib_pemilik);

        listIbPemilikPresenter = new ListIbPemilikImpl(this);

        sharedPreferences = this.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(this, Prefs.USER_SESSION);

        String id_pemilik = getUserResource.getId().toString();

        txtKosong = findViewById(R.id.txt_info_kosong);

        pDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Mohon Tunggu...");
        pDialog.setCancelable(false);
        pDialog.show();

        rvIb = findViewById(R.id.rec_daftar_ib_pemilik);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvIb.setLayoutManager(linearLayoutManager);

        listIbPemilikPresenter.listIbSelesai(id_pemilik);
    }

    @Override
    public void LoadIbWait(List<ListIbResource> listIbResources) {

    }

    @Override
    public void LoadIbSelesai(List<ListIbResource> listIbResources) {
        pDialog.dismiss();
        listIbSelesaiPemilikAdapter = new ListIbSelesaiPemilikAdapter(this, listIbResources, this);
        rvIb.setAdapter(listIbSelesaiPemilikAdapter);
        listIbSelesaiPemilikAdapter.notifyDataSetChanged();
    }

    @Override
    public void LoadIbProses(List<ListIbResource> listIbResources) {

    }

    @Override
    public void DataEmpty(String message) {
        if (message.equals("Gagal")){
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