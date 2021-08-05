package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListIbResponse;
import dev.app.enak.Model.ListLaporanResponse;
import dev.app.enak.Presenter.ListIbPemilikPresenter;
import dev.app.enak.View.MvpView.ListIbPemilikMvp;
import dev.app.enak.View.MvpView.ListLaporanPemilikMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListIbPemilikImpl implements ListIbPemilikPresenter {

    ListIbPemilikMvp listIbPemilikMvp;

    public ListIbPemilikImpl(ListIbPemilikMvp listIbPemilikMvp){
        this.listIbPemilikMvp = listIbPemilikMvp;
    }

    @Override
    public void listIbWait(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListIbResponse> call = service.listIbPemilikWait(id);
        call.enqueue(new Callback<ListIbResponse>() {
            @Override
            public void onResponse(Call<ListIbResponse> call, Response<ListIbResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listIbPemilikMvp.LoadIbWait(response.body().getData());
                } else {
                    listIbPemilikMvp.DataEmpty(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ListIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listIbProses(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListIbResponse> call = service.listIbPemilikProses(id);
        call.enqueue(new Callback<ListIbResponse>() {
            @Override
            public void onResponse(Call<ListIbResponse> call, Response<ListIbResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listIbPemilikMvp.LoadIbProses(response.body().getData());
                } else {
                    listIbPemilikMvp.DataEmpty(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ListIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listIbSelesai(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListIbResponse> call = service.listIbPemilikSelesai(id);
        call.enqueue(new Callback<ListIbResponse>() {
            @Override
            public void onResponse(Call<ListIbResponse> call, Response<ListIbResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listIbPemilikMvp.LoadIbSelesai(response.body().getData());
                } else {
                    listIbPemilikMvp.DataEmpty(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ListIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
