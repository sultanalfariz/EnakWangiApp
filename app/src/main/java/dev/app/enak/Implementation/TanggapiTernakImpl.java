package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailTernakResource;
import dev.app.enak.Model.DetailTernakResponse;
import dev.app.enak.Model.LoginPetugasResponse;
import dev.app.enak.Model.SuksesResponse;
import dev.app.enak.Presenter.TanggapiTernakPresenter;
import dev.app.enak.View.MvpView.TanggapiTernakMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TanggapiTernakImpl implements TanggapiTernakPresenter {

    TanggapiTernakMvp tanggapiTernakMvp;
    List<DetailTernakResource> detailTernakResource = new ArrayList<>();

    public TanggapiTernakImpl (TanggapiTernakMvp tanggapiTernakMvp){
        this.tanggapiTernakMvp = tanggapiTernakMvp;
    }

    @Override
    public void tinjauTernak(String id_ternak, String tgl_peninjauan) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<SuksesResponse> call = service.tanggapiTernak(id_ternak, tgl_peninjauan);
        call.enqueue(new Callback<SuksesResponse>() {
            @Override
            public void onResponse(Call<SuksesResponse> call, Response<SuksesResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    tanggapiTernakMvp.PostSuccess();
                } else {
                    tanggapiTernakMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<SuksesResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                tanggapiTernakMvp.PostFailed();
            }
        });
    }

    @Override
    public void detailTernak(String id_ternak) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<DetailTernakResponse> call = service.detailTernak(id_ternak);
        call.enqueue(new Callback<DetailTernakResponse>() {
            @Override
            public void onResponse(Call<DetailTernakResponse> call, Response<DetailTernakResponse> response) {
                detailTernakResource = response.body().getData();

                if (detailTernakResource == null){
                    tanggapiTernakMvp.DataNull();
                } else if (detailTernakResource != null){
                    tanggapiTernakMvp.LoadData(detailTernakResource);
                }
            }

            @Override
            public void onFailure(Call<DetailTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
