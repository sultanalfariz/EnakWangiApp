package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListTernakResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Presenter.TambahIbPresenter;
import dev.app.enak.View.MvpView.TambahIbMvp;
import dev.app.enak.View.MvpView.TambahLaporanMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahIbImpl implements TambahIbPresenter {

    TambahIbMvp tambahIbMvp;

    public TambahIbImpl(TambahIbMvp tambahIbMvp){
        this.tambahIbMvp = tambahIbMvp;
    }

    @Override
    public void TambahIb(Integer id_ternak, Integer id_pemilik, String keterangan) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.mintaIb(id_ternak, id_pemilik, keterangan);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()){
                    tambahIbMvp.PostSuccess();
                }else {
                    Log.d("dataaaaaa", "error "+response.body().toString());
                    tambahIbMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void LoadTernak(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListTernakResponse> call = service.listTernakPemilikVerif(id);
        call.enqueue(new Callback<ListTernakResponse>() {
            @Override
            public void onResponse(Call<ListTernakResponse> call, Response<ListTernakResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    tambahIbMvp.LoadDataTernak(response.body().getData());
                } else {
                    tambahIbMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
