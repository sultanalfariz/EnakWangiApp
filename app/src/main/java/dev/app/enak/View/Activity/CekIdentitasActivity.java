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
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;

import dev.app.enak.Implementation.CekIdImpl;
import dev.app.enak.Model.CekIdResource;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.CekIdPresenter;
import dev.app.enak.Presenter.LoginUserPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.CekIdMvp;
import dev.app.enak.View.MvpView.LoginUserMvp;

public class CekIdentitasActivity extends AppCompatActivity implements CekIdMvp {

    EditText edtNik, edtKK;
    RelativeLayout btnCek;

    String nik, tgl_lahir;

    private int mYear, mMonth, mDay;

    CekIdPresenter cekIdPresenter;
    CekIdMvp cekIdMvp;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_identitas);

        final Calendar myCalendar = Calendar.getInstance();

        cekIdPresenter = new CekIdImpl(this);

        edtNik = findViewById(R.id.form_nik);
        edtKK = findViewById(R.id.form_kk);
        btnCek = findViewById(R.id.btn_cek);

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek();
            }
        });

        edtKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(CekIdentitasActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtKK.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void cek(){
        nik = edtNik.getText().toString();
        tgl_lahir = edtKK.getText().toString();

        if (nik.length() == 0 && tgl_lahir.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan NIK dan No.KK Anda",
                    Toast.LENGTH_LONG).show();
        } else if (nik.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan NIK Anda",
                    Toast.LENGTH_LONG).show();
        } else if(tgl_lahir.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan No.KK Anda",
                    Toast.LENGTH_LONG).show();
        } else {
            pDialog = new ProgressDialog(CekIdentitasActivity.this, ProgressDialog.STYLE_SPINNER);
            pDialog.setIndeterminate(true);
            pDialog.setMessage("Mohon Tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();
            cekIdPresenter.CekIdentitas(nik,tgl_lahir);
        }
    }

    @Override
    public void CekSuccess(CekIdResource cekIdResource) {
        pDialog.dismiss();

        Intent intent = new Intent(CekIdentitasActivity.this, RegisterActivity.class);
        intent.putExtra("id", cekIdResource.getId().toString());
        intent.putExtra("nik", cekIdResource.getNik());
        intent.putExtra("nama", cekIdResource.getNamaLengkap());
        intent.putExtra("alamat", cekIdResource.getAlamat());
        intent.putExtra("rt", cekIdResource.getRt());
        intent.putExtra("rw", cekIdResource.getRw());
        intent.putExtra("kelurahan", cekIdResource.getKelurahan());
        intent.putExtra("kecamatan", cekIdResource.getKecamatan());
        startActivity(intent);
    }

    @Override
    public void CekFail(String message) {
        pDialog.dismiss();

        if (message.equals("Akun telah terdaftar.")){
            AlertDialog.Builder builder = new AlertDialog.Builder(CekIdentitasActivity.this);
            builder.setMessage("Akun telah terdaftar, silahkan mencoba untuk masuk kembali")
                    .setTitle("Perhatian")
                    .setCancelable(false)
                    .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        } else if(message.equals("Tanggal lahir yang anda masukkan salah.")){
            AlertDialog.Builder builder = new AlertDialog.Builder(CekIdentitasActivity.this);
            builder.setMessage("Tanggal lahir yang anda masukkan salah.")
                    .setTitle("Perhatian")
                    .setCancelable(false)
                    .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        } else if(message.equals("NIK tidak terdaftar. Silahkan periksa terlebih dahulu.")){
            AlertDialog.Builder builder = new AlertDialog.Builder(CekIdentitasActivity.this);
            builder.setMessage("NIK tidak terdaftar. Silahkan periksa terlebih dahulu.")
                    .setTitle("Perhatian")
                    .setCancelable(false)
                    .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }
    }
}