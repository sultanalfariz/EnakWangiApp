package dev.app.enak.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import dev.app.enak.Implementation.TanggapiLaporanImpl;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.TanggapiLaporanPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.TanggapiLaporanMvp;

public class TanggapiLaporanActivity extends AppCompatActivity implements TanggapiLaporanMvp {

    TextView txtKode, txtStatus, txtKet;
    EditText edtTgp, edtTglTinjau;
    RelativeLayout btnKirim;

    TanggapiLaporanPresenter tanggapiLaporanPresenter;

    GetPetugasResource getPetugasResource;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    String id_laporan, tglTinjau, tanggapan;
    Integer id_petugas;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggapi_laporan);

        tanggapiLaporanPresenter = new TanggapiLaporanImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getPetugasResource = Prefs.getPetugas(this,Prefs.PETUGAS_SESSION);

        id_laporan = getIntent().getStringExtra("id_laporan");
        id_petugas = getPetugasResource.getId();

        tanggapiLaporanPresenter.DetailLaporan(id_laporan);

        txtKode = findViewById(R.id.text_kode_anting_ternak);
        txtStatus = findViewById(R.id.text_status_laporan);
        txtKet = findViewById(R.id.text_ket_laporan);
        edtTgp = findViewById(R.id.form_keterangan);
        edtTglTinjau = findViewById(R.id.form_tgl_tinjau);
        btnKirim = findViewById(R.id.btn_kirim);

        edtTglTinjau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(TanggapiLaporanActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtTglTinjau.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tglTinjau = edtTglTinjau.getText().toString();
                tanggapan = edtTgp.getText().toString();

                if (tglTinjau.length() == 0 || tanggapan.length() == 0){
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi kolom yang tersedia",
                            Toast.LENGTH_LONG).show();
                } else{
                    pDialog = new ProgressDialog(TanggapiLaporanActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    tanggapiLaporanPresenter.tanggapiLaporan(id_laporan, id_petugas, tanggapan, tglTinjau);
                }
            }
        });
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(TanggapiLaporanActivity.this);
        builder.setMessage("Laporan berhasil ditanggapi")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TanggapiLaporanActivity.this, MainActivity2.class);
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
        txtKode.setText("#"+detailLaporanResources.get(0).getIdTernak().toString());
        txtStatus.setText(detailLaporanResources.get(0).getStatus().toString());
        txtKet.setText(detailLaporanResources.get(0).getKeterangan().toString());
    }

    @Override
    public void DataNull() {
        onResume();
    }
}