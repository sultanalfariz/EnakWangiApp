package dev.app.enak.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListLaporanResource;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarLaporanPemilik;
import dev.app.enak.View.Activity.DetailLaporanActivity;
import dev.app.enak.View.Activity.RiwayatLaporanPemilik;

public class ListLaporanSelesaiPemilikAdapter extends RecyclerView.Adapter<ListLaporanSelesaiPemilikAdapter.ListLaporanSelesaiPemilikViewHolder> {

    List<ListLaporanResource> listLaporanResources;
    Context context;
    LayoutInflater layoutInflater;
    RiwayatLaporanPemilik activity;

    public ListLaporanSelesaiPemilikAdapter(Context context, List<ListLaporanResource> listLaporanResources, RiwayatLaporanPemilik activity){
        this.context = context;
        this.listLaporanResources = listLaporanResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListLaporanSelesaiPemilikAdapter.ListLaporanSelesaiPemilikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_riwayat_laporan,parent,false);
        ListLaporanSelesaiPemilikAdapter.ListLaporanSelesaiPemilikViewHolder holder = new ListLaporanSelesaiPemilikAdapter.ListLaporanSelesaiPemilikViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListLaporanSelesaiPemilikAdapter.ListLaporanSelesaiPemilikViewHolder holder, int position) {
        holder.txtTgl.setText(listLaporanResources.get(position).getTglPeninjauan());
        holder.txtKode.setText("Kode Anting: #"+listLaporanResources.get(position).getIdTernak().toString());
        holder.txtStatus.setText(listLaporanResources.get(position).getStatus());

        holder.cardLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailLaporanActivity.class);
                intent.putExtra("id", listLaporanResources.get(position).getId().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLaporanResources.size();
    }

    public class ListLaporanSelesaiPemilikViewHolder extends RecyclerView.ViewHolder {

        CardView cardLaporan;
        TextView txtTgl, txtKode, txtStatus;

        public ListLaporanSelesaiPemilikViewHolder(@NonNull View itemView) {
            super(itemView);

            cardLaporan = itemView.findViewById(R.id.card_laporan);
            txtKode = itemView.findViewById(R.id.text_kode_anting);
            txtTgl = itemView.findViewById(R.id.text_tgl_tinjau);
            txtStatus = itemView.findViewById(R.id.text_status);
        }
    }
}