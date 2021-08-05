package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailArtikelResource;
import dev.app.enak.Model.DetailArtikelResponse;
import dev.app.enak.Model.ListArtikelResponse;
import dev.app.enak.Presenter.DetailArtikelPresenter;
import dev.app.enak.View.MvpView.DetailArtikelMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailArtikelImpl implements DetailArtikelPresenter {

    DetailArtikelResource detailArtikelResource;
    DetailArtikelMvp detailArtikelMvp;

    public DetailArtikelImpl (DetailArtikelMvp detailArtikelMvp){
        this.detailArtikelMvp = detailArtikelMvp;
    }

    @Override
    public void DetailArtikel(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<DetailArtikelResponse> call = service.detailArtikel(id);
        call.enqueue(new Callback<DetailArtikelResponse>() {
            @Override
            public void onResponse(Call<DetailArtikelResponse> call, Response<DetailArtikelResponse> response) {
                detailArtikelResource = response.body().getData();

                if (detailArtikelResource == null){
                    detailArtikelMvp.DataNull();
                } else if (detailArtikelResource != null){
                    detailArtikelMvp.LoadData(detailArtikelResource);
                }
            }

            @Override
            public void onFailure(Call<DetailArtikelResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
