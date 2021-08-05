package dev.app.enak.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import dev.app.enak.Implementation.AddPasswordImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.AddPasswordPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.AddPasswordMvp;

public class RegisterActivity extends AppCompatActivity implements AddPasswordMvp {

    EditText edtNIK, edtNama, edtAlamat, edtKelurahan, edtKecamatan,
            edtPassword;

    RelativeLayout btnDaftar;

    AddPasswordPresenter addPasswordPresenter;

    ProgressDialog pDialog;

    String id, nama, nik, alamat, rt, rw, kecamatan, kelurahan, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addPasswordPresenter = new AddPasswordImpl(this);

        id = getIntent().getStringExtra("id");
        Log.d("dataaaaaa", "id "+id);
        nik = getIntent().getStringExtra("nik");
        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        rt = getIntent().getStringExtra("rt");
        rw = getIntent().getStringExtra("rw");
        kelurahan = getIntent().getStringExtra("kelurahan");
        kecamatan = getIntent().getStringExtra("kecamatan");

        edtNIK = findViewById(R.id.form_nik);
        edtNama = findViewById(R.id.form_namaLengkap);
        edtAlamat = findViewById(R.id.form_alamat);
        edtKelurahan = findViewById(R.id.form_kelurahan);
        edtKecamatan = findViewById(R.id.form_kecamatan);
        edtPassword = findViewById(R.id.form_password);

        edtNIK.setText(nik);
        edtNama.setText(nama);
        edtAlamat.setText(alamat+" RT."+rt+" RW."+rw);
        edtKelurahan.setText(kelurahan);
        edtKecamatan.setText(kecamatan);

        btnDaftar = findViewById(R.id.btn_masuk);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPassword();
            }
        });
    }

    private void addPassword() {

        password = edtPassword.getText().toString();

        if (password.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan Password",
                    Toast.LENGTH_LONG).show();
        } else if (password.length() < 7){
            Toast.makeText(getApplicationContext(), "Password minimum 8 karakter",
                    Toast.LENGTH_LONG).show();
        } else {
            pDialog = new ProgressDialog(RegisterActivity.this, ProgressDialog.STYLE_SPINNER);
            pDialog.setIndeterminate(true);
            pDialog.setMessage("Mohon Tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();
            Log.d("dataaaaaaaa", "data = "+id+password);
            addPasswordPresenter.AddPassword(getIntent().getStringExtra("id"), password);
        }
    }

    @Override
    public void AddPasswordSuccess(GetUserResource getUserResource) {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setMessage("Akun telah terdaftar, silahkan masuk dengan menggunakan NIK dan Password.")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RegisterActivity.this, LoginPemilikActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }
}