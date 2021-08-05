package dev.app.enak.View.Adapter;

import android.content.Context;
import android.content.Intent;
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
import dev.app.enak.View.Activity.DaftarLaporanPetugasTinjauActivity;
import dev.app.enak.View.Activity.DaftarLaporanPetugasWaitActivity;
import dev.app.enak.View.Activity.TinjauLaporanActivity;
import dev.app.enak.View.Activity.VerifikasiTernakActivity;

public class ListLaporanPetugasTinjauAdapter extends RecyclerView.Adapter<ListLaporanPetugasTinjauAdapter.ListLaporanPetugasTinjauViewHolder> {

    List<ListLaporanResource> listLaporanResources;
    Context context;
    LayoutInflater layoutInflater;
    DaftarLaporanPetugasTinjauActivity activity;

    public ListLaporanPetugasTinjauAdapter(Context context, List<ListLaporanResource> listLaporanResources, DaftarLaporanPetugasTinjauActivity activity){
        this.context = context;
        this.listLaporanResources = listLaporanResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListLaporanPetugasTinjauAdapter.ListLaporanPetugasTinjauViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list_ternak_tinjau,parent,false);
        ListLaporanPetugasTinjauAdapter.ListLaporanPetugasTinjauViewHolder holder = new ListLaporanPetugasTinjauAdapter.ListLaporanPetugasTinjauViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListLaporanPetugasTinjauAdapter.ListLaporanPetugasTinjauViewHolder holder, int position) {
        holder.txtNama.setText(listLaporanResources.get(position).getNamaTernak());
        holder.txtKode.setText("Kode Anting: #" + listLaporanResources.get(position).getIdTernak().toString());
        holder.txtStatus.setText(listLaporanResources.get(position).getStatus());
        Glide.with(context)
                .load(BaseService.PATH_IMAGE_TERNAK + listLaporanResources.get(position).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgTernak);
        holder.txtTgl.setText("*Harus ditinjau pada: "+listLaporanResources.get(position).getTglPeninjauan().toString());

        holder.cardTernak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TinjauLaporanActivity.class);
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

    public class ListLaporanPetugasTinjauViewHolder extends RecyclerView.ViewHolder {

        CardView cardTernak;
        RoundedImageView imgTernak;
        TextView txtNama, txtKode, txtStatus, txtTgl;

        public ListLaporanPetugasTinjauViewHolder(@NonNull View itemView) {
            super(itemView);

            cardTernak = itemView.findViewById(R.id.card_ternak);
            imgTernak = itemView.findViewById(R.id.img_ternak);
            txtNama = itemView.findViewById(R.id.text_nama_ternak);
            txtKode = itemView.findViewById(R.id.text_kode);
            txtStatus = itemView.findViewById(R.id.text_status);
            txtTgl = itemView.findViewById(R.id.text_tanggal);
        }
    }
}