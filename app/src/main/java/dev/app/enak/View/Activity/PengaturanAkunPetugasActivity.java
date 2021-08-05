package dev.app.enak.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

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
import java.util.List;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Implementation.PengaturanAkunPetugasImpl;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Model.UriImageSource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.PengaturanAkunPetugasPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.PengaturanAkunPetugasMvp;
import id.zelory.compressor.Compressor;

public class PengaturanAkunPetugasActivity extends AppCompatActivity implements PengaturanAkunPetugasMvp {

    RoundedImageView imgUser;
    EditText edtNama, edtNIk;
    RelativeLayout btnUbahPass, btnTutup;

    PengaturanAkunPetugasPresenter pengaturanAkunPetugasPresenter;

    GetPetugasResource getPetugasResource;

    private File fileContentToUpload;
    InputStream inputStream;
    OutputStream output, outputs;
    String UriImage;
    Uri GlobalUri;
    List<UriImageSource> uriImageSources = new ArrayList<>();

    Integer id;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_akun_petugas);

        pengaturanAkunPetugasPresenter = new PengaturanAkunPetugasImpl(this);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getPetugasResource = Prefs.getPetugas(this,Prefs.PETUGAS_SESSION);

        id = getPetugasResource.getId();

        pengaturanAkunPetugasPresenter.getPetugas(id.toString());

        imgUser = findViewById(R.id.imgUser);
        edtNama = findViewById(R.id.form_nama_lengkap);
        edtNIk = findViewById(R.id.form_nik);

        btnTutup = findViewById(R.id.btn_tutup);
        btnUbahPass = findViewById(R.id.btn_ubah);

        btnUbahPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengaturanAkunPetugasActivity.this, UbahPasswordPetugasActivity.class);
                startActivity(intent);
            }
        });

        btnTutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(PengaturanAkunPetugasActivity.this);
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
            imgUser.setImageURI(Uri.parse(uriImageSources.get(0).getImage_uri().toString()));
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
                    fileContentToUpload = new Compressor(PengaturanAkunPetugasActivity.this).compressToFile(fileContentToUpload);
                    pengaturanAkunPetugasPresenter.ubahFoto(getPetugasResource.getId(), fileContentToUpload);

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
    public void PostSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PengaturanAkunPetugasActivity.this);
        builder.setMessage("Foto Profil berhasil diubah")
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
    public void PostFail() {

    }

    @Override
    public void PostSuccessPass(GetPetugasResource getPetugasResource) {
        Glide.with(PengaturanAkunPetugasActivity.this)
                .load(BaseService.PATH_IMAGE_PETUGAS+getPetugasResource.getFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgUser);
    }

    @Override
    public void PostFailPass(String message) {

    }

    @Override
    public void Relogin(String nip, String password) {

    }

    @Override
    public void LoadData(GetPetugasResource getPetugasResource) {
        edtNIk.setText(getPetugasResource.getNip().toString());
        edtNama.setText(getPetugasResource.getNama().toString());
        Glide.with(PengaturanAkunPetugasActivity.this)
                .load(BaseService.PATH_IMAGE_PETUGAS+getPetugasResource.getFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgUser);
    }
}