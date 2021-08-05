package dev.app.enak.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarLaporanPemilik;
import dev.app.enak.View.Activity.DaftarLaporanPetugasSelesaiActivity;
import dev.app.enak.View.Activity.DaftarLaporanPetugasTinjauActivity;
import dev.app.enak.View.Activity.DaftarLaporanPetugasWaitActivity;

public class LaporanPetugasFragment extends Fragment {

    CardView btnLaporanWait, btnLaporanTinjau, btnLaporanSelesai;

    public LaporanPetugasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layananFrg = inflater.inflate(R.layout.fragment_laporan_petugas, container, false);

        btnLaporanWait = layananFrg.findViewById(R.id.card_add_layanan);
        btnLaporanTinjau = layananFrg.findViewById(R.id.card_list_layanan);
        btnLaporanSelesai = layananFrg.findViewById(R.id.card_selesai);

        btnLaporanWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarLaporanPetugasWaitActivity.class);
                startActivity(intent);
            }
        });

        btnLaporanTinjau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarLaporanPetugasTinjauActivity.class);
                startActivity(intent);
            }
        });

        btnLaporanSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarLaporanPetugasSelesaiActivity.class);
                startActivity(intent);
            }
        });

        return layananFrg;
    }
}