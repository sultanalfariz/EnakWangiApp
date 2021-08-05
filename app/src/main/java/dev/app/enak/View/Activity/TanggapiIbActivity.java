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

import dev.app.enak.Implementation.TanggapiIbImpl;
import dev.app.enak.Implementation.TanggapiLaporanImpl;
import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.TanggapiIbPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.TanggapiIbMvp;

public class TanggapiIbActivity extends AppCompatActivity implements TanggapiIbMvp {

    TextView txtKode, txtNama, txtSt, txtKet;
    EditText edtTgp, edtTgl;
    RelativeLayout btnKirim;

    TanggapiIbPresenter tanggapiIbPresenter;

    GetPetugasResource getPetugasResource;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    String id_permintaan_ib, tglTinjau, tanggapan;
    Integer id_petugas;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggapi_ib);

        tanggapiIbPresenter = new TanggapiIbImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getPetugasResource = Prefs.getPetugas(this,Prefs.PETUGAS_SESSION);

        id_permintaan_ib = getIntent().getStringExtra("id_permintaan_ib");
        id_petugas = getPetugasResource.getId();

        tanggapiIbPresenter.DetailIb(id_permintaan_ib);

        txtKode = findViewById(R.id.text_kode_anting_ternak);
        txtSt = findViewById(R.id.text_status_laporan);
        txtKet = findViewById(R.id.text_ket_laporan);
        edtTgp = findViewById(R.id.form_keterangan);
        edtTgl = findViewById(R.id.form_tgl_tinjau);
        txtNama = findViewById(R.id.text_nama_ternak);
        btnKirim = findViewById(R.id.btn_kirim);

        edtTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(TanggapiIbActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtTgl.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tglTinjau = edtTgl.getText().toString();
                tanggapan = edtTgp.getText().toString();

                if (tglTinjau.length() == 0 || tanggapan.length() == 0){
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi kolom yang tersedia",
                            Toast.LENGTH_LONG).show();
                } else{
                    pDialog = new ProgressDialog(TanggapiIbActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    tanggapiIbPresenter.tanggapiIb(id_permintaan_ib, id_petugas, tanggapan, tglTinjau);
                }
            }
        });
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(TanggapiIbActivity.this);
        builder.setMessage("Permintaan IB berhasil ditanggapi")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TanggapiIbActivity.this, MainActivity2.class);
                        intent.putExtra("posisi","2");
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
    public void LoadData(List<DetailIbResource> detailIbResource) {
        txtKode.setText("#"+detailIbResource.get(0).getIdTernak().toString());
        txtSt.setText(detailIbResource.get(0).getStatus().toString());
        txtKet.setText(detailIbResource.get(0).getKeterangan().toString());
        txtNama.setText(detailIbResource.get(0).getNamaTernak().toString());
    }

    @Override
    public void DataNull() {
        onResume();
    }
}