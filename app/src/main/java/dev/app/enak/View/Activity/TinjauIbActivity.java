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

import dev.app.enak.Implementation.TinjauIbImpl;
import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.TinjauIbPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.TinjauIbMvp;

public class TinjauIbActivity extends AppCompatActivity implements TinjauIbMvp {

    TextView txtAlamatKdg, txtKdAnt, txtNama, txtSt, txtAlasan, txtTgp, txtCek;
    TextView edtBirahi;
    EditText edtTglSuntik;
    RelativeLayout btnKirim;

    String birahi, tglSuntik, id_permintaan_ib;
    Integer id_petugas;
    private int mYear, mMonth, mDay;

    TinjauIbPresenter tinjauIbPresenter;

    GetPetugasResource getPetugasResource;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    final String[] pilih = {"Ya", "Tidak"};

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinjau_ib);

        tinjauIbPresenter = new TinjauIbImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getPetugasResource = Prefs.getPetugas(this,Prefs.PETUGAS_SESSION);

        id_permintaan_ib = getIntent().getStringExtra("id_permintaan_ib");
        id_petugas = getPetugasResource.getId();

        tinjauIbPresenter.DetailIb(id_permintaan_ib);

        txtAlamatKdg = findViewById(R.id.text_alamat_kandang);
        txtKdAnt = findViewById(R.id.text_kode_anting_ternak);
        txtNama = findViewById(R.id.text_nama_ternak);
        txtSt = findViewById(R.id.text_status_laporan);
        txtAlasan = findViewById(R.id.text_ket_laporan);
        txtTgp = findViewById(R.id.text_tanggapan_lapor);
        txtCek = findViewById(R.id.text_tgl_peninjauan);

        edtBirahi = findViewById(R.id.form_birahi);
        edtTglSuntik = findViewById(R.id.form_tgl_tinjau);

        btnKirim = findViewById(R.id.btn_kirim);

        edtBirahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TinjauIbActivity.this);
                builder.setTitle("Apakah hewan ternak mangalami birahi ?")
                        .setItems(pilih, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtBirahi.setText(pilih[which].toString());
                                birahi = pilih.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        edtTglSuntik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(TinjauIbActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtTglSuntik.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birahi = edtBirahi.getText().toString();
                tglSuntik = edtTglSuntik.getText().toString();

                if ((birahi.equals("Ya")) & (tglSuntik.length() == 0)){
                    Toast.makeText(getApplicationContext(), "Mohon isi data tanggal suntik ib",
                            Toast.LENGTH_LONG).show();
                } else if (birahi.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Mohon isi data birahi ternak",
                            Toast.LENGTH_LONG).show();
                } else{
                    pDialog = new ProgressDialog(TinjauIbActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    tinjauIbPresenter.tanggapiIb(id_permintaan_ib, id_petugas, birahi, tglSuntik);
                }
            }
        });
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(TinjauIbActivity.this);
        builder.setMessage("Permintaan IB berhasil ditinjau")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TinjauIbActivity.this, MainActivity2.class);
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
        txtAlamatKdg.setText(detailIbResource.get(0).getAlamatKandang()+" RT."+detailIbResource.get(0).getRt()+"/"+
                detailIbResource.get(0).getRw()+", "+detailIbResource.get(0).getKelKandang()+", "+
                detailIbResource.get(0).getKecKandang());
        txtKdAnt.setText(detailIbResource.get(0).getIdTernak().toString());
        txtNama.setText(detailIbResource.get(0).getNamaTernak());
        txtAlasan.setText(detailIbResource.get(0).getKeterangan());
        txtSt.setText(detailIbResource.get(0).getStatus());
        txtTgp.setText(detailIbResource.get(0).getTanggapan().get(0).getKeterangan());
        txtCek.setText(detailIbResource.get(0).getTanggapan().get(0).getTglPeninjauan());
    }

    @Override
    public void DataNull() {
        onResume();
    }
}