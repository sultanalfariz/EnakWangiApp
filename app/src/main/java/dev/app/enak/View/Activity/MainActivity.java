package dev.app.enak.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import dev.app.enak.R;
import dev.app.enak.View.Fragment.AkunFragment;
import dev.app.enak.View.Fragment.BerandaPemilikFragment;
import dev.app.enak.View.Fragment.IbPemilikFragment;
import dev.app.enak.View.Fragment.LaporanPemilikFragment;

public class MainActivity extends AppCompatActivity {

    RelativeLayout btnHome, btnLapor, btnIB, btnAkun;
    ImageView icHome, icLapor, icIB, icAkun;
    public int position = 3;
    String intentfrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent.getStringExtra("posisi") != null){
            intentfrg = intent.getStringExtra("posisi");
            position = Integer.parseInt(intentfrg);
        } else{
            position = 0;
        }

        btnHome = findViewById(R.id.btn_home);
        btnLapor = findViewById(R.id.btn_laporan);
        btnIB = findViewById(R.id.btn_ib);
        btnAkun = findViewById(R.id.btn_account);

        icHome = findViewById(R.id.icon_home);
        icLapor = findViewById(R.id.icon_laporan);
        icIB = findViewById(R.id.icon_ib);
        icAkun = findViewById(R.id.icon_akun);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BerandaPemilikFragment());
                icHome.setImageResource(R.drawable.ic_home_active);
                icLapor.setImageResource(R.drawable.ic_lapor_deactive);
                icIB.setImageResource(R.drawable.ic_ib_deactive);
                icAkun.setImageResource(R.drawable.ic_akun_deactive);
            }
        });

        btnLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new LaporanPemilikFragment());
                icHome.setImageResource(R.drawable.ic_home_deactive);
                icLapor.setImageResource(R.drawable.ic_lapor_active);
                icIB.setImageResource(R.drawable.ic_ib_deactive);
                icAkun.setImageResource(R.drawable.ic_akun_deactive);
            }
        });

        btnIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new IbPemilikFragment());
                icHome.setImageResource(R.drawable.ic_home_deactive);
                icLapor.setImageResource(R.drawable.ic_lapor_deactive);
                icIB.setImageResource(R.drawable.ic_ib_active);
                icAkun.setImageResource(R.drawable.ic_akun_deactive);
            }
        });

        btnAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AkunFragment());
                icHome.setImageResource(R.drawable.ic_home_deactive);
                icLapor.setImageResource(R.drawable.ic_lapor_deactive);
                icIB.setImageResource(R.drawable.ic_ib_deactive);
                icAkun.setImageResource(R.drawable.ic_akun_active);
            }
        });

        switch(position){
            case 0:
                loadFragment(new BerandaPemilikFragment());
                icHome.setImageResource(R.drawable.ic_home_active);
                icLapor.setImageResource(R.drawable.ic_lapor_deactive);
                icIB.setImageResource(R.drawable.ic_ib_deactive);
                icAkun.setImageResource(R.drawable.ic_akun_deactive);
                if (savedInstanceState == null) {
                    setFragment(new BerandaPemilikFragment());
                }
                break;
            case 1:
                loadFragment(new LaporanPemilikFragment());
                icHome.setImageResource(R.drawable.ic_home_deactive);
                icLapor.setImageResource(R.drawable.ic_lapor_active);
                icIB.setImageResource(R.drawable.ic_ib_deactive);
                icAkun.setImageResource(R.drawable.ic_akun_deactive);
                if (savedInstanceState == null) {
                    setFragment(new LaporanPemilikFragment());
                }
                break;
            case 2:
                loadFragment(new IbPemilikFragment());
                icHome.setImageResource(R.drawable.ic_home_deactive);
                icLapor.setImageResource(R.drawable.ic_lapor_deactive);
                icIB.setImageResource(R.drawable.ic_ib_active);
                icAkun.setImageResource(R.drawable.ic_akun_deactive);
                if (savedInstanceState == null) {
                    setFragment(new IbPemilikFragment());
                }
                break;
            case 3:
                loadFragment(new AkunFragment());
                icHome.setImageResource(R.drawable.ic_home_deactive);
                icLapor.setImageResource(R.drawable.ic_lapor_deactive);
                icIB.setImageResource(R.drawable.ic_ib_deactive);
                icAkun.setImageResource(R.drawable.ic_akun_active);
                if (savedInstanceState == null) {
                    setFragment(new AkunFragment());
                }

                break;
            default:
                Toast.makeText(MainActivity.this,"no page",Toast.LENGTH_LONG).show();
        }
    }

    public void  loadFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}