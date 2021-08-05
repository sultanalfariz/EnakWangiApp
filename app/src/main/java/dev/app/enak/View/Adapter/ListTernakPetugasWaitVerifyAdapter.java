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
import dev.app.enak.View.Activity.DaftarTernakPetugasWaitVerifyActivity;
import dev.app.enak.View.Activity.DetailTernakActivity;
import dev.app.enak.View.Activity.TanggapiTernakActivity;

public class ListTernakPetugasWaitVerifyAdapter extends RecyclerView.Adapter<ListTernakPetugasWaitVerifyAdapter
        .ListTernakPetugasWaitVerifyViewHolder> {

    List<ListTernakResource> listTernakResources;
    Context context;
    LayoutInflater layoutInflater;
    DaftarTernakPetugasWaitVerifyActivity activity;

    public ListTernakPetugasWaitVerifyAdapter(Context context, List<ListTernakResource> listTernakResources, DaftarTernakPetugasWaitVerifyActivity
            activity) {
        this.context = context;
        this.listTernakResources = listTernakResources;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListTernakPetugasWaitVerifyAdapter.ListTernakPetugasWaitVerifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list_ternak_wait, parent, false);
        ListTernakPetugasWaitVerifyAdapter.ListTernakPetugasWaitVerifyViewHolder holder = new ListTernakPetugasWaitVerifyAdapter.ListTernakPetugasWaitVerifyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListTernakPetugasWaitVerifyAdapter.ListTernakPetugasWaitVerifyViewHolder holder, int position) {
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
                Intent intent = new Intent(context, TanggapiTernakActivity.class);
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

    public class ListTernakPetugasWaitVerifyViewHolder extends RecyclerView.ViewHolder {

        CardView cardTernak;
        RoundedImageView imgTernak;
        TextView txtNama, txtKode, txtStatus;

        public ListTernakPetugasWaitVerifyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardTernak = itemView.findViewById(R.id.card_ternak);
            imgTernak = itemView.findViewById(R.id.img_ternak);
            txtNama = itemView.findViewById(R.id.text_nama_ternak);
            txtKode = itemView.findViewById(R.id.text_kode);
            txtStatus = itemView.findViewById(R.id.text_status);
        }
    }
}
