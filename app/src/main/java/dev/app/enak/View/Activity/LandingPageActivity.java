package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import dev.app.enak.Preferences.Prefs;
import dev.app.enak.R;

public class LandingPageActivity extends AppCompatActivity {

    RelativeLayout btnPetugas, btnPemilik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        btnPetugas = findViewById(R.id.btn_petugas);
        btnPemilik = findViewById(R.id.btn_pemilik);

        if (isSessionLoginUser()){
            Intent session = new Intent(LandingPageActivity.this, MainActivity.class);
            startActivity(session);
            finish();
        } else if (isSessionLoginPetugas()){
            Intent session = new Intent(LandingPageActivity.this, MainActivity2.class);
            startActivity(session);
            finish();
        }

        btnPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, LoginPetugasActivity.class);
                startActivity(intent);
            }
        });

        btnPemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, LoginPemilikActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean isSessionLoginUser() {
        return Prefs.getUser(this, Prefs.USER_SESSION) != null;
    }

    boolean isSessionLoginPetugas() {
        return Prefs.getPetugas(this, Prefs.PETUGAS_SESSION) != null;
    }
}