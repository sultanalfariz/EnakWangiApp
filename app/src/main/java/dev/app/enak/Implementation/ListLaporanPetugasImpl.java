package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListLaporanResponse;
import dev.app.enak.Presenter.ListLaporanPetugasPresenter;
import dev.app.enak.View.MvpView.ListLaporanPetugasMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLaporanPetugasImpl implements ListLaporanPetugasPresenter {

    ListLaporanPetugasMvp listLaporanPetugasMvp;

    public ListLaporanPetugasImpl(ListLaporanPetugasMvp listLaporanPetugasMvp){
        this.listLaporanPetugasMvp = listLaporanPetugasMvp;
    }

    @Override
    public void listLaporanWait() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListLaporanResponse> call = service.listLaporanPetugasWait();
        call.enqueue(new Callback<ListLaporanResponse>() {
            @Override
            public void onResponse(Call<ListLaporanResponse> call, Response<ListLaporanResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listLaporanPetugasMvp.LoadLaporanWait(response.body().getData());
                } else {
                    listLaporanPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listLaporanTinjau() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListLaporanResponse> call = service.listLaporanPetugasTinjau();
        call.enqueue(new Callback<ListLaporanResponse>() {
            @Override
            public void onResponse(Call<ListLaporanResponse> call, Response<ListLaporanResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listLaporanPetugasMvp.LoadLaporanTinjau(response.body().getData());
                } else {
                    listLaporanPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listLaporanSelesai() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListLaporanResponse> call = service.listLaporanPetugasSelesai();
        call.enqueue(new Callback<ListLaporanResponse>() {
            @Override
            public void onResponse(Call<ListLaporanResponse> call, Response<ListLaporanResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listLaporanPetugasMvp.LoadLaporanSelesai(response.body().getData());
                } else {
                    listLaporanPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListLaporanResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
