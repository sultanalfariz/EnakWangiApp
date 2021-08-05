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
import dev.app.enak.Model.ListIbResource;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarIbPetugasProsesActivity;
import dev.app.enak.View.Activity.DaftarIbPetugasSelesaiActivity;
import dev.app.enak.View.Activity.DetailIbPetugasActivity;

public class ListIbPetugasSelesaiAdapter extends RecyclerView.Adapter<ListIbPetugasSelesaiAdapter.ListIbPetugasSelesaiViewHolder> {

    List<ListIbResource> listIbResources;
    Context context;
    LayoutInflater layoutInflater;
    DaftarIbPetugasSelesaiActivity activity;

    public ListIbPetugasSelesaiAdapter(Context context, List<ListIbResource> listIbResources, DaftarIbPetugasSelesaiActivity activity){
        this.context = context;
        this.listIbResources = listIbResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListIbPetugasSelesaiAdapter.ListIbPetugasSelesaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_laporan_pemilik,parent,false);
        ListIbPetugasSelesaiAdapter.ListIbPetugasSelesaiViewHolder holder = new ListIbPetugasSelesaiAdapter.ListIbPetugasSelesaiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListIbPetugasSelesaiAdapter.ListIbPetugasSelesaiViewHolder holder, int position) {
        holder.txtNama.setText(listIbResources.get(position).getNamaTernak());
        holder.txtKode.setText("Kode Anting: #"+listIbResources.get(position).getIdTernak().toString());

//        holder.txtStatus.setText(listLaporanResources.get(position).getStatus());

        if (listIbResources.get(0).getStatus().equals("Selesai")){
            holder.txtStatus.setTextColor(Color.parseColor("#545454"));
            holder.bgstatus.setBackgroundResource(R.drawable.bg_btn_grey_transp);
            holder.txtStatus.setText(listIbResources.get(position).getStatus());
        }

        Glide.with(context)
                .load(BaseService.PATH_IMAGE_TERNAK+listIbResources.get(position).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgTernak);

        holder.cardLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailIbPetugasActivity.class);
                intent.putExtra("id_permintaan_ib", listIbResources.get(position).getId().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listIbResources.size();
    }

    public class ListIbPetugasSelesaiViewHolder extends RecyclerView.ViewHolder {

        CardView cardLaporan;
        RoundedImageView imgTernak;
        TextView txtNama, txtKode, txtStatus;
        RelativeLayout bgstatus;

        public ListIbPetugasSelesaiViewHolder(@NonNull View itemView) {
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