package dev.app.enak.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Calendar;
import java.util.List;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Implementation.TanggapiTernakImpl;
import dev.app.enak.Model.DetailTernakResource;
import dev.app.enak.Presenter.TanggapiTernakPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.TanggapiTernakMvp;

public class TanggapiTernakActivity extends AppCompatActivity implements TanggapiTernakMvp {

    RoundedImageView imgTenak;
    TextView txtkode, txtNama, txtTglLahir, txtKdInd, txtKdPj, txtBangsa, txtJkel, txtAlamat;
    EditText edtTglTinjau;
    RelativeLayout btnKirim;

    TanggapiTernakPresenter tanggapiTernakPresenter;

    String tglTinjau, id_ternak;
    private int mYear, mMonth, mDay;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggapi_ternak);

        tanggapiTernakPresenter = new TanggapiTernakImpl(this);

        id_ternak = getIntent().getStringExtra("id_ternak");

        tanggapiTernakPresenter.detailTernak(id_ternak);

        imgTenak = findViewById(R.id.img_ternak);
        txtkode = findViewById(R.id.text_kode_antri_ternak);
        txtNama = findViewById(R.id.text_nama_ternak);
        txtTglLahir = findViewById(R.id.text_tgl_lahir_ternak);
        txtKdInd = findViewById(R.id.text_kode_induk_ternak);
        txtKdPj = findViewById(R.id.text_kode_Pejantan_ternak);
        txtBangsa = findViewById(R.id.text_bangsa_ternak);
        txtJkel = findViewById(R.id.text_jenis_kelamin_ternak);
        txtAlamat = findViewById(R.id.text_alamat_kandang);
        edtTglTinjau = findViewById(R.id.form_tgl_tinjau);
        btnKirim = findViewById(R.id.btn_kririm);

        edtTglTinjau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(TanggapiTernakActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtTglTinjau.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tglTinjau = edtTglTinjau.getText().toString();

                if (tglTinjau.length() == 0){
                    Toast.makeText(getApplicationContext(), "Masukkan Tanggal Peninjauan",
                            Toast.LENGTH_LONG).show();
                } else{
                    pDialog = new ProgressDialog(TanggapiTernakActivity.this, ProgressDialog.STYLE_SPINNER);
                    pDialog.setIndeterminate(true);
                    pDialog.setMessage("Mohon Tunggu...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    tanggapiTernakPresenter.tinjauTernak(id_ternak,tglTinjau);
                }
            }
        });
    }

    @Override
    public void PostSuccess() {
        pDialog.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(TanggapiTernakActivity.this);
        builder.setMessage("Pengajuan ternak berhasil ditanggapi")
                .setTitle("Berhasil")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TanggapiTernakActivity.this, MainActivity2.class);
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

    @Override
    public void LoadData(List<DetailTernakResource> detailTernakResource) {
        Glide.with(TanggapiTernakActivity.this)
                .load(BaseService.PATH_IMAGE_TERNAK+detailTernakResource.get(0).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgTenak);

        txtkode.setText("#"+id_ternak);
        txtNama.setText(detailTernakResource.get(0).getNamaTernak());
        txtTglLahir.setText(detailTernakResource.get(0).getTglLahir());
        txtKdInd.setText(detailTernakResource.get(0).getKodeInduk());
        txtKdPj.setText(detailTernakResource.get(0).getKodePejantan());
        txtBangsa.setText(detailTernakResource.get(0).getJenisTernak());
        txtJkel.setText(detailTernakResource.get(0).getJenisKelamin());
        txtAlamat.setText(detailTernakResource.get(0).getAlamatKandang()+" RT."+detailTernakResource.get(0).getRtKdg()+"/RW."+
                detailTernakResource.get(0).getRw()+", "+detailTernakResource.get(0).getKelKandang()+", "+
                detailTernakResource.get(0).getKecKandang());
    }

    @Override
    public void DataNull() {
        onResume();
    }
}