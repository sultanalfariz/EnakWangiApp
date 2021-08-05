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
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.app.enak.Implementation.PengaturanAkunUserImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.PengaturanAkunUserPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.PengaturanAkunUserMvp;

public class UbahPasswordUserActivity extends AppCompatActivity implements PengaturanAkunUserMvp {

    EditText edtPL, edtPB;
    RelativeLayout btnSimpan;

    GetUserResource getUserResource;
    PengaturanAkunUserPresenter pengaturanAkunUserPresenter;

    String id, passwordLama, passwordBaru;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password_user);

        pengaturanAkunUserPresenter = new PengaturanAkunUserImpl(this);

        sharedPreferences = this.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(this, Prefs.USER_SESSION);

        id = getUserResource.getId().toString();

        edtPL = findViewById(R.id.form_pass_lama);
        edtPB = findViewById(R.id.form_pass_baru);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordLama = edtPL.getText().toString();
                passwordBaru = edtPB.getText().toString();

                if (passwordLama.length() == 0 || passwordBaru.length() == 0){
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (passwordBaru.length() < 7){
                    Toast.makeText(getApplicationContext(), "Panjang password minimal 8 karakter",
                            Toast.LENGTH_LONG).show();
                }else {
                    pDialog = new ProgressDialog(UbahPasswordUserActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    pengaturanAkunUserPresenter.ubahPassword(id, passwordLama, passwordBaru, getUserResource.getNik());
                }
            }
        });
    }

    @Override
    public void PostSuccessUpload() {

    }

    @Override
    public void PostSuccess() {

    }

    @Override
    public void PostSuccessPass(GetUserResource getUserResource) {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(UbahPasswordUserActivity.this);
        builder.setMessage("Password berhasil diperbarui")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UbahPasswordUserActivity.this, MainActivity.class);
                        intent.putExtra("posisi", "3");
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void PostFailPass(String message) {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(UbahPasswordUserActivity.this);
        builder.setMessage("Periksa password lama anda")
                .setTitle("Gagal")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void PostFail() {

    }

    @Override
    public void Relogin(String nik, String password) {
        pengaturanAkunUserPresenter.Relogin(nik, password);
    }

    @Override
    public void LoadData(GetUserResource getUserResource) {

    }
}