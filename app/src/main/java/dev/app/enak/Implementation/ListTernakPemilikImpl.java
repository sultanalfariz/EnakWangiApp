package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListArtikelResponse;
import dev.app.enak.Model.ListTernakResource;
import dev.app.enak.Model.ListTernakResponse;
import dev.app.enak.Presenter.ListTernakPemilikPresenter;
import dev.app.enak.View.MvpView.ListTernakPemilikMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTernakPemilikImpl implements ListTernakPemilikPresenter {

    ListTernakPemilikMvp listTernakPemilikMvp;
//    List<ListTernakResource> listTernakResources = new ArrayList<>();

    public ListTernakPemilikImpl (ListTernakPemilikMvp listTernakPemilikMvp){
        this.listTernakPemilikMvp = listTernakPemilikMvp;
    }

    @Override
    public void listTernakWait(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListTernakResponse> call = service.listTernakPemilikNotVerif(id);
        call.enqueue(new Callback<ListTernakResponse>() {
            @Override
            public void onResponse(Call<ListTernakResponse> call, Response<ListTernakResponse> response) {
//                listTernakResources = response.body().getData();
//
//                Log.d("dataaaaaaaaaa", "data = "+listTernakResources.size());

                if (response.body().getStatus().equals("Sukses")){
                    listTernakPemilikMvp.LoadTernakWait(response.body().getData());
                } else {
                    listTernakPemilikMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listTernakVerify(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListTernakResponse> call = service.listTernakPemilikVerif(id);
        call.enqueue(new Callback<ListTernakResponse>() {
            @Override
            public void onResponse(Call<ListTernakResponse> call, Response<ListTernakResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listTernakPemilikMvp.LoadTernakVerify(response.body().getData());
                } else {
                    listTernakPemilikMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
