package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailArtikelResponse;
import dev.app.enak.Model.DetailTernakResource;
import dev.app.enak.Model.DetailTernakResponse;
import dev.app.enak.Presenter.DetailTernakPresenter;
import dev.app.enak.View.Activity.DetailTernakActivity;
import dev.app.enak.View.MvpView.DetailTernakMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTernakImpl implements DetailTernakPresenter {

    DetailTernakMvp detailTernakMvp;
    List<DetailTernakResource> detailTernakResource = new ArrayList<>();

    public DetailTernakImpl (DetailTernakMvp detailTernakMvp){
        this.detailTernakMvp = detailTernakMvp;
    }

    @Override
    public void DetailTernak(String id_ternak) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<DetailTernakResponse> call = service.detailTernak(id_ternak);
        call.enqueue(new Callback<DetailTernakResponse>() {
            @Override
            public void onResponse(Call<DetailTernakResponse> call, Response<DetailTernakResponse> response) {
                detailTernakResource = response.body().getData();

                if (detailTernakResource == null){
                    detailTernakMvp.DataNull();
                } else if (detailTernakResource != null){
                    detailTernakMvp.LoadData(detailTernakResource);
                }
            }

            @Override
            public void onFailure(Call<DetailTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
