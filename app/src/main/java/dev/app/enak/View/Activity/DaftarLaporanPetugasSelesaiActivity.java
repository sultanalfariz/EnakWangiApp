package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dev.app.enak.Implementation.ListLaporanPetugasImpl;
import dev.app.enak.Model.ListLaporanResource;
import dev.app.enak.Presenter.ListLaporanPetugasPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Adapter.ListLaporanPetugasSelesaiAdapter;
import dev.app.enak.View.Adapter.ListLaporanPetugasTinjauAdapter;
import dev.app.enak.View.MvpView.ListLaporanPetugasMvp;

public class DaftarLaporanPetugasSelesaiActivity extends AppCompatActivity implements ListLaporanPetugasMvp {

    RecyclerView rvLaporan;
    TextView txtKosong;

    ListLaporanPetugasPresenter listLaporanPetugasPresenter;
    ListLaporanPetugasSelesaiAdapter listLaporanPetugasSelesaiAdapter;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_laporan_petugas_selesai);

        listLaporanPetugasPresenter = new ListLaporanPetugasImpl(this);

        listLaporanPetugasPresenter.listLaporanSelesai();

        rvLaporan = findViewById(R.id.rec_daftar_laporan);
        txtKosong = findViewById(R.id.txt_info_kosong);

        pDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Mohon Tunggu...");
        pDialog.setCancelable(false);
        pDialog.show();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvLaporan.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void LoadLaporanWait(List<ListLaporanResource> listLaporanResource) {

    }

    @Override
    public void LoadLaporanTinjau(List<ListLaporanResource> listLaporanResource) {

    }

    @Override
    public void LoadLaporanSelesai(List<ListLaporanResource> listLaporanResource) {
        pDialog.dismiss();
        listLaporanPetugasSelesaiAdapter = new ListLaporanPetugasSelesaiAdapter(this, listLaporanResource, this);
        rvLaporan.setAdapter(listLaporanPetugasSelesaiAdapter);
        listLaporanPetugasSelesaiAdapter.notifyDataSetChanged();
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