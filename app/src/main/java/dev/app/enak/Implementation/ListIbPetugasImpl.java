package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListIbResponse;
import dev.app.enak.Model.ListLaporanResponse;
import dev.app.enak.Presenter.ListIbPetugasPresenter;
import dev.app.enak.View.MvpView.ListIbPetugasMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListIbPetugasImpl implements ListIbPetugasPresenter {

    ListIbPetugasMvp listIbPetugasMvp;

    public ListIbPetugasImpl(ListIbPetugasMvp listIbPetugasMvp){
        this.listIbPetugasMvp = listIbPetugasMvp;
    }

    @Override
    public void listIbWait() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListIbResponse> call = service.listIbPetugasWait();
        call.enqueue(new Callback<ListIbResponse>() {
            @Override
            public void onResponse(Call<ListIbResponse> call, Response<ListIbResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listIbPetugasMvp.LoadIbWait(response.body().getData());
                } else {
                    listIbPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listIbTinjau() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListIbResponse> call = service.listIbPetugasTinjau();
        call.enqueue(new Callback<ListIbResponse>() {
            @Override
            public void onResponse(Call<ListIbResponse> call, Response<ListIbResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listIbPetugasMvp.LoadIbTinjau(response.body().getData());
                } else {
                    listIbPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listIbProses() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListIbResponse> call = service.listIbPetugasProses();
        call.enqueue(new Callback<ListIbResponse>() {
            @Override
            public void onResponse(Call<ListIbResponse> call, Response<ListIbResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listIbPetugasMvp.LoadIbProses(response.body().getData());
                } else {
                    listIbPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listIbSelesai() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListIbResponse> call = service.listIbPetugasSelesai();
        call.enqueue(new Callback<ListIbResponse>() {
            @Override
            public void onResponse(Call<ListIbResponse> call, Response<ListIbResponse> response) {

                if (response.body().getStatus().equals("Sukses")){
                    listIbPetugasMvp.LoadIbSelesai(response.body().getData());
                } else {
                    listIbPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListIbResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
