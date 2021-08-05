package dev.app.enak.View.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Preferences.Prefs;
import dev.app.enak.R;
import dev.app.enak.View.Activity.LandingPageActivity;
import dev.app.enak.View.Activity.LoginPemilikActivity;
import dev.app.enak.View.Activity.PengaturanAkunUserActivity;
import dev.app.enak.View.Activity.RegisterActivity;
import dev.app.enak.View.Activity.TambahIbActivity;

public class AkunFragment extends Fragment {

    CardView btnLogout, btnAtur;

    ProgressDialog pDialog;

    SharedPreferences sharedPreferences;
    GetUserResource getUserResource;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNIK = "Key Nik";
    public static final String KEYPASSWORD = "Key Password";

    public AkunFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View accountFrg = inflater.inflate(R.layout.fragment_akun, container, false);

        sharedPreferences = getActivity().getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        getUserResource = Prefs.getUser(getContext(),Prefs.USER_SESSION);

        btnLogout = accountFrg.findViewById(R.id.card_keluar);

        btnAtur = accountFrg.findViewById(R.id.card_pengaturan_akun);
        btnAtur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PengaturanAkunUserActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Apakah anda yakin akan keluar akun?")
                        .setTitle("Perhatian")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
                                pDialog.setIndeterminate(true);
                                pDialog.setMessage("Mohon Tunggu...");
                                pDialog.setCancelable(false);
                                pDialog.show();

                                Prefs.clear(getActivity().getApplicationContext());
                                Intent logout = new Intent(getActivity(), LandingPageActivity.class);
                                startActivity(logout);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        return accountFrg;
    }
}