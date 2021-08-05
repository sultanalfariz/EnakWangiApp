package dev.app.enak.Implementation;

import android.util.Log;

import java.io.File;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListTernakResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Model.SuksesResponse;
import dev.app.enak.Presenter.TambahLaporanPresenter;
import dev.app.enak.View.MvpView.TambahLaporanMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahLaporanImpl implements TambahLaporanPresenter {

    TambahLaporanMvp tambahLaporanMvp;

    public TambahLaporanImpl(TambahLaporanMvp tambahLaporanMvp){
        this.tambahLaporanMvp = tambahLaporanMvp;
    }

    @Override
    public void TambahLaporn(Integer id_ternak, Integer id_pemilik, String keterangan) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.tambahLaporan(id_ternak, id_pemilik, keterangan);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()){
                    tambahLaporanMvp.PostSuccess();
                }else {
                    Log.d("dataaaaaa", "error "+response.body().toString());
                    tambahLaporanMvp.PostFailed();
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
                    tambahLaporanMvp.LoadDataTernak(response.body().getData());
                } else {
                    tambahLaporanMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
