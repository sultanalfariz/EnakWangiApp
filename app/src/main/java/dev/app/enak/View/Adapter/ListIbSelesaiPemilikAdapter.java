package dev.app.enak.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.app.enak.Model.ListIbResource;
import dev.app.enak.Model.ListLaporanResource;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DetailIbActivity;
import dev.app.enak.View.Activity.DetailLaporanActivity;
import dev.app.enak.View.Activity.RiwayatIbPemilikActivity;
import dev.app.enak.View.Activity.RiwayatLaporanPemilik;

public class ListIbSelesaiPemilikAdapter extends RecyclerView.Adapter<ListIbSelesaiPemilikAdapter.ListIbSelesaiPemilikViewHolder> {

    List<ListIbResource> listIbResources;
    Context context;
    LayoutInflater layoutInflater;
    RiwayatIbPemilikActivity activity;

    public ListIbSelesaiPemilikAdapter(Context context, List<ListIbResource> listIbResources, RiwayatIbPemilikActivity activity){
        this.context = context;
        this.listIbResources = listIbResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListIbSelesaiPemilikAdapter.ListIbSelesaiPemilikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_riwayat_laporan,parent,false);
        ListIbSelesaiPemilikAdapter.ListIbSelesaiPemilikViewHolder holder = new ListIbSelesaiPemilikAdapter.ListIbSelesaiPemilikViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListIbSelesaiPemilikAdapter.ListIbSelesaiPemilikViewHolder holder, int position) {
        holder.txtTgl.setText(listIbResources.get(position).getTglPeninjauan());
        holder.txtKode.setText("Kode Anting: #"+listIbResources.get(position).getIdTernak().toString());
        holder.txtStatus.setText(listIbResources.get(position).getStatus());

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

    public class ListIbSelesaiPemilikViewHolder extends RecyclerView.ViewHolder {

        CardView cardLaporan;
        TextView txtTgl, txtKode, txtStatus;

        public ListIbSelesaiPemilikViewHolder(@NonNull View itemView) {
            super(itemView);

            cardLaporan = itemView.findViewById(R.id.card_laporan);
            txtKode = itemView.findViewById(R.id.text_kode_anting);
            txtTgl = itemView.findViewById(R.id.text_tgl_tinjau);
            txtStatus = itemView.findViewById(R.id.text_status);
        }
    }
}