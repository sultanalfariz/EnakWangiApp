package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Model.DetailIbResponse;
import dev.app.enak.Model.ListIbResource;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Presenter.TanggapiIbPresenter;
import dev.app.enak.View.MvpView.TanggapiIbMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TanggapiIbImpl implements TanggapiIbPresenter {

    TanggapiIbMvp tanggapiIbMvp;
    List<DetailIbResource> detailIbResources = new ArrayList<>();

    public TanggapiIbImpl(TanggapiIbMvp tanggapiIbMvp){
        this.tanggapiIbMvp = tanggapiIbMvp;
    }

    @Override
    public void tanggapiIb(String id_permintaan_ib, Integer id_petugas, String keterangan, String tgl_peninjauan) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.tanggapiIb(id_permintaan_ib, id_petugas, keterangan, tgl_peninjauan);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    tanggapiIbMvp.PostSuccess();
                } else {
                    tanggapiIbMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                tanggapiIbMvp.PostFailed();
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
                    tanggapiIbMvp.DataNull();
                } else if (detailIbResources != null){
                    tanggapiIbMvp.LoadData(detailIbResources);
                }
            }

            @Override
            public void onFailure(Call<DetailIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
