package dev.app.enak.View.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Implementation.HomePetugasImpl;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.HomePetugasPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarTernakPemilikVerifikasi;
import dev.app.enak.View.Activity.DaftarTernakPetugasWaitTinjauActivity;
import dev.app.enak.View.Activity.DaftarTernakPetugasWaitVerifyActivity;
import dev.app.enak.View.MvpView.HomePetugasMvp;

public class BerandaPetugasFragment extends Fragment implements HomePetugasMvp {

    TextView textNama, txtTernakWaitverify, txtTernakWaitTinjau;
    RoundedImageView imgUser;

    CardView btnTernakWaitVerif, btnTernakWaitTinjau;

    HomePetugasPresenter homePetugasPresenter;
    GetPetugasResource getPetugasResource;

    Context context;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIP = "Key Nip";
    public static final String KEYPASSWORD = "Key Password";

    ProgressDialog pDialog;

    public BerandaPetugasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View homeFrg = inflater.inflate(R.layout.fragment_beranda_petugas, container, false);

        homePetugasPresenter = new HomePetugasImpl(this);

        sharedPreferences = getActivity().getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getPetugasResource = Prefs.getPetugas(getContext(),Prefs.PETUGAS_SESSION);

        String id = getPetugasResource.getId().toString();

        textNama = homeFrg.findViewById(R.id.text_nama);
        txtTernakWaitverify = homeFrg.findViewById(R.id.jumlah_ternak_terverif);
        txtTernakWaitTinjau = homeFrg.findViewById(R.id.jumlah_ternak_pemilik);
        imgUser = homeFrg.findViewById(R.id.foto_user);

        btnTernakWaitVerif = homeFrg.findViewById(R.id.klik_add_ternak);
        btnTernakWaitTinjau = homeFrg.findViewById(R.id.klik_daftar_ternak);

        btnTernakWaitVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarTernakPetugasWaitVerifyActivity.class);
                startActivity(intent);
            }
        });

        btnTernakWaitTinjau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarTernakPetugasWaitTinjauActivity.class);
                startActivity(intent);
            }
        });

        context = getActivity().getApplicationContext();

        homePetugasPresenter.getPetugas(id);

        return homeFrg;
    }

    @Override
    public void LoadData(GetPetugasResource getPetugasResource) {
        textNama.setText(getPetugasResource.getNama().toString());
        txtTernakWaitverify.setText(getPetugasResource.getTernakTinjau().toString());
        txtTernakWaitTinjau.setText(getPetugasResource.getTernakWait().toString());
        Glide.with(context)
                .load(BaseService.PATH_IMAGE_PETUGAS+getPetugasResource.getFoto().toString())
                .into(imgUser);
    }

    @Override
    public void DataNUll() {

    }
}