package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Implementation.DetailArtikelImpl;
import dev.app.enak.Model.DetailArtikelResource;
import dev.app.enak.Presenter.DetailArtikelPresenter;
import dev.app.enak.R;
import dev.app.enak.View.MvpView.DetailArtikelMvp;

public class DetailArtikelActivity extends AppCompatActivity implements DetailArtikelMvp {

    ImageView imgArtikel;
    TextView txtJudul, txtAuthor, txtIsi;

    DetailArtikelPresenter detailArtikelPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        detailArtikelPresenter = new DetailArtikelImpl(this);

        imgArtikel = findViewById(R.id.gambar_artikel);
        txtJudul = findViewById(R.id.text_judul_artikel);
        txtIsi = findViewById(R.id.text_isi_artikel);

        String id = getIntent().getStringExtra("id");

        detailArtikelPresenter.DetailArtikel(id);
    }

    @Override
    public void LoadData(DetailArtikelResource detailArtikelResource) {
        Glide.with(DetailArtikelActivity.this)
                .load(BaseService.PATH_IMAGE_ARTIKEL+detailArtikelResource.getGambar().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgArtikel);
        txtJudul.setText(detailArtikelResource.getJudul());
        txtIsi.setText(detailArtikelResource.getIsiArtikel());
    }

    @Override
    public void DataNull() {
        onResume();
    }
}