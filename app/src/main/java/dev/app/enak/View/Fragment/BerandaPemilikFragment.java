package dev.app.enak.View.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Implementation.HomeUserImpl;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.ListArtikelResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.Presenter.HomeUserPresenter;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarTernakPemilikMenungguVerifikasi;
import dev.app.enak.View.Activity.DaftarTernakPemilikVerifikasi;
import dev.app.enak.View.Activity.TambahTernakActivity;
import dev.app.enak.View.Adapter.ListArtikelHomeAdapter;
import dev.app.enak.View.MvpView.HomeUserMvp;

public class BerandaPemilikFragment extends Fragment implements HomeUserMvp {

    TextView textNama, txtCountTernakPemilikVerify, txtCountTernakVerify, txtKosong;
    RoundedImageView imgUser;
    RecyclerView rvArtikel;

    CardView btnTernakVerify, btnTernakWait, btnTambahTernak;

    HomeUserPresenter homeUserPresenter;
    GetUserResource getUserResource;

    Context context;

    ListArtikelHomeAdapter listArtikelHomeAdapter;

    SharedPreferences sharedPreferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    public BerandaPemilikFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeFrg = inflater.inflate(R.layout.fragment_beranda_pemilik, container, false);

        homeUserPresenter = new HomeUserImpl(this);

        sharedPreferences = getActivity().getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(getContext(),Prefs.USER_SESSION);

        String id = getUserResource.getId().toString();
        txtKosong = homeFrg.findViewById(R.id.txt_info_kosong);
        textNama = homeFrg.findViewById(R.id.text_nama);
        txtCountTernakVerify = homeFrg.findViewById(R.id.jumlah_ternak_terverif);
        txtCountTernakPemilikVerify = homeFrg.findViewById(R.id.jumlah_ternak_pemilik);
        imgUser = homeFrg.findViewById(R.id.foto_user);

        btnTernakVerify = homeFrg.findViewById(R.id.klik_daftar_ternak);
        btnTernakWait = homeFrg.findViewById(R.id.klik_ternak_wait);
        btnTambahTernak = homeFrg.findViewById(R.id.klik_add_ternak);

        btnTambahTernak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TambahTernakActivity.class);
                startActivity(intent);
            }
        });

        btnTernakVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarTernakPemilikVerifikasi.class);
                startActivity(intent);
            }
        });

        btnTernakWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarTernakPemilikMenungguVerifikasi.class);
                startActivity(intent);
            }
        });

        rvArtikel = homeFrg.findViewById(R.id.rec_artikel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvArtikel.setLayoutManager(linearLayoutManager);
        rvArtikel.setNestedScrollingEnabled(false);

        homeUserPresenter.getUser(id);
        homeUserPresenter.listArtikel();

        context = getActivity().getApplicationContext();

        return homeFrg;
    }

    @Override
    public void loadData(GetUserResource getUserResources) {
        textNama.setText(getUserResources.getNamaTampilan());
        txtCountTernakPemilikVerify.setText(getUserResources.getTernakWait().toString());
        txtCountTernakVerify.setText(getUserResources.getTernakVerify().toString());
        Glide.with(context.getApplicationContext())
                .load(BaseService.PATH_IMAGE_PEMILIK+getUserResources.getFoto().toString())
                .into(imgUser);
    }

    @Override
    public void loadArtikel(List<ListArtikelResource> listArtikelResources) {
        listArtikelHomeAdapter = new ListArtikelHomeAdapter(context.getApplicationContext(), listArtikelResources, this);
        rvArtikel.setAdapter(listArtikelHomeAdapter);
        listArtikelHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void DataEmpty(String message) {
        txtKosong.setVisibility(View.VISIBLE);
    }

    @Override
    public void DataNull() {
        onResume();
    }
}