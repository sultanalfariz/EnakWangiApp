package dev.app.enak.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Implementation.VerifikasiTernakImpl;
import dev.app.enak.Model.DetailTernakResource;
import dev.app.enak.Model.UriImageSource;
import dev.app.enak.Presenter.VerifikasiTernakPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.VerifikasiTernakMvp;
import id.zelory.compressor.Compressor;

public class VerifikasiTernakActivity extends AppCompatActivity implements VerifikasiTernakMvp {

    RoundedImageView imgTernak;
    EditText edtNama, edtKdInduk, edtKdPj, edtTglLahir;
    TextView edtbangsa, edtJk, txtAlamatKdg;
    RelativeLayout btnVerifikasi;

    VerifikasiTernakPresenter verifikasiTernakPresenter;

    String nama, jenister, jeniskel, kinduk, kpejantan, tglLahir, id_ternak;
    private int mYear, mMonth, mDay;

    final String[] gender = {"Jantan", "Betina"};
    final String[] bangsa = {"Limosin", "Perah"};

    private File fileContentToUpload, fileContentToUploads;
    InputStream inputStream;
    OutputStream output, outputs;
    String UriImage;
    Uri GlobalUri;
    List<UriImageSource> uriImageSources = new ArrayList<>();

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_ternak);

        verifikasiTernakPresenter = new VerifikasiTernakImpl(this);

        id_ternak = getIntent().getStringExtra("id_ternak");

        verifikasiTernakPresenter.detailTernak(id_ternak);

        edtNama = findViewById(R.id.form_nama_ternak);
        edtTglLahir = findViewById(R.id.form_tgl_lahir_ternak);
        edtKdInduk = findViewById(R.id.form_kode_induk);
        edtKdPj = findViewById(R.id.form_kode_pejantan);
        edtbangsa = findViewById(R.id.form_bangsa);
        edtJk = findViewById(R.id.form_jenis_kelamin);
        txtAlamatKdg = findViewById(R.id.text_alamat_kandang);
        imgTernak = findViewById(R.id.img_layout_foto_depan);
        btnVerifikasi = findViewById(R.id.btn_verifikasi);

        imgTernak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(VerifikasiTernakActivity.this);
            }
        });

        edtJk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerifikasiTernakActivity.this);
                builder.setTitle("Pilih Jenis Kelamin Ternak")
                        .setItems(gender, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtJk.setText(gender[which].toString());
                                jeniskel = gender.toString();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        edtbangsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerifikasiTernakActivity.this);
                builder.setTitle("Pilih Bangsa Ternak")
                        .setItems(bangsa, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtbangsa.setText(bangsa[which].toString());
                                jenister = bangsa.toString();
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(VerifikasiTernakActivity.this,
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

        btnVerifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = edtNama.getText().toString();
                kinduk = edtKdInduk.getText().toString();
                kpejantan = edtKdPj.getText().toString();
                tglLahir = edtTglLahir.getText().toString();

                if (tglLahir.length() == 0 || edtbangsa.getText().toString().length() == 0 || edtbangsa.getText().toString().length() == 0
                    || nama.length() == 0){
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi formulir tambah ternak anda",
                            Toast.LENGTH_LONG).show();
                } else {
                    pDialog = new ProgressDialog(VerifikasiTernakActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    verifikasiTernakPresenter.verifyTernak(id_ternak, nama, edtbangsa.getText().toString(), edtJk.getText().toString(),
                            kinduk, kpejantan, tglLahir);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK){
            Uri imagePick = CropImage.getPickImageResultUri(this,data);
            CropImage.activity(imagePick)
                    .start(this);
        }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri imageUri = result.getUri();
            UriImage = imageUri.toString();
            uriImageSources.add(new UriImageSource(UriImage,imageUri));
            imgTernak.setImageURI(Uri.parse(uriImageSources.get(0).getImage_uri().toString()));
            Log.d("dataaaaaaaaaaa uri","data uri = "+UriImage);
            Log.d("dataaaaa uri dalam","data uri dalam = "+uriImageSources.get(0).getImage_uri().toString());
            this.GlobalUri = imageUri;
            //upload
            try {

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
                    fileContentToUpload = new Compressor(VerifikasiTernakActivity.this).compressToFile(fileContentToUpload);
                    verifikasiTernakPresenter.changeImg(Integer.parseInt(id_ternak), fileContentToUpload);

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

    @Override
    public void VerifySuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(VerifikasiTernakActivity.this);
        builder.setMessage("Ternak berhasil diverifikasi")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(VerifikasiTernakActivity.this, MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void VerifyFail(String message) {
        pDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(VerifikasiTernakActivity.this);
        builder.setMessage(message)
                .setTitle("gagal")
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
    public void ChangeImgSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VerifikasiTernakActivity.this);
        builder.setMessage("Gambar ternak berhasil diubah")
                .setTitle("Berhasil")
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
    public void LoadData(List<DetailTernakResource> detailTernakResource) {
        txtAlamatKdg.setText(detailTernakResource.get(0).getAlamatKandang()+" RT."+detailTernakResource.get(0).getRtKdg()+"/RW."+
                detailTernakResource.get(0).getRw()+", "+detailTernakResource.get(0).getKelKandang()+", "+
                detailTernakResource.get(0).getKecKandang());
        edtNama.setText(detailTernakResource.get(0).getNamaTernak());
        edtTglLahir.setText(detailTernakResource.get(0).getTglLahir());
        edtKdInduk.setText(detailTernakResource.get(0).getKodeInduk());
        edtKdPj.setText(detailTernakResource.get(0).getKodePejantan());
        edtbangsa.setText(detailTernakResource.get(0).getJenisTernak());
        edtJk.setText(detailTernakResource.get(0).getJenisKelamin());
        Glide.with(VerifikasiTernakActivity.this)
                .load(BaseService.PATH_IMAGE_TERNAK+detailTernakResource.get(0).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgTernak);
    }

    @Override
    public void DataNull() {
        onResume();
    }
}