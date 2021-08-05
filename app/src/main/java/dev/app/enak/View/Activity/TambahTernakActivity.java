package dev.app.enak.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.app.enak.Implementation.TambahTernakImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.UriImageSource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.TambahTernakPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.TambahTernakMvp;
import id.zelory.compressor.Compressor;

public class TambahTernakActivity extends AppCompatActivity implements TambahTernakMvp {

    EditText edtNamaTernak, edtTglLahir, edtKodeInduk, edtKodePejantan, edtAlamatKandang, edtKec, edtKel, edtRt, edtRw;
    TextView txtBangsa, txtJenisKelamin;
    ImageView addImage, imageView;
    RelativeLayout btnTambahkan;

    private File fileContentToUpload, fileContentToUploads;
    InputStream inputStream;
    OutputStream output, outputs;
    String UriImage;
    Uri GlobalUri;
    List<UriImageSource> uriImageSources = new ArrayList<>();

    final String[] gender = {"Jantan", "Betina"};
    final String[] bangsa = {"Limosin", "Perah"};

    private int mYear, mMonth, mDay;

    String namaTernak, tglLahir, kodeInduk, kodePejantan, bangsaTer, jk, alamat, kec, kel, rt, rw, id_pemilik;

    TambahTernakPresenter tambahTernakPresenter;

    GetUserResource getUserResource;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ternak);

        tambahTernakPresenter = new TambahTernakImpl(this);

        sharedPreferences = this.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(this, Prefs.USER_SESSION);

        id_pemilik = getUserResource.getId().toString();

        edtNamaTernak = findViewById(R.id.form_nama_ternak);
        edtTglLahir = findViewById(R.id.form_tgl_lahir_ternak);
        edtKodeInduk = findViewById(R.id.form_kode_induk);
        edtKodePejantan = findViewById(R.id.form_kode_pejantan);
        txtBangsa = findViewById(R.id.form_bangsa);
        txtJenisKelamin = findViewById(R.id.form_jenis_kelamin);
        edtAlamatKandang = findViewById(R.id.form_alamat_kandang);
        edtKel = findViewById(R.id.form_kelurahan);
        edtKec = findViewById(R.id.form_kecamatan);
        edtRt = findViewById(R.id.form_rt);
        edtRw = findViewById(R.id.form_rw);

        addImage = findViewById(R.id.img_masukan_foto_depan);
        imageView = findViewById(R.id.img_layout_foto_depan);

        btnTambahkan = findViewById(R.id.btn_tambahkan);

        txtJenisKelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahTernakActivity.this);
                builder.setTitle("Pilih Jenis Kelamin Ternak")
                        .setItems(gender, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtJenisKelamin.setText(gender[which].toString());
                                jk = gender.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        txtBangsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahTernakActivity.this);
                builder.setTitle("Pilih Bangsa Ternak")
                        .setItems(bangsa, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtBangsa.setText(bangsa[which].toString());
                                bangsaTer = bangsa.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(TambahTernakActivity.this,
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

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(TambahTernakActivity.this);
            }
        });

        btnTambahkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                namaTernak = edtNamaTernak.getText().toString();
                kodeInduk = edtKodeInduk.getText().toString();
                kodePejantan = edtKodePejantan.getText().toString();
                tglLahir = edtTglLahir.getText().toString();
                alamat = edtAlamatKandang.getText().toString();
                kel = edtKel.getText().toString();
                kec = edtKec.getText().toString();
                rt = edtRt.getText().toString();
                rw = edtRw.getText().toString();

                if (tglLahir.length() == 0 || alamat.length() == 0 || kel.length() == 0 || kec.length() == 0 || rt.length() == 0 ||
                    rw.length() == 0 || txtBangsa.getText().toString().length() == 0 || txtJenisKelamin.getText().toString().length() == 0
                    || namaTernak.length() == 0){
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi formulir tambah ternak anda",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {

                        if (kodePejantan.length() == 0){
                            kodePejantan = "-";
                            if (kodeInduk.length() == 0){
                                kodeInduk = "-";
                            }
                        } else if (kodeInduk.length() == 0){
                            kodeInduk = "-";
                            if (kodePejantan.length() == 0){
                                kodePejantan = "-";
                            }
                        }else {
                            kodeInduk = edtKodeInduk.getText().toString();
                            kodePejantan = edtKodePejantan.getText().toString();
                        }

                        inputStream = getBaseContext().getContentResolver().openInputStream(uriImageSources.get(0).getUriImage());
                        Log.d("dataaaaaaa","input stream"+inputStream);
                        Log.d("dataaaaaaa","data uri = "+uriImageSources.get(0).getUriImage());
                        try {
                            fileContentToUpload = new File(getBaseContext().getCacheDir(), "cacheFileAppeal.jpg");
                            Log.d("dataaaaaaa","data file"+fileContentToUpload);
                            output = new FileOutputStream(fileContentToUpload);
                            try {
                                byte[] buffer = new byte[4 * 1024]; // or other buffer size
                                int read;

                                while ((read = inputStream.read(buffer)) != -1) {
                                    output.write(buffer, 0, read);
                                }

                                output.flush();
                            } finally {
                                output.close();
                            }
                        } finally {
                            inputStream.close();
                            fileContentToUpload = new Compressor(TambahTernakActivity.this).compressToFile(fileContentToUpload);

                            pDialog = new ProgressDialog(TambahTernakActivity.this, ProgressDialog.STYLE_SPINNER);
                            pDialog.setIndeterminate(true);
                            pDialog.setMessage("Mohon Tunggu...");
                            pDialog.setCancelable(false);
                            pDialog.show();

                            Log.d("dataaaaaaa", "data semua "+alamat+" "+kel+" "+kec+" "+txtBangsa.getText().toString()+" "+fileContentToUpload);

                            tambahTernakPresenter.TambahTernak(Integer.parseInt(id_pemilik), namaTernak, txtBangsa.getText().toString(),
                                    txtJenisKelamin.getText().toString(), kodeInduk, kodePejantan, alamat, kel, kec, rt, rw, tglLahir, fileContentToUpload);

                        }
                    }
                    catch (IOException e){
                        try {

                        }
                        catch (NullPointerException ignored){

                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("dataaaaaaaa","masuk result");
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK){
            Uri imagePick = CropImage.getPickImageResultUri(this,data);
            CropImage.activity(imagePick)
                    .start(this);
        }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri imageUri = result.getUri();
            UriImage = imageUri.toString();
            uriImageSources.add(new UriImageSource(UriImage,imageUri));
            imageView.setImageURI(Uri.parse(uriImageSources.get(0).getImage_uri().toString()));
            imageView.setVisibility(View.VISIBLE);
            Log.d("dataaaaaaaaaaa uri","data uri = "+UriImage);
            Log.d("dataaaaa uri dalam","data uri dalam = "+uriImageSources.get(0).getImage_uri().toString());
        }
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(TambahTernakActivity.this);
        builder.setMessage("Ternak anda berhasil ditambahkan, mohon tunggu petugas kami untuk melakukan verifikasi dan peninjauan.")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TambahTernakActivity.this, MainActivity.class);
                        intent.putExtra("posisi", "0");
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
}