package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.DetailLaporanResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Presenter.TinjauLaporanPresenter;
import dev.app.enak.View.MvpView.TinjauLaporanMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinjauLaporanImpl implements TinjauLaporanPresenter {

    TinjauLaporanMvp tinjauLaporanMvp;
    List<DetailLaporanResource> detailLaporanResources = new ArrayList<>();

    public TinjauLaporanImpl(TinjauLaporanMvp tinjauLaporanMvp){
        this.tinjauLaporanMvp = tinjauLaporanMvp;
    }

    @Override
    public void tambahLayanan(String id_laporan, Integer id_ternak, Integer id_petugas, String jenis_obat, String obat, String dosis) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.tambahLayanan(id_laporan, id_ternak, id_petugas, jenis_obat, obat, dosis);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    tinjauLaporanMvp.PostSuccess();
                } else {
                    tinjauLaporanMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                tinjauLaporanMvp.PostFailed();
            }
        });
    }

    @Override
    public void diagnosaSakit(String id_laporan, Integer id_ternak, Integer id_petugas, String diagnosa, String gejala, String morb_mort) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.diagnosaSakit(id_laporan, id_ternak, id_petugas, diagnosa, gejala, morb_mort);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    tinjauLaporanMvp.PostSuccess();
                } else {
                    tinjauLaporanMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                tinjauLaporanMvp.PostFailed();
            }
        });
    }

    @Override
    public void DetailLaporan(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<DetailLaporanResponse> call = service.detailLaporan(id);
        call.enqueue(new Callback<DetailLaporanResponse>() {
            @Override
            public void onResponse(Call<DetailLaporanResponse> call, Response<DetailLaporanResponse> response) {
                detailLaporanResources = response.body().getData();

                if (detailLaporanResources == null){
                    tinjauLaporanMvp.DataNull();
                } else if (detailLaporanResources != null){
                    tinjauLaporanMvp.LoadData(detailLaporanResources);
                }
            }

            @Override
            public void onFailure(Call<DetailLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
