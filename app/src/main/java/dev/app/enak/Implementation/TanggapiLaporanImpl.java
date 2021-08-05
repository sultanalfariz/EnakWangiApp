package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.DetailLaporanResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Model.SuksesResponse;
import dev.app.enak.Presenter.TanggapiLaporanPresenter;
import dev.app.enak.View.MvpView.TanggapiLaporanMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TanggapiLaporanImpl implements TanggapiLaporanPresenter {

    TanggapiLaporanMvp tanggapiLaporanMvp;
    List<DetailLaporanResource> detailLaporanResources = new ArrayList<>();

    public TanggapiLaporanImpl(TanggapiLaporanMvp tanggapiLaporanMvp){
        this.tanggapiLaporanMvp = tanggapiLaporanMvp;
    }

    @Override
    public void tanggapiLaporan(String id_laporan, Integer id_petugas, String keterangan, String tgl_peninjauan) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.tanggapiLaporan(id_laporan, id_petugas, keterangan, tgl_peninjauan);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    tanggapiLaporanMvp.PostSuccess();
                } else {
                    tanggapiLaporanMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                tanggapiLaporanMvp.PostFailed();
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
                    tanggapiLaporanMvp.DataNull();
                } else if (detailLaporanResources != null){
                    tanggapiLaporanMvp.LoadData(detailLaporanResources);
                }
            }

            @Override
            public void onFailure(Call<DetailLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
