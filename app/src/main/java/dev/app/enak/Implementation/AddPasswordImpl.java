package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.GetUserResponse;
import dev.app.enak.Model.LoginResponse;
import dev.app.enak.Presenter.AddPasswordPresenter;
import dev.app.enak.View.MvpView.AddPasswordMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPasswordImpl implements AddPasswordPresenter {

    AddPasswordMvp addPasswordMvp;
    GetUserResponse getUserResponse;

    public AddPasswordImpl(AddPasswordMvp addPasswordMvp){
        this.addPasswordMvp = addPasswordMvp;
    }

    @Override
    public void AddPassword(String id, String password) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<GetUserResponse> call = service.addPassword(id, password);
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    addPasswordMvp.AddPasswordSuccess(response.body().getData());
                    Log.d("dataaaaaaaaaa", "masuk login "+response.body().getData());
                } else {
                    Log.d("dataaaaaaaaaa", "ga masuk login");
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
            }
        });
    }
}
