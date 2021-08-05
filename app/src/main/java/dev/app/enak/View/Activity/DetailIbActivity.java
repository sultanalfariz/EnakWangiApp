package dev.app.enak.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import dev.app.enak.Implementation.DetailIbPemilikImpl;
import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Presenter.DetailIbPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.DetailIbMvp;

public class DetailIbActivity extends AppCompatActivity implements DetailIbMvp {

    TextView txtKdAnting, txtStatus, txtKet, txtTgp, txtTglCb, txtTglSib, txtNamaPetugas, txtNIP,
     edtBirahi, edtHamil,edtLahir;

    EditText edtTglLahir;

    ImageView btnBA, btnBD, btnHA, btnHD, btnLA, btnLD, btnTLA, btnTLD;

    DetailIbPresenter detailIbPresenter;

    final String[] pilih = {"Ya", "Tidak"};

    String birahi, lahir, hamil, id_permintaan_ib;
    private int mYear, mMonth, mDay;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ib);

        detailIbPresenter = new DetailIbPemilikImpl(this);

        id_permintaan_ib = getIntent().getStringExtra("id");
        Log.d("dataaaaaaaa", "data inten id ="+id_permintaan_ib);

        detailIbPresenter.DetailIb(id_permintaan_ib);

        txtKdAnting = findViewById(R.id.text_kode_anting);
        txtStatus = findViewById(R.id.text_status_ib);
        txtKet = findViewById(R.id.text_keterangan);
        txtTgp = findViewById(R.id.text_tanggapan_ib);
        txtTglCb = findViewById(R.id.text_tgl_cek_birahi);
        txtTglSib = findViewById(R.id.text_tgl_suntik_ib);
        txtNamaPetugas = findViewById(R.id.text_nama_petugas);
        txtNIP = findViewById(R.id.text_nip_petugas);

        edtBirahi = findViewById(R.id.form_birahi);
        edtHamil = findViewById(R.id.form_hamil);
        edtLahir = findViewById(R.id.form_lahir);
        edtTglLahir = findViewById(R.id.form_tgl_lahir);

        btnBA = findViewById(R.id.send_birahi_active);
        btnBD = findViewById(R.id.send_birahi_deactive);
        btnHA = findViewById(R.id.send_hamil_active);
        btnHD = findViewById(R.id.send_hamil_deactive);
        btnLA = findViewById(R.id.send_lahir_active);
        btnLD = findViewById(R.id.send_lahir_deactive);
        btnTLA = findViewById(R.id.send_tgl_active);
        btnTLD = findViewById(R.id.send_tgl_deactive);

        edtBirahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailIbActivity.this);
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

        edtLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailIbActivity.this);
                builder.setTitle("Apakah hewan ternak berhasil melahirkan ?")
                        .setItems(pilih, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtLahir.setText(pilih[which].toString());
                                lahir = pilih.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        edtHamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailIbActivity.this);
                builder.setTitle("Apakah hewan ternak berhasil hamil ?")
                        .setItems(pilih, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtHamil.setText(pilih[which].toString());
                                hamil = pilih.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtHamil.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(), "Mohon isi status hamil IB",
                            Toast.LENGTH_LONG).show();
                }else {
                    detailIbPresenter.ubahStatusIbHamil(id_permintaan_ib, edtHamil.getText().toString());
                    Log.d("dataaaaaaa", "data ib = "+id_permintaan_ib+hamil);
                    pDialog = new ProgressDialog(DetailIbActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                }
            }
        });

        btnLA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtLahir.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(), "Mohon isi status lahir IB",
                            Toast.LENGTH_LONG).show();
                }else {
                    detailIbPresenter.ubahStatusIbLahir(id_permintaan_ib, edtLahir.getText().toString());
                    Log.d("dataaaaaaa", "data ib = " + id_permintaan_ib + edtHamil.getText().toString());
                    pDialog = new ProgressDialog(DetailIbActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                }
            }
        });

        edtTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailIbActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtTglLahir.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnTLA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTglLahir.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(), "Mohon isi tanggal lahir",
                            Toast.LENGTH_LONG).show();
                }else {
                    detailIbPresenter.inputTglLahir(id_permintaan_ib, edtTglLahir.getText().toString());
                    pDialog = new ProgressDialog(DetailIbActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                }
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
        txtNamaPetugas.setText(detailIbResources.get(0).getNama().toString());
        txtNIP.setText(detailIbResources.get(0).getNip().toString());

        String birahi = detailIbResources.get(0).getIb().get(0).getBirahi();
        String lahir  = detailIbResources.get(0).getIb().get(0).getLahir();
        String hamil = detailIbResources.get(0).getIb().get(0).getHamil();
        String status = detailIbResources.get(0).getStatus().toString();

        Log.d("dataaaaaaaaaa", "birahi: "+birahi);
        Log.d("dataaaaaaaaaa", "hamil: "+hamil);
        Log.d("dataaaaaaaaaa", "lahir: "+lahir);
        Log.d("dataaaaaaaaaa", "status: "+status);

        if (birahi.equals("Ya") && hamil.equals("-") && lahir.equals("-")){
            btnBA.setVisibility(View.GONE);
            btnBD.setVisibility(View.VISIBLE);
            btnHA.setVisibility(View.VISIBLE);
            btnHD.setVisibility(View.GONE);
            btnLA.setVisibility(View.GONE);
            btnLD.setVisibility(View.VISIBLE);
            btnTLA.setVisibility(View.GONE);
            btnTLD.setVisibility(View.VISIBLE);
        } if (birahi.equals("Ya") && hamil.equals("Ya") && lahir.equals("-")){
            btnBA.setVisibility(View.GONE);
            btnBD.setVisibility(View.VISIBLE);
            btnHA.setVisibility(View.GONE);
            btnHD.setVisibility(View.VISIBLE);
            btnLA.setVisibility(View.VISIBLE);
            btnLD.setVisibility(View.GONE);
            btnTLA.setVisibility(View.GONE);
            btnTLD.setVisibility(View.VISIBLE);
        } if (birahi.equals("Ya") && hamil.equals("Ya") && lahir.equals("Ya")){
            btnBA.setVisibility(View.GONE);
            btnBD.setVisibility(View.VISIBLE);
            btnHA.setVisibility(View.GONE);
            btnHD.setVisibility(View.VISIBLE);
            btnLA.setVisibility(View.GONE);
            btnLD.setVisibility(View.VISIBLE);
            btnTLA.setVisibility(View.VISIBLE);
            btnTLD.setVisibility(View.GONE);
        } if (status.equals("Selesai")){
            btnLA.setVisibility(View.GONE);
            btnHA.setVisibility(View.GONE);
            btnBA.setVisibility(View.GONE);
            btnTLA.setVisibility(View.GONE);
            btnTLD.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void DataNull() {
        onResume();
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailIbActivity.this);
        builder.setMessage("Data hasil IB berhasil dikirim")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                })
                .show();
    }

    @Override
    public void PostFail() {
        pDialog.dismiss();
    }

    @Override
    public void PostTglSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailIbActivity.this);
        builder.setMessage("Tracking IB selesai.")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DetailIbActivity.this, MainActivity.class);
                        intent.putExtra("posisi", "2");
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }
}