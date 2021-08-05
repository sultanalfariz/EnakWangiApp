package dev.app.enak.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.app.enak.Model.DetailTernakResource;
import dev.app.enak.R;

public class RiwayatIbAdapter extends RecyclerView.Adapter<RiwayatIbAdapter.RiwayatIbViewHolder> {

    List<DetailTernakResource> detailTernakResources;
    Context context;
    LayoutInflater layoutInflater;

    public RiwayatIbAdapter (List<DetailTernakResource> detailTernakResources, Context context){
        this.detailTernakResources = detailTernakResources;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RiwayatIbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_riwayat_ib,parent,false);
        RiwayatIbViewHolder holder = new RiwayatIbViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatIbViewHolder holder, int position) {

        String birahi = detailTernakResources.get(0).getIb().get(position).getBirahi();
        String hamil = detailTernakResources.get(0).getIb().get(position).getHamil();
        String lahir = detailTernakResources.get(0).getIb().get(position).getLahir();

        holder.txtRiwayat.setText(detailTernakResources.get(0).getIb().get(position).getId()+". "
            +"Birahi: "+birahi+", Hamil: "+hamil+", Lahir: "+lahir);
    }

    @Override
    public int getItemCount() {
        return detailTernakResources.get(0).getIb().size();
    }

    public class RiwayatIbViewHolder extends RecyclerView.ViewHolder {

        TextView txtRiwayat;

        public RiwayatIbViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRiwayat = itemView.findViewById(R.id.text_riwayat);
        }
    }
}
