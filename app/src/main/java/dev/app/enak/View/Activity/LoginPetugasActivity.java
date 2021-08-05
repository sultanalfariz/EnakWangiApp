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

import dev.app.enak.Implementation.LoginPetugasImpl;
import dev.app.enak.Implementation.LoginUserImpl;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.LoginPetugasPresenter;
import dev.app.enak.Presenter.LoginUserPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.LoginPetugasMvp;
import dev.app.enak.View.MvpView.LoginUserMvp;

public class LoginPetugasActivity extends AppCompatActivity implements LoginPetugasMvp {

    EditText editNIP, edtPassword;
    RelativeLayout btnMasuk;
    String nip, password;

    LoginPetugasPresenter loginPetugasPresenter;
    LoginPetugasMvp loginPetugasMvp;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_petugas);

        loginPetugasPresenter = new LoginPetugasImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);

        editNIP = findViewById(R.id.form_nip);
        edtPassword = findViewById(R.id.form_password);
        btnMasuk = findViewById(R.id.btn_masuk);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        nip = editNIP.getText().toString();
        password = edtPassword.getText().toString();

        if (nip.length() == 0 && password.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan NIP dan Password Anda",
                    Toast.LENGTH_LONG).show();
        } else if (nip.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan NIP Anda",
                    Toast.LENGTH_LONG).show();
        } else if(password.length() == 0){
            Toast.makeText(getApplicationContext(), "Masukkan Password Anda",
                    Toast.LENGTH_LONG).show();
        } else {
            pDialog = new ProgressDialog(LoginPetugasActivity.this, ProgressDialog.STYLE_SPINNER);
            pDialog.setIndeterminate(true);
            pDialog.setMessage("Mohon Tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();
            loginPetugasPresenter.Login(nip,password);
        }
    }

    @Override
    public void LoginSuccess(GetPetugasResource getPetugasResource) {
        pDialog.dismiss();
        Prefs.putPetugas(LoginPetugasActivity.this, Prefs.PETUGAS_SESSION, getPetugasResource);
        Prefs.putString(LoginPetugasActivity.this, Prefs.PETUGAS_PASSWORD, edtPassword.getText().toString());
        SharedPreferences.Editor data = sharedPreferences.edit();
        Log.d("dataaaaaaa", "isi "+ data.toString());
        data.putString(KEYNIP, nip);
        data.commit();

        Intent intent = new Intent(LoginPetugasActivity.this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginFail(String message) {
        pDialog.dismiss();

        if (message.equals("Password Anda Salah")){

            AlertDialog.Builder builder = new AlertDialog.Builder(LoginPetugasActivity.this);
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
        } else if (message.equals("Akun anda belum terdaftar")){
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginPetugasActivity.this);
            builder.setMessage("Akun anda belum terdaftar")
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