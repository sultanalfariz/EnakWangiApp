package dev.app.enak.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.app.enak.Implementation.LoginUserImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.LoginUserPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.LoginUserMvp;

public class LoginPemilikActivity extends AppCompatActivity implements LoginUserMvp {

    EditText editNIK, edtPassword;
    RelativeLayout btnMasuk, btnDaftar;
    String nik, password;

    LoginUserPresenter loginUserPresenter;
    LoginUserMvp loginUserMvp;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pemilik);

        loginUserPresenter = new LoginUserImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);

        editNIK = findViewById(R.id.form_nik);
        edtPassword = findViewById(R.id.form_password);
        btnMasuk = findViewById(R.id.btn_masuk);
        btnDaftar = findViewById(R.id.btn_daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPemilikActivity.this, CekIdentitasActivity.class);
                startActivity(intent);
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        nik = editNIK.getText().toString();
        password = edtPassword.getText().toString();

        if (nik.length() == 0 && password.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan NIK dan Password Anda",
                    Toast.LENGTH_LONG).show();
        } else if (nik.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan NIK Anda",
                    Toast.LENGTH_LONG).show();
        } else if(password.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan Password Anda",
                    Toast.LENGTH_LONG).show();
        } else {
            pDialog = new ProgressDialog(LoginPemilikActivity.this, ProgressDialog.STYLE_SPINNER);
            pDialog.setIndeterminate(true);
            pDialog.setMessage("Mohon Tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();
            loginUserPresenter.Login(nik,password);
        }
    }

    @Override
    public void LoginSuccess(GetUserResource getUserResource) {
        pDialog.dismiss();
        Prefs.putUser(LoginPemilikActivity.this, Prefs.USER_SESSION, getUserResource);
        Prefs.putString(LoginPemilikActivity.this, Prefs.USER_PASSWORD, edtPassword.getText().toString());
        SharedPreferences.Editor data = sharedPreferences.edit();
        Log.d("dataaaaaaa", "isi "+ data.toString());
        data.putString(KEYNIK, nik);
        data.commit();

        Intent intent = new Intent(LoginPemilikActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginFail(String message) {
        pDialog.dismiss();

        if (message.equals("Password Anda Salah")){

            AlertDialog.Builder builder = new AlertDialog.Builder(LoginPemilikActivity.this);
            builder.setMessage("Password yang anda masukkan salah")
                .setTitle("Perhatian")
                .setCancelable(false)
                .setPositiveButton("Coba lagi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
        } else if (message.equals("Akun anda belum terdaftar. Silahkan daftar terlebih dahulu.")){
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginPemilikActivity.this);
            builder.setMessage("Akun anda belum terdaftar, silahkan daftar terlebih dahulu.")
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