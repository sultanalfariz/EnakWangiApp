package dev.app.enak.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarIbPemilikActivity;
import dev.app.enak.View.Activity.DaftarLaporanPemilik;
import dev.app.enak.View.Activity.TambahIbActivity;
import dev.app.enak.View.Activity.TrackIbPemilikActivity;

public class IbPemilikFragment extends Fragment {

    CardView cardMintaIb, cardWait, cardTrack;

    public IbPemilikFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ibFrg = inflater.inflate(R.layout.fragment_ib_pemilik, container, false);

        cardMintaIb = ibFrg.findViewById(R.id.card_minta_ib);
        cardWait = ibFrg.findViewById(R.id.card_ib_belum);
        cardTrack = ibFrg.findViewById(R.id.card_track_ib);

        cardMintaIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TambahIbActivity.class);
                startActivity(intent);
            }
        });

        cardWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarIbPemilikActivity.class);
                startActivity(intent);
            }
        });

        cardTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrackIbPemilikActivity.class);
                startActivity(intent);
            }
        });

        return ibFrg;
    }
}