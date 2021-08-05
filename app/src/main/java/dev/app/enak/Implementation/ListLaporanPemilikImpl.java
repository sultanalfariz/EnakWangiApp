package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListLaporanResponse;
import dev.app.enak.Model.ListTernakResponse;
import dev.app.enak.Presenter.ListLaporanPemilikPresenter;
import dev.app.enak.View.MvpView.ListLaporanPemilikMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLaporanPemilikImpl implements ListLaporanPemilikPresenter {

    ListLaporanPemilikMvp listLaporanPemilikMvp;

    public ListLaporanPemilikImpl(ListLaporanPemilikMvp listLaporanPemilikMvp){
        this.listLaporanPemilikMvp = listLaporanPemilikMvp;
    }

    @Override
    public void listLaporan(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListLaporanResponse> call = service.listLaporanBelumPemilik(id);
        call.enqueue(new Callback<ListLaporanResponse>() {
            @Override
            public void onResponse(Call<ListLaporanResponse> call, Response<ListLaporanResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listLaporanPemilikMvp.LoadLaporan(response.body().getData());
                } else {
                    listLaporanPemilikMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listLaporanSelesai(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListLaporanResponse> call = service.listLaporanPemilikSelesai(id);
        call.enqueue(new Callback<ListLaporanResponse>() {
            @Override
            public void onResponse(Call<ListLaporanResponse> call, Response<ListLaporanResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listLaporanPemilikMvp.LoadLaporanSelesai(response.body().getData());
                } else {
                    listLaporanPemilikMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
