package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Model.DetailIbResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Presenter.TinjauIbPresenter;
import dev.app.enak.View.MvpView.TinjauIbMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinjauIbImpl implements TinjauIbPresenter {

    TinjauIbMvp tinjauIbMvp;
    List<DetailIbResource> detailIbResources = new ArrayList<>();

    public TinjauIbImpl (TinjauIbMvp tinjauIbMvp){
        this.tinjauIbMvp = tinjauIbMvp;
    }

    @Override
    public void tanggapiIb(String id_permintaan_ib, Integer id_petugas, String birahi, String tgl_suntik_ib) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.ubahStatusIbBirahi(id_permintaan_ib, id_petugas, birahi, tgl_suntik_ib);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    tinjauIbMvp.PostSuccess();
                } else {
                    tinjauIbMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                tinjauIbMvp.PostFailed();
            }
        });
    }

    @Override
    public void DetailIb(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<DetailIbResponse> call = service.detailIb(id);
        call.enqueue(new Callback<DetailIbResponse>() {
            @Override
            public void onResponse(Call<DetailIbResponse> call, Response<DetailIbResponse> response) {
                detailIbResources = response.body().getData();

                if (detailIbResources == null){
                    tinjauIbMvp.DataNull();
                } else if (detailIbResources != null){
                    tinjauIbMvp.LoadData(detailIbResources);
                }
            }

            @Override
            public void onFailure(Call<DetailIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
