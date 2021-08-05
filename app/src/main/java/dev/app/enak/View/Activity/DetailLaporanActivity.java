package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dev.app.enak.Implementation.DetailLaporanImpl;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Presenter.DetailLaporanPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.DetailLaporanMvp;

public class DetailLaporanActivity extends AppCompatActivity implements DetailLaporanMvp {

    TextView txtKode, txtStatus, txtKet, txtTg, txtTglTinjau, txtJenisObat, txtObat,txtDosis, txtNamaPetugas, txtNIP,
            txtDiagnosa, txtGejala, txtMorb;
    RelativeLayout btnTutup;

    DetailLaporanPresenter detailLaporanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        detailLaporanPresenter = new DetailLaporanImpl(this);

        String id = getIntent().getStringExtra("id");

        txtKode = findViewById(R.id.text_kode_anting_ternak);
        txtStatus = findViewById(R.id.text_status_laporan);
        txtKet = findViewById(R.id.text_ket_laporan);
        txtTg = findViewById(R.id.text_tanggapan_laporan);
        txtTglTinjau = findViewById(R.id.text_tgl_tinjau_laporan);
        txtJenisObat = findViewById(R.id.text_jenis_tindakan_laporan);
        txtObat = findViewById(R.id.text_obat_diberi);
        txtDosis = findViewById(R.id.text_dosis_diberi);
        txtDiagnosa = findViewById(R.id.text_diagnosa_laporan);
        txtGejala = findViewById(R.id.text_gejala_laporan);
        txtMorb = findViewById(R.id.text_mort_morb);
        txtNamaPetugas = findViewById(R.id.text_nama_petugas);
        txtNIP = findViewById(R.id.text_nip_petugas);

        btnTutup = findViewById(R.id.btn_tutup);
        btnTutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailLaporanPresenter.DetailLaporan(id);
    }

    @Override
    public void LoadData(List<DetailLaporanResource> detailLaporanResources) {
        txtKode.setText("#"+detailLaporanResources.get(0).getIdTernak());
        txtStatus.setText(detailLaporanResources.get(0).getStatus());
        txtKet.setText(detailLaporanResources.get(0).getKeterangan());
        txtTg.setText(detailLaporanResources.get(0).getTanggapan().get(0).getKeterangan());
        txtTglTinjau.setText(detailLaporanResources.get(0).getTanggapan().get(0).getTglPeninjauan());
        txtJenisObat.setText(detailLaporanResources.get(0).getLayanan().get(0).getJenisObat());
        txtObat.setText(detailLaporanResources.get(0).getLayanan().get(0).getObat());
        txtDosis.setText(detailLaporanResources.get(0).getLayanan().get(0).getDosis());
        txtDiagnosa.setText(detailLaporanResources.get(0).getRiwayat().get(0).getDiagnosa());
        txtGejala.setText(detailLaporanResources.get(0).getRiwayat().get(0).getGejala());
        txtMorb.setText(detailLaporanResources.get(0).getRiwayat().get(0).getMorbMort());
        txtNamaPetugas.setText(detailLaporanResources.get(0).getNama().toString());
        txtNIP.setText(detailLaporanResources.get(0).getNip().toString());
    }

    @Override
    public void DataNull() {
        onResume();
    }
}