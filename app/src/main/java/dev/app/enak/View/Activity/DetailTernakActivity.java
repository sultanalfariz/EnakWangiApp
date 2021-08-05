package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Implementation.DetailTernakImpl;
import dev.app.enak.Model.DetailTernakResource;
import dev.app.enak.Presenter.DetailTernakPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Adapter.RiwayatAmbAdapter;
import dev.app.enak.View.Adapter.RiwayatIbAdapter;
import dev.app.enak.View.MvpView.DetailTernakMvp;

public class DetailTernakActivity extends AppCompatActivity implements DetailTernakMvp {

    ImageView imgTernak;
    ImageView btnPlusPemilik, btnMinPemilik, btnPlusTernak, btnMinTernak,
    btnPlusIB, btnMinIB, btnPlusAmb, btnMinAmb, btnPlusVer, btnMinVer;
    ImageView imgVerWait, imgVerKonfirm, imgVerDate;
    TextView txtVerMenunggu, txtVerDikonfim, txtVerTinjau, txtInfoTernak;
    View view1, view2;

    LinearLayout kontenTernak, kontenPemilik, kontenIB, kontenAmb, kontenVer;
    RelativeLayout hdrTernak, hdrPemilik, hdrIB, hdrAmb, hdrVer;

    TextView txtNamaTernak, txtTgllahirTernak, txtKodeInduk, txtKodePejantan, txtBangsa, txtJenisKelamin,
    txtNamaPemilik, txtNIK, txtAlamatPemilik, txtIbKosong, txtAmbKosong, txtStatusTernak, txtTglPeninjauan,
    txtNamaPetugas, txtNIP;

    RecyclerView rvIB, rvAmb;

    CardView cardVerifikasi, cardPemilik, cardIB, cardAmb, cardTernak;

    DetailTernakPresenter detailTernakPresenter;

    RiwayatIbAdapter riwayatIbAdapter;
    RiwayatAmbAdapter riwayatAmbAdapter;

    ProgressDialog pDialog;

