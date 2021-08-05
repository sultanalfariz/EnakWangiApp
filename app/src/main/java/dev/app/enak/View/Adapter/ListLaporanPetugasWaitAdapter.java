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
import dev.app.enak.View.Activity.DaftarLaporanPetugasWaitActivity;
import dev.app.enak.View.Activity.DetailLaporanActivity;
import dev.app.enak.View.Activity.TanggapiLaporanActivity;

public class ListLaporanPetugasWaitAdapter extends RecyclerView.Adapter<ListLaporanPetugasWaitAdapter.ListLaporanPetugasWaitViewHolder> {

    List<ListLaporanResource> listLaporanResources;
    Context context;
    LayoutInflater layoutInflater;
    DaftarLaporanPetugasWaitActivity activity;

    public ListLaporanPetugasWaitAdapter(Context context, List<ListLaporanResource> listLaporanResources, DaftarLaporanPetugasWaitActivity activity){
        this.context = context;
        this.listLaporanResources = listLaporanResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListLaporanPetugasWaitAdapter.ListLaporanPetugasWaitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_laporan_pemilik,parent,false);
        ListLaporanPetugasWaitAdapter.ListLaporanPetugasWaitViewHolder holder = new ListLaporanPetugasWaitAdapter.ListLaporanPetugasWaitViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListLaporanPetugasWaitAdapter.ListLaporanPetugasWaitViewHolder holder, int position) {
        holder.txtNama.setText(listLaporanResources.get(position).getNamaTernak());
        holder.txtKode.setText("Kode Anting: #"+listLaporanResources.get(position).getIdTernak().toString());

//        holder.txtStatus.setText(listLaporanResources.get(position).getStatus());

        if (listLaporanResources.get(0).getStatus().equals("Belum Ditanggapi")){
            holder.txtStatus.setTextColor(Color.parseColor("#E10000"));
            holder.bgstatus.setBackgroundResource(R.drawable.bg_status_red);
            holder.txtStatus.setText(listLaporanResources.get(position).getStatus());
        }

        Glide.with(context)
                .load(BaseService.PATH_IMAGE_TERNAK+listLaporanResources.get(position).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgTernak);

        holder.cardLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TanggapiLaporanActivity.class);
                intent.putExtra("id_laporan", listLaporanResources.get(position).getId().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLaporanResources.size();
    }

    public class ListLaporanPetugasWaitViewHolder extends RecyclerView.ViewHolder {

        CardView cardLaporan;
        RoundedImageView imgTernak;
        TextView txtNama, txtKode, txtStatus;
        RelativeLayout bgstatus;

        public ListLaporanPetugasWaitViewHolder(@NonNull View itemView) {
            super(itemView);

            cardLaporan = itemView.findViewById(R.id.card_laporan);
            imgTernak = itemView.findViewById(R.id.img_ternak);
            txtNama = itemView.findViewById(R.id.text_nama_ternak);
            txtKode = itemView.findViewById(R.id.text_kode);
            txtStatus = itemView.findViewById(R.id.text_status);
            bgstatus = itemView.findViewById(R.id.bgStatus);
        }
    }
}