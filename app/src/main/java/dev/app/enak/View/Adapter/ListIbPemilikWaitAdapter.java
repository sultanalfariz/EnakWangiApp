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
import dev.app.enak.Model.ListLaporanResource;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarIbPemilikActivity;
import dev.app.enak.View.Activity.DaftarLaporanPemilik;
import dev.app.enak.View.Activity.DetailIbActivity;
import dev.app.enak.View.Activity.DetailLaporanActivity;

public class ListIbPemilikWaitAdapter extends RecyclerView.Adapter<ListIbPemilikWaitAdapter.ListIbPemilikViewHolder> {

    List<ListIbResource> listIbResources;
    Context context;
    LayoutInflater layoutInflater;
    DaftarIbPemilikActivity activity;

    public ListIbPemilikWaitAdapter(Context context, List<ListIbResource> listIbResources, DaftarIbPemilikActivity activity){
        this.context = context;
        this.listIbResources = listIbResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListIbPemilikWaitAdapter.ListIbPemilikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list_ib,parent,false);
        ListIbPemilikWaitAdapter.ListIbPemilikViewHolder holder = new ListIbPemilikWaitAdapter.ListIbPemilikViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListIbPemilikWaitAdapter.ListIbPemilikViewHolder holder, int position) {
        holder.txtNama.setText(listIbResources.get(position).getNamaTernak());
        holder.txtKode.setText("Kode Anting: #"+listIbResources.get(position).getIdTernak().toString());

        String status = listIbResources.get(position).getStatus().toString();

        if (status.equals("Menunggu Peninjauan")){
            holder.txtStatus.setTextColor(Color.parseColor("#C75F00"));
            holder.bgstatus.setBackgroundResource(R.drawable.bg_status_orange);
            holder.txtStatus.setText(listIbResources.get(position).getStatus());
        } else if (status.equals("Belum Ditanggapi")){
            holder.txtStatus.setTextColor(Color.parseColor("#E10000"));
            holder.bgstatus.setBackgroundResource(R.drawable.bg_status_red);
            holder.txtStatus.setText(listIbResources.get(position).getStatus());
        }

        Glide.with(context)
                .load(BaseService.PATH_IMAGE_TERNAK+listIbResources.get(position).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgTernak);

        holder.cardLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailIbActivity.class);
                intent.putExtra("id", listIbResources.get(position).getId().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listIbResources.size();
    }

    public class ListIbPemilikViewHolder extends RecyclerView.ViewHolder {

        CardView cardLaporan;
        RoundedImageView imgTernak;
        TextView txtNama, txtKode, txtStatus;
        RelativeLayout bgstatus;

        public ListIbPemilikViewHolder(@NonNull View itemView) {
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