    boolean cek = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ternak);

        detailTernakPresenter = new DetailTernakImpl(this);

        String id_ternak = getIntent().getStringExtra("id_ternak");

        detailTernakPresenter.DetailTernak(id_ternak);

        imgTernak = findViewById(R.id.gambar_ternak);

        btnPlusTernak = findViewById(R.id.img_plus_data);
        btnMinTernak = findViewById(R.id.img_min_data);
        hdrTernak = findViewById(R.id.konten_1);
        kontenTernak = findViewById(R.id.konten_data_ternak);
        txtNamaTernak = findViewById(R.id.text_nama_ternak);
        txtTgllahirTernak = findViewById(R.id.text_tgl_lahir_ternak);
        txtKodeInduk = findViewById(R.id.text_kode_induk_ternak);
        txtKodePejantan = findViewById(R.id.text_kode_Pejantan_ternak);
        txtBangsa = findViewById(R.id.text_bangsa_ternak);
        txtJenisKelamin = findViewById(R.id.text_jenis_kelamin_ternak);
        txtNamaPetugas = findViewById(R.id.text_nama_petugas);
        txtNIP = findViewById(R.id.text_nip_petugas);

        btnPlusPemilik = findViewById(R.id.img_plus_data_pemilik);
        btnMinPemilik = findViewById(R.id.img_min_data_pemilik);
        hdrPemilik = findViewById(R.id.konten_2);
        kontenPemilik = findViewById(R.id.konten_data_pemilik);
        txtNamaPemilik = findViewById(R.id.text_nama_pemilik_ternak);
        txtNIK = findViewById(R.id.text_nik_pemilik);
        txtAlamatPemilik = findViewById(R.id.text_alamat_pemilik);

        btnPlusIB = findViewById(R.id.img_plus_data_ib);
        btnMinIB = findViewById(R.id.img_min_data_ib);
        hdrIB = findViewById(R.id.konten_3);
        kontenIB = findViewById(R.id.konten_data_ib);
        txtIbKosong = findViewById(R.id.text_info_ib_kosong);
        rvIB = findViewById(R.id.rec_riwayat_ib);

        btnPlusAmb = findViewById(R.id.img_plus_data_amb);
        btnMinAmb = findViewById(R.id.img_min_data_amb);
        hdrAmb = findViewById(R.id.konten_4);
        kontenAmb = findViewById(R.id.konten_data_ambulator);
        txtAmbKosong = findViewById(R.id.text_info_amb_kosong);
        rvAmb = findViewById(R.id.rec_riwayat_ambulator);

        btnPlusVer = findViewById(R.id.img_plus_verify);
        btnMinVer = findViewById(R.id.img_min_verify);
        hdrVer = findViewById(R.id.konten_5);
        kontenVer = findViewById(R.id.konten_data_verifikasi);
        imgVerWait = findViewById(R.id.img_ver_wait);
        txtVerMenunggu = findViewById(R.id.txt_menunggu);
        view1 = findViewById(R.id.view1);
        imgVerKonfirm = findViewById(R.id.img_ver_konfirm);
        txtVerDikonfim = findViewById(R.id.txt_konfirm);
        view2 = findViewById(R.id.view2);
        imgVerDate = findViewById(R.id.img_ver_tinjau);
        txtVerTinjau = findViewById(R.id.text_tinjau);
        txtStatusTernak = findViewById(R.id.text_status_peninjauan_ternak);
        txtTglPeninjauan = findViewById(R.id.text_tgl_peninjauan_ternak);

        cardPemilik = findViewById(R.id.card_data_pemilik);
        cardIB = findViewById(R.id.card_data_ib);
        cardAmb = findViewById(R.id.card_data_ambulator);
        cardVerifikasi = findViewById(R.id.card_data_verifikasi);
        cardTernak = findViewById(R.id.card_data_ternak);

        txtInfoTernak = findViewById(R.id.text_informasi_ternak);

        pDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Mohon Tunggu...");
        pDialog.setCancelable(false);
        pDialog.show();

        hdrTernak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek = !cek;

                if (cek){
                    btnMinTernak.setVisibility(View.VISIBLE);
                    kontenTernak.setVisibility(View.VISIBLE);
                    btnPlusTernak.setVisibility(View.GONE);
                } else if(!cek){
                    btnMinTernak.setVisibility(View.GONE);
                    kontenTernak.setVisibility(View.GONE);
                    btnPlusTernak.setVisibility(View.VISIBLE);
                }
            }
        });

        hdrPemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek = !cek;

                if (cek){
                    btnMinPemilik.setVisibility(View.VISIBLE);
                    kontenPemilik.setVisibility(View.VISIBLE);
                    btnPlusPemilik.setVisibility(View.GONE);
                } else if(!cek){
                    btnMinPemilik.setVisibility(View.GONE);
                    kontenPemilik.setVisibility(View.GONE);
                    btnPlusPemilik.setVisibility(View.VISIBLE);
                }
            }
        });

        hdrAmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek = !cek;

                if (cek){
                    btnMinAmb.setVisibility(View.VISIBLE);
                    kontenAmb.setVisibility(View.VISIBLE);
                    btnPlusAmb.setVisibility(View.GONE);
                } else if(!cek){
                    btnMinAmb.setVisibility(View.GONE);
                    kontenAmb.setVisibility(View.GONE);
                    btnPlusAmb.setVisibility(View.VISIBLE);
                }
            }
        });

        hdrIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek = !cek;

                if (cek){
                    btnMinIB.setVisibility(View.VISIBLE);
                    kontenIB.setVisibility(View.VISIBLE);
                    btnPlusIB.setVisibility(View.GONE);
                } else if(!cek){
                    btnMinIB.setVisibility(View.GONE);
                    kontenIB.setVisibility(View.GONE);
                    btnPlusIB.setVisibility(View.VISIBLE);
                }
            }
        });

        hdrVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek = !cek;

                if (cek){
                    btnMinVer.setVisibility(View.VISIBLE);
                    kontenVer.setVisibility(View.VISIBLE);
                    btnPlusVer.setVisibility(View.GONE);
                } else if(!cek){
                    btnMinVer.setVisibility(View.GONE);
                    kontenVer.setVisibility(View.GONE);
                    btnPlusVer.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void LoadData(List<DetailTernakResource> detailTernakResource) {
        pDialog.dismiss();

        cardTernak.setVisibility(View.VISIBLE);
        txtInfoTernak.setVisibility(View.VISIBLE);

        String status = detailTernakResource.get(0).getStatus();

        Glide.with(DetailTernakActivity.this)
                .load(BaseService.PATH_IMAGE_TERNAK+detailTernakResource.get(0).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgTernak);
        Log.d("dataaaaaaaaa", "gambar = "+detailTernakResource.get(0).getGambarDepan().toString());

        txtNamaTernak.setText(detailTernakResource.get(0).getNamaTernak());
        txtTgllahirTernak.setText(detailTernakResource.get(0).getTglLahir());
        txtKodeInduk.setText(detailTernakResource.get(0).getKodeInduk());
        txtKodePejantan.setText(detailTernakResource.get(0).getKodePejantan());
        txtBangsa.setText(detailTernakResource.get(0).getJenisTernak());
        txtJenisKelamin.setText(detailTernakResource.get(0).getJenisKelamin());

        txtNamaPemilik.setText(detailTernakResource.get(0).getNamaLengkap());
        txtNIK.setText(detailTernakResource.get(0).getNik());
        txtAlamatPemilik.setText(detailTernakResource.get(0).getAlamat()+" RT."+
                detailTernakResource.get(0).getRt()+" RW."+detailTernakResource.get(0).getRw());

        txtStatusTernak.setText(detailTernakResource.get(0).getStatus());
        txtTglPeninjauan.setText(detailTernakResource.get(0).getTglPeninjauan());
        txtNamaPetugas.setText(detailTernakResource.get(0).getNama().toString());
        txtNIP.setText(detailTernakResource.get(0).getNip().toString());

        if (status.equals("Belum Diverifikasi")){
            cardPemilik.setVisibility(View.GONE);
            cardIB.setVisibility(View.GONE);
            cardAmb.setVisibility(View.GONE);

            cardVerifikasi.setVisibility(View.VISIBLE);
        } else if (status.equals("Menunggu Peninjauan")){
            cardPemilik.setVisibility(View.GONE);
            cardIB.setVisibility(View.GONE);
            cardAmb.setVisibility(View.GONE);

            cardVerifikasi.setVisibility(View.VISIBLE);
            imgVerKonfirm.setImageResource(R.drawable.ver_konfirm_active);
            imgVerDate.setImageResource(R.drawable.ver_date_active);
            txtVerDikonfim.setTextColor(Color.parseColor("#C75F00"));
            txtVerTinjau.setTextColor(Color.parseColor("#C75F00"));
            view2.setBackgroundColor(Color.parseColor("#C75F00"));
            view1.setBackgroundColor(Color.parseColor("#C75F00"));
        } else if (status.equals("Diverifikasi")){
            cardPemilik.setVisibility(View.VISIBLE);
            cardIB.setVisibility(View.VISIBLE);
            cardAmb.setVisibility(View.VISIBLE);

            cardVerifikasi.setVisibility(View.VISIBLE);
            imgVerKonfirm.setImageResource(R.drawable.ver_konfirm_active);
            imgVerDate.setImageResource(R.drawable.ver_date_active);
            txtVerDikonfim.setTextColor(Color.parseColor("#C75F00"));
            txtVerTinjau.setTextColor(Color.parseColor("#C75F00"));
            view2.setBackgroundColor(Color.parseColor("#C75F00"));
            view1.setBackgroundColor(Color.parseColor("#C75F00"));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvIB.setLayoutManager(layoutManager);
        rvAmb.setLayoutManager(layoutManager2);


        riwayatIbAdapter = new RiwayatIbAdapter(detailTernakResource, this);
        riwayatAmbAdapter = new RiwayatAmbAdapter(detailTernakResource,this);
        rvIB.setAdapter(riwayatIbAdapter);
        rvAmb.setAdapter(riwayatAmbAdapter);
    }

    @Override
    public void DataNull() {
        pDialog.dismiss();
        onResume();
    }
}