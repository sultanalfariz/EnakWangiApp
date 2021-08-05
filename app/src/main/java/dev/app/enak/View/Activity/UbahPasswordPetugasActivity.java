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

import dev.app.enak.Implementation.PengaturanAkunPetugasImpl;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.PengaturanAkunPetugasPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.PengaturanAkunPetugasMvp;

public class UbahPasswordPetugasActivity extends AppCompatActivity implements PengaturanAkunPetugasMvp {

    EditText edtPL, edtPB;
    RelativeLayout btnSimpan;

    String id, passwordLama, passwordBaru;

    PengaturanAkunPetugasPresenter pengaturanAkunPetugasPresenter;

    GetPetugasResource getPetugasResource;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password_petugas);

        pengaturanAkunPetugasPresenter = new PengaturanAkunPetugasImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getPetugasResource = Prefs.getPetugas(this,Prefs.PETUGAS_SESSION);

        id = getPetugasResource.getId().toString();

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
                    pDialog = new ProgressDialog(UbahPasswordPetugasActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    pengaturanAkunPetugasPresenter.ubahPassword(id, passwordLama, passwordBaru, getPetugasResource.getNip());
                }
            }
        });
    }

    @Override
    public void PostSuccess() {

    }

    @Override
    public void PostFail() {

    }

    @Override
    public void PostSuccessPass(GetPetugasResource getPetugasResource) {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(UbahPasswordPetugasActivity.this);
        builder.setMessage("Password berhasil diperbarui")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UbahPasswordPetugasActivity.this, MainActivity2.class);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(UbahPasswordPetugasActivity.this);
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
    public void Relogin(String nip, String password) {
        pengaturanAkunPetugasPresenter.Relogin(nip, password);
    }

    @Override
    public void LoadData(GetPetugasResource getPetugasResource) {

    }
}