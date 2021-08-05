package dev.app.enak.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarIbPetugasProsesActivity;
import dev.app.enak.View.Activity.DaftarIbPetugasSelesaiActivity;
import dev.app.enak.View.Activity.DaftarIbPetugasTinjauActivity;
import dev.app.enak.View.Activity.DaftarIbPetugasWaitActivity;
import dev.app.enak.View.Activity.TambahIbActivity;

public class IbPetugasFragment extends Fragment {

    CardView cardWait, cardTinjau, cardProses, cardSelesai;

    public IbPetugasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ibFrg = inflater.inflate(R.layout.fragment_ib_petugas, container, false);

        cardWait = ibFrg.findViewById(R.id.card_minta_ib);
        cardTinjau = ibFrg.findViewById(R.id.card_ib_belum);
        cardProses = ibFrg.findViewById(R.id.card_track_ib);
        cardSelesai = ibFrg.findViewById(R.id.card_track_ib2);

        cardWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarIbPetugasWaitActivity.class);
                startActivity(intent);
            }
        });

        cardTinjau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarIbPetugasTinjauActivity.class);
                startActivity(intent);
            }
        });

        cardProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarIbPetugasProsesActivity.class);
                startActivity(intent);
            }
        });

        cardSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarIbPetugasSelesaiActivity.class);
                startActivity(intent);
            }
        });

        return ibFrg;
    }
}