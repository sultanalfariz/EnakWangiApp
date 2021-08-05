package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.DetailLaporanResponse;
import dev.app.enak.Model.DetailTernakResponse;
import dev.app.enak.Presenter.DetailLaporanPresenter;
import dev.app.enak.View.MvpView.DetailLaporanMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLaporanImpl implements DetailLaporanPresenter {

    DetailLaporanMvp detailLaporanMvp;
    List<DetailLaporanResource> detailLaporanResources = new ArrayList<>();

    public DetailLaporanImpl (DetailLaporanMvp detailLaporanMvp){
        this.detailLaporanMvp = detailLaporanMvp;
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
                    detailLaporanMvp.DataNull();
                } else if (detailLaporanResources != null){
                    detailLaporanMvp.LoadData(detailLaporanResources);
                }
            }

            @Override
            public void onFailure(Call<DetailLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
