package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Model.DetailIbResponse;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.DetailLaporanResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Presenter.DetailIbPresenter;
import dev.app.enak.View.MvpView.DetailIbMvp;
import dev.app.enak.View.MvpView.DetailLaporanMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailIbPemilikImpl implements DetailIbPresenter {

    DetailIbMvp detailIbMvp;
    List<DetailIbResource> detailIbResources = new ArrayList<>();

    public DetailIbPemilikImpl (DetailIbMvp detailIbMvp){
        this.detailIbMvp = detailIbMvp;
    }

    @Override
    public void DetailIb(String id_permintaan_ib) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<DetailIbResponse> call = service.detailIb(id_permintaan_ib);
        call.enqueue(new Callback<DetailIbResponse>() {
            @Override
            public void onResponse(Call<DetailIbResponse> call, Response<DetailIbResponse> response) {
                detailIbResources = response.body().getData();

                if (detailIbResources == null){
                    detailIbMvp.DataNull();
                } else if (detailIbResources != null){
                    detailIbMvp.LoadData(detailIbResources);
                }
            }

            @Override
            public void onFailure(Call<DetailIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void ubahStatusIbHamil(String id_permintaan_ib, String hamil) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.ubahStatusIbHamil(id_permintaan_ib, hamil);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()){
                    detailIbMvp.PostSuccess();
                }else {
                    Log.d("dataaaaaa", "error "+response.body().toString());
                    detailIbMvp.PostFail();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void ubahStatusIbLahir(String id_permintaan_ib, String lahir) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.ubahStatusIbLahir(id_permintaan_ib, lahir);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()){
                    detailIbMvp.PostSuccess();
                }else {
                    Log.d("dataaaaaa", "error "+response.body().toString());
                    detailIbMvp.PostFail();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void inputTglLahir(String id_permintaan_ib, String tgl_lahir) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.inputTglLahir(id_permintaan_ib, tgl_lahir);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()){
                    detailIbMvp.PostTglSuccess();
                }else {
                    Log.d("dataaaaaa", "error "+response.body().toString());
                    detailIbMvp.PostFail();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
    }
}
