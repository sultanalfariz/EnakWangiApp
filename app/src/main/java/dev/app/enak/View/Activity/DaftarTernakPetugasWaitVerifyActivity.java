package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dev.app.enak.Implementation.ListTernakPetugasImpl;
import dev.app.enak.Model.ListTernakResource;
import dev.app.enak.Presenter.ListTernakPetugasPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Adapter.ListTernakPemilikVerifyAdapter;
import dev.app.enak.View.Adapter.ListTernakPetugasWaitVerifyAdapter;
import dev.app.enak.View.MvpView.ListTernakPetugasMvp;

public class DaftarTernakPetugasWaitVerifyActivity extends AppCompatActivity implements ListTernakPetugasMvp {

    RecyclerView rvListTernak;
    TextView txtKosong;

    ListTernakPetugasPresenter listTernakPetugasPresenter;
    ListTernakPetugasWaitVerifyAdapter listTernakPetugasWaitVerifyAdapter;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_ternak_petugas_wait_verify);

        listTernakPetugasPresenter = new ListTernakPetugasImpl(this);

        txtKosong = findViewById(R.id.txt_info_kosong);

        pDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Mohon Tunggu...");
        pDialog.setCancelable(false);
        pDialog.show();

        rvListTernak = findViewById(R.id.rec_daftar_ternak);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvListTernak.setLayoutManager(linearLayoutManager);

        listTernakPetugasPresenter.listTernakWaitVerify();
    }

    @Override
    public void LoadTernakWaitVerify(List<ListTernakResource> listTernakResources) {
        pDialog.dismiss();
        listTernakPetugasWaitVerifyAdapter = new ListTernakPetugasWaitVerifyAdapter(this, listTernakResources, this);
        rvListTernak.setAdapter(listTernakPetugasWaitVerifyAdapter);
        listTernakPetugasWaitVerifyAdapter.notifyDataSetChanged();
    }

    @Override
    public void LoadTernakWaitTinjau(List<ListTernakResource> listTernakResources) {

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