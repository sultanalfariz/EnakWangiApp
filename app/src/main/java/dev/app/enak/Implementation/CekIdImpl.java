package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.CekIdResponse;
import dev.app.enak.Model.LoginResponse;
import dev.app.enak.Presenter.CekIdPresenter;
import dev.app.enak.View.MvpView.CekIdMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CekIdImpl implements CekIdPresenter {
;
    CekIdMvp cekIdMvp;
    CekIdResponse cekIdResponse;

    public  CekIdImpl (CekIdMvp cekIdMvp){
        this.cekIdMvp = cekIdMvp;
    }

    @Override
    public void CekIdentitas(String nik, String tgl_lahir) {
        API_Service service =  BaseService.getApiClient().create(API_Service.class);
        Call<CekIdResponse> call = service.cekIdentitas(nik, tgl_lahir);
        call.enqueue(new Callback<CekIdResponse>() {
            @Override
            public void onResponse(Call<CekIdResponse> call, Response<CekIdResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    Log.d("dataaaaaaaa", "masuk 1");
                    cekIdMvp.CekSuccess(response.body().getData());
                } else if(response.body().getStatus() == null){
                    cekIdMvp.CekFail("Coba lagi");
                } else {
                    cekIdMvp.CekFail(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CekIdResponse> call, Throwable t) {
                Log.d("dataaaaaaaa", "err = "+t.toString());
            }
        });
    }
}
