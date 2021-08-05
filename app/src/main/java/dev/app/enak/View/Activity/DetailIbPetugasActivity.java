package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dev.app.enak.Implementation.DetailIbPemilikImpl;
import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Presenter.DetailIbPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.DetailIbMvp;

public class DetailIbPetugasActivity extends AppCompatActivity implements DetailIbMvp {

    TextView txtKdAnting, txtStatus, txtKet, txtTgp, txtTglCb, txtTglSib,
            edtBirahi, edtHamil,edtLahir;
    EditText edtTglLahir;
    RelativeLayout btnTutuo;

    DetailIbPresenter detailIbPresenter;

    String id_permintaan_ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ib_petugas);

        detailIbPresenter = new DetailIbPemilikImpl(this);

        id_permintaan_ib = getIntent().getStringExtra("id_permintaan_ib");
        Log.d("dataaaaaaaa", "data inten id ="+id_permintaan_ib);

        detailIbPresenter.DetailIb(id_permintaan_ib);

        txtKdAnting = findViewById(R.id.text_kode_anting);
        txtStatus = findViewById(R.id.text_status_ib);
        txtKet = findViewById(R.id.text_keterangan);
        txtTgp = findViewById(R.id.text_tanggapan_ib);
        txtTglCb = findViewById(R.id.text_tgl_cek_birahi);
        txtTglSib = findViewById(R.id.text_tgl_suntik_ib);

        edtBirahi = findViewById(R.id.form_birahi);
        edtHamil = findViewById(R.id.form_hamil);
        edtLahir = findViewById(R.id.form_lahir);
        edtTglLahir = findViewById(R.id.form_tgl_lahir);

        btnTutuo = findViewById(R.id.btn_tutup);
        btnTutuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void LoadData(List<DetailIbResource> detailIbResources) {
        txtKdAnting.setText(detailIbResources.get(0).getIdTernak().toString());
        txtStatus.setText(detailIbResources.get(0).getStatus().toString());
        txtKet.setText(detailIbResources.get(0).getKeterangan().toString());
        txtTgp.setText(detailIbResources.get(0).getTanggapan().get(0).getKeterangan().toString());
        txtTglCb.setText(detailIbResources.get(0).getTanggapan().get(0).getTglPeninjauan().toString());
        txtTglSib.setText(detailIbResources.get(0).getIb().get(0).getTglSuntikIb());
        edtBirahi.setText(detailIbResources.get(0).getIb().get(0).getBirahi());
        edtHamil.setText(detailIbResources.get(0).getIb().get(0).getHamil());
        edtLahir.setText(detailIbResources.get(0).getIb().get(0).getLahir());
        edtTglLahir.setText(detailIbResources.get(0).getIb().get(0).getTglLahir());
    }

    @Override
    public void DataNull() {

    }

    @Override
    public void PostSuccess() {

    }

    @Override
    public void PostFail() {

    }

    @Override
    public void PostTglSuccess() {

    }
}