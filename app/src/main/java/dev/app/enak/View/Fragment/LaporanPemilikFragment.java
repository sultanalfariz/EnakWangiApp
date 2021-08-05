package dev.app.enak.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarLaporanPemilik;
import dev.app.enak.View.Activity.TambahLaporanActivity;
import dev.app.enak.View.Activity.TambahTernakActivity;

public class LaporanPemilikFragment extends Fragment {

    CardView btnListLaporan, btnTambahLaporan;

    public LaporanPemilikFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layananFrg = inflater.inflate(R.layout.fragment_laporan_pemilik, container, false);

        btnListLaporan = layananFrg.findViewById(R.id.card_list_layanan);
        btnTambahLaporan = layananFrg.findViewById(R.id.card_add_layanan);

        btnListLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarLaporanPemilik.class);
                startActivity(intent);
            }
        });

        btnTambahLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TambahLaporanActivity.class);
                startActivity(intent);
            }
        });

        return layananFrg;
    }
}