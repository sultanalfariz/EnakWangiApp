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
import dev.app.enak.Model.ListTernakResource;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DaftarTernakPemilikMenungguVerifikasi;
import dev.app.enak.View.Activity.DetailArtikelActivity;
import dev.app.enak.View.Activity.DetailTernakActivity;

public class ListTernakPemilikWaitAdapter extends RecyclerView.Adapter<ListTernakPemilikWaitAdapter.ListTernakPemilikWaitViewHolder> {

    List<ListTernakResource> listTernakResources;
    Context context;
    LayoutInflater layoutInflater;
    DaftarTernakPemilikMenungguVerifikasi activity;

    public ListTernakPemilikWaitAdapter(Context context, List<ListTernakResource> listTernakResources, DaftarTernakPemilikMenungguVerifikasi activity) {
        this.context = context;
        this.listTernakResources = listTernakResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListTernakPemilikWaitAdapter.ListTernakPemilikWaitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list_ternak_wait, parent, false);
        ListTernakPemilikWaitAdapter.ListTernakPemilikWaitViewHolder holder = new ListTernakPemilikWaitAdapter.ListTernakPemilikWaitViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListTernakPemilikWaitAdapter.ListTernakPemilikWaitViewHolder holder, int position) {
        holder.txtNama.setText(listTernakResources.get(position).getNamaTernak());
        holder.txtKode.setText("Kode Antri: #" + listTernakResources.get(position).getIdTernak().toString());
        holder.txtStatus.setText(listTernakResources.get(position).getStatus());
        Glide.with(context)
                .load(BaseService.PATH_IMAGE_TERNAK + listTernakResources.get(position).getGambarDepan().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgTernak);

        holder.cardTernak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTernakActivity.class);
                intent.putExtra("id_ternak", listTernakResources.get(position).getIdTernak().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTernakResources.size();
    }

    public class ListTernakPemilikWaitViewHolder extends RecyclerView.ViewHolder {

        CardView cardTernak;
        RoundedImageView imgTernak;
        TextView txtNama, txtKode, txtStatus;

        public ListTernakPemilikWaitViewHolder(@NonNull View itemView) {
            super(itemView);

            cardTernak = itemView.findViewById(R.id.card_ternak);
            imgTernak = itemView.findViewById(R.id.img_ternak);
            txtNama = itemView.findViewById(R.id.text_nama_ternak);
            txtKode = itemView.findViewById(R.id.text_kode);
            txtStatus = itemView.findViewById(R.id.text_status);
        }
    }
}
