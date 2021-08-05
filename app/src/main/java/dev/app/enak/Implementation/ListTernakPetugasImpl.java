package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.ListTernakResponse;
import dev.app.enak.Presenter.ListTernakPetugasPresenter;
import dev.app.enak.View.MvpView.ListTernakPetugasMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTernakPetugasImpl implements ListTernakPetugasPresenter {

    ListTernakPetugasMvp listTernakPetugasMvp;

    public ListTernakPetugasImpl(ListTernakPetugasMvp listTernakPetugasMvp){
        this.listTernakPetugasMvp = listTernakPetugasMvp;
    }

    @Override
    public void listTernakWaitVerify() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListTernakResponse> call = service.listTernakPetugasWaitVerify();
        call.enqueue(new Callback<ListTernakResponse>() {
            @Override
            public void onResponse(Call<ListTernakResponse> call, Response<ListTernakResponse> response) {
//                listTernakResources = response.body().getData();
//
//                Log.d("dataaaaaaaaaa", "data = "+listTernakResources.size());

                if (response.body().getStatus().equals("Sukses")){
                    listTernakPetugasMvp.LoadTernakWaitVerify(response.body().getData());
                } else {
                    listTernakPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listTernakWaitTinjau() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListTernakResponse> call = service.listTernakPetugasPeninjauan();
        call.enqueue(new Callback<ListTernakResponse>() {
            @Override
            public void onResponse(Call<ListTernakResponse> call, Response<ListTernakResponse> response) {
//                listTernakResources = response.body().getData();
//
//                Log.d("dataaaaaaaaaa", "data = "+listTernakResources.size());

                if (response.body().getStatus().equals("Sukses")){
                    listTernakPetugasMvp.LoadTernakWaitTinjau(response.body().getData());
                } else {
                    listTernakPetugasMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
