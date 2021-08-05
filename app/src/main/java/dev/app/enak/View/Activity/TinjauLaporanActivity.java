package dev.app.enak.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.app.enak.Implementation.TinjauLaporanImpl;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.TinjauLaporanPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.TinjauLaporanMvp;

public class TinjauLaporanActivity extends AppCompatActivity implements TinjauLaporanMvp {

    TextView txtAlamatKdg, txtKdAnt, txtStLaporan, txtKetLapo, txtTgp, txtTglTinjau;
    EditText edtDiagnosa, edtDosis, edtObat;
    TextView edtGejala, edtMor, edtTindakan;
    RelativeLayout btnKirim;

    TinjauLaporanPresenter tinjauLaporanPresenter;

    GetPetugasResource getPetugasResource;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    String id_laporan, diagnosa, gejala, morb, tindakan, obat, dosis;
    Integer id_ternak, id_petugas;

    final String[] pilih = {"Ya", "Tidak"};
    final String[] mort = {"Rendah-Rendah", "Rendah-Tinggi", "Tinggi-Rendah", "Tinggi-Tinggi"};
    final String[] tind = {"Supportif", "Vaccin", "Antibiotik", "Hormonal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinjau_laporan);

        tinjauLaporanPresenter = new TinjauLaporanImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getPetugasResource = Prefs.getPetugas(this,Prefs.PETUGAS_SESSION);

        id_petugas = getPetugasResource.getId();

        id_laporan = getIntent().getStringExtra("id_laporan");
        tinjauLaporanPresenter.DetailLaporan(id_laporan);

        txtAlamatKdg = findViewById(R.id.text_alamat_kandang);
        txtKdAnt = findViewById(R.id.text_kode_anting_ternak);
        txtStLaporan = findViewById(R.id.text_status_laporan);
        txtKetLapo = findViewById(R.id.text_ket_laporan);
        txtTgp = findViewById(R.id.text_tanggapan_lapor);
        txtTglTinjau = findViewById(R.id.text_tgl_peninjauan);

        edtDiagnosa = findViewById(R.id.form_diagnosa);
        edtGejala = findViewById(R.id.form_gejala);
        edtMor = findViewById(R.id.form_morb_mort);
        edtTindakan = findViewById(R.id.form_tindakan);
        edtObat = findViewById(R.id.form_obat);
        edtDosis = findViewById(R.id.form_dosis);

        btnKirim = findViewById(R.id.btn_kirim);

        edtGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TinjauLaporanActivity.this);
                builder.setTitle("Apakah ternak mengalami gejala?")
                        .setItems(pilih, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtGejala.setText(pilih[which].toString());
                                gejala = pilih.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        edtMor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TinjauLaporanActivity.this);
                builder.setTitle("Pilih Morbiditas-Mortalitas ternak")
                        .setItems(mort, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtMor.setText(mort[which].toString());
                                morb = mort.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        edtTindakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TinjauLaporanActivity.this);
                builder.setTitle("Pilih tindakan yang akan diberikan")
                        .setItems(tind, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtTindakan.setText(tind[which].toString());
                                tindakan = tind.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagnosa = edtDiagnosa.getText().toString();
                gejala = edtGejala.getText().toString();
                morb = edtMor.getText().toString();
                tindakan = edtTindakan.getText().toString();
                obat = edtObat.getText().toString();
                dosis = edtDosis.getText().toString();

                if (diagnosa.length() == 0 || gejala.length() == 0 || morb.length() == 0 || tindakan.length() == 0 ||
                    obat.length() == 0 || dosis.length() == 0){
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi kolom yang tersedia",
                            Toast.LENGTH_LONG).show();
                } else {
                    pDialog = new ProgressDialog(TinjauLaporanActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    tinjauLaporanPresenter.diagnosaSakit(id_laporan, id_ternak, id_petugas, diagnosa, gejala, morb);
                    tinjauLaporanPresenter.tambahLayanan(id_laporan, id_ternak, id_petugas, tindakan, obat, dosis);
                }
            }
        });
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(TinjauLaporanActivity.this);
        builder.setMessage("Laporan telah ditinjau, data berhasil dikirimkan")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TinjauLaporanActivity.this, MainActivity2.class);
                        intent.putExtra("posisi","1");
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

    @Override
    public void LoadData(List<DetailLaporanResource> detailLaporanResources) {
        id_ternak = Integer.parseInt(detailLaporanResources.get(0).getIdTernak());
        txtAlamatKdg.setText(detailLaporanResources.get(0).getAlamatKandang()+" RT."+detailLaporanResources.get(0).getRt()+"/"+
                detailLaporanResources.get(0).getRw()+", "+detailLaporanResources.get(0).getKelKandang()+", "+
                detailLaporanResources.get(0).getKecKandang());
        txtKdAnt.setText("#"+detailLaporanResources.get(0).getIdTernak());
        txtStLaporan.setText(detailLaporanResources.get(0).getStatus());
        txtKetLapo.setText(detailLaporanResources.get(0).getKeterangan());
        txtTgp.setText(detailLaporanResources.get(0).getTanggapan().get(0).getKeterangan());
        txtTglTinjau.setText(detailLaporanResources.get(0).getTanggapan().get(0).getTglPeninjauan());
    }

    @Override
    public void DataNull() {
        onResume();
    }
}