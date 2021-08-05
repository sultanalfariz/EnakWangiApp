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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListIbResource;
import dev.app.enak.Model.ListLaporanResource;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarIbPetugasTinjauActivity;
import dev.app.enak.View.Activity.DaftarLaporanPetugasTinjauActivity;
import dev.app.enak.View.Activity.TinjauIbActivity;
import dev.app.enak.View.Activity.TinjauLaporanActivity;

public class ListIbPetugasTinjauAdapter extends RecyclerView.Adapter<ListIbPetugasTinjauAdapter.ListIbPetugasTinjauViewHolder> {

    List<ListIbResource> listIbResources;
    Context context;
    LayoutInflater layoutInflater;
    DaftarIbPetugasTinjauActivity activity;

    public ListIbPetugasTinjauAdapter(Context context, List<ListIbResource> listIbResources, DaftarIbPetugasTinjauActivity activity){
        this.context = context;
        this.listIbResources = listIbResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListIbPetugasTinjauAdapter.ListIbPetugasTinjauViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list_ternak_tinjau,parent,false);
        ListIbPetugasTinjauAdapter.ListIbPetugasTinjauViewHolder holder = new ListIbPetugasTinjauAdapter.ListIbPetugasTinjauViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListIbPetugasTinjauAdapter.ListIbPetugasTinjauViewHolder holder, int position) {
        holder.txtNama.setText(listIbResources.get(position).getNamaTernak());
        holder.txtKode.setText("Kode Anting: #" + listIbResources.get(position).getIdTernak().toString());
        holder.txtStatus.setText(listIbResources.get(position).getStatus());
        Glide.with(context)
                .load(BaseService.PATH_IMAGE_TERNAK + listIbResources.get(position).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgTernak);
        holder.txtTgl.setText("*Harus ditinjau pada: "+listIbResources.get(position).getTglPeninjauan().toString());

        holder.cardTernak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TinjauIbActivity.class);
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

    public class ListIbPetugasTinjauViewHolder extends RecyclerView.ViewHolder {

        CardView cardTernak;
        RoundedImageView imgTernak;
        TextView txtNama, txtKode, txtStatus, txtTgl;

        public ListIbPetugasTinjauViewHolder(@NonNull View itemView) {
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