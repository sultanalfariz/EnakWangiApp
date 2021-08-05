package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dev.app.enak.Implementation.ListIbPetugasImpl;
import dev.app.enak.Model.ListIbResource;
import dev.app.enak.Presenter.ListIbPetugasPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Adapter.ListIbPetugasProsesAdapter;
import dev.app.enak.View.Adapter.ListIbPetugasSelesaiAdapter;
import dev.app.enak.View.Adapter.ListLaporanPetugasSelesaiAdapter;
import dev.app.enak.View.MvpView.ListIbPetugasMvp;

public class DaftarIbPetugasSelesaiActivity extends AppCompatActivity implements ListIbPetugasMvp {

    RecyclerView rvIb;
    TextView txtKosong;

    ListIbPetugasPresenter listIbPetugasPresenter;
    ListIbPetugasSelesaiAdapter listIbPetugasSelesaiAdapter;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_ib_petugas_selesai);

        listIbPetugasPresenter = new ListIbPetugasImpl(this);

        listIbPetugasPresenter.listIbSelesai();

        rvIb = findViewById(R.id.rec_daftar_ib_petugas);
        txtKosong = findViewById(R.id.txt_info_kosong);

        pDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Mohon Tunggu...");
        pDialog.setCancelable(false);
        pDialog.show();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvIb.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void LoadIbWait(List<ListIbResource> listIbResources) {

    }

    @Override
    public void LoadIbTinjau(List<ListIbResource> listIbResources) {

    }

    @Override
    public void LoadIbProses(List<ListIbResource> listIbResources) {

    }

    @Override
    public void LoadIbSelesai(List<ListIbResource> listIbResources) {
        pDialog.dismiss();
        listIbPetugasSelesaiAdapter = new ListIbPetugasSelesaiAdapter(this, listIbResources, this);
        rvIb.setAdapter(listIbPetugasSelesaiAdapter);
        listIbPetugasSelesaiAdapter.notifyDataSetChanged();
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