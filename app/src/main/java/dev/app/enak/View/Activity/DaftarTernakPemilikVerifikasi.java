package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dev.app.enak.Implementation.ListTernakPemilikImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.ListTernakResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.ListTernakPemilikPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Adapter.ListTernakPemilikVerifyAdapter;
import dev.app.enak.View.MvpView.ListTernakPemilikMvp;

public class DaftarTernakPemilikVerifikasi extends AppCompatActivity implements ListTernakPemilikMvp {

    RecyclerView rvListTernak;
    TextView txtKosong;

    GetUserResource getUserResource;

    ListTernakPemilikPresenter listTernakPemilikPresenter;

    ListTernakPemilikVerifyAdapter listTernakPemilikVerifyAdapter;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_ternak_pemilik_verifikasi);

        listTernakPemilikPresenter = new ListTernakPemilikImpl(this);

        sharedPreferences = this.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(this, Prefs.USER_SESSION);

        String id_pemilik = getUserResource.getId().toString();

        txtKosong = findViewById(R.id.txt_info_kosong);

        pDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Mohon Tunggu...");
        pDialog.setCancelable(false);
        pDialog.show();

        rvListTernak = findViewById(R.id.rec_daftar_ternak);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvListTernak.setLayoutManager(linearLayoutManager);

        listTernakPemilikPresenter.listTernakVerify(id_pemilik);
    }

    @Override
    public void LoadTernakWait(List<ListTernakResource> listTernakResources) {
        
    }

    @Override
    public void LoadTernakVerify(List<ListTernakResource> listTernakResources) {
        pDialog.dismiss();
        listTernakPemilikVerifyAdapter = new ListTernakPemilikVerifyAdapter(this, listTernakResources, this);
        rvListTernak.setAdapter(listTernakPemilikVerifyAdapter);
        listTernakPemilikVerifyAdapter.notifyDataSetChanged();
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
        onResume();
    }
}