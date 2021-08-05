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
import dev.app.enak.Model.ListArtikelResource;
import dev.app.enak.R;
import dev.app.enak.View.Activity.DetailArtikelActivity;
import dev.app.enak.View.Fragment.BerandaPemilikFragment;

public class ListArtikelHomeAdapter extends RecyclerView.Adapter<ListArtikelHomeAdapter.ListArtikelHomeViewHolder> {

    List<ListArtikelResource> listArtikelResources;
    Context context;
    LayoutInflater layoutInflater;
    BerandaPemilikFragment fragment;

    public ListArtikelHomeAdapter(Context context,List<ListArtikelResource> listArtikelResources,BerandaPemilikFragment fragment){
        this.context = context;
        this.listArtikelResources = listArtikelResources;
        this.fragment = fragment;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListArtikelHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_artikel,parent,false);
        ListArtikelHomeViewHolder holder = new ListArtikelHomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListArtikelHomeViewHolder holder, int position) {
        holder.txtJudul.setText(listArtikelResources.get(position).getJudul().toString());
        holder.txtAuthor.setText(listArtikelResources.get(position).getNama().toString());
        Glide.with(context)
                .load(BaseService.PATH_IMAGE_ARTIKEL+listArtikelResources.get(position).getGambar().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgArtikel);

        holder.cardArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailArtikelActivity.class);
                intent.putExtra("id", listArtikelResources.get(position).getId().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listArtikelResources.size();
    }

    public class ListArtikelHomeViewHolder extends RecyclerView.ViewHolder {

        CardView cardArtikel;
        RoundedImageView imgArtikel;
        TextView txtJudul, txtAuthor;

        public ListArtikelHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            cardArtikel = itemView.findViewById(R.id.card_artikel);
            imgArtikel = itemView.findViewById(R.id.img_artikel);
            txtJudul = itemView.findViewById(R.id.text_judul);
            txtAuthor = itemView.findViewById(R.id.text_author);
        }
    }
}
