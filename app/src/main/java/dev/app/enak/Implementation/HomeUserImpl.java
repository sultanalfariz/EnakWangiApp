package dev.app.enak.Implementation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.GetUserResponse;
import dev.app.enak.Model.ListArtikelResource;
import dev.app.enak.Model.ListArtikelResponse;
import dev.app.enak.Presenter.HomeUserPresenter;
import dev.app.enak.View.MvpView.HomeUserMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeUserImpl implements HomeUserPresenter {

    HomeUserMvp homeUserMvp;
    GetUserResource getUserResource;
//    List<ListArtikelResource> listArtikelResources = new ArrayList<>();

    public HomeUserImpl(HomeUserMvp homeUserMvp){
        this.homeUserMvp = homeUserMvp;
    }

    @Override
    public void getUser(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<GetUserResponse> call = service.getUser(id);
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                getUserResource = response.body().getData();
                if (getUserResource == null){
                    homeUserMvp.DataNull();
                } else if (getUserResource != null){
                    homeUserMvp.loadData(getUserResource);
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }

    @Override
    public void listArtikel() {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<ListArtikelResponse> call = service.listArtikel();
        call.enqueue(new Callback<ListArtikelResponse>() {
            @Override
            public void onResponse(Call<ListArtikelResponse> call, Response<ListArtikelResponse> response) {
//                listArtikelResources = response.body().getData();

                if (response.body().getStatus().equals("Sukses")){
                    homeUserMvp.loadArtikel(response.body().getData());
                } else {
                    homeUserMvp.DataEmpty(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ListArtikelResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
