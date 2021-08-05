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

public class RiwayatAmbAdapter extends RecyclerView.Adapter<RiwayatAmbAdapter.RiwayatAmbViewHolder> {

    List<DetailTernakResource> detailTernakResources;
    Context context;
    LayoutInflater layoutInflater;

    public RiwayatAmbAdapter (List<DetailTernakResource> detailTernakResources, Context context){
        this.detailTernakResources = detailTernakResources;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RiwayatAmbAdapter.RiwayatAmbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_riwayat_amb,parent,false);
        RiwayatAmbAdapter.RiwayatAmbViewHolder holder = new RiwayatAmbAdapter.RiwayatAmbViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatAmbAdapter.RiwayatAmbViewHolder holder, int position) {

        String riwayat = detailTernakResources.get(0).getRiwayat().get(position).getDiagnosa();
        String obat = detailTernakResources.get(0).getLayanan().get(position).getObat();
        String dosis = detailTernakResources.get(0).getLayanan().get(position).getDosis();

        holder.txtRiwayat.setText(detailTernakResources.get(0).getRiwayat().get(position).getIdLaporan()+". "
                +"Diagnosa: "+riwayat+", Obat: "+obat+" "+dosis);
    }

    @Override
    public int getItemCount() {
        return detailTernakResources.get(0).getRiwayat().size();
    }

    public class RiwayatAmbViewHolder extends RecyclerView.ViewHolder {

        TextView txtRiwayat;

        public RiwayatAmbViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRiwayat = itemView.findViewById(R.id.text_riwayat);
        }
    }
}
