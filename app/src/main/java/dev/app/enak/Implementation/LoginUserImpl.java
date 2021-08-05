package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.LoginResponse;
import dev.app.enak.Presenter.LoginUserPresenter;
import dev.app.enak.View.MvpView.LoginUserMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserImpl implements LoginUserPresenter {

    LoginUserMvp loginUserMvp;
    LoginResponse loginResponse;

    public LoginUserImpl(LoginUserMvp loginUserMvp){
        this.loginUserMvp = loginUserMvp;
    }

    @Override
    public void Login(String nik, String password) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<LoginResponse> call = service.loginUser(nik, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    loginUserMvp.LoginSuccess(response.body().getData());
                    Log.d("dataaaaaaaaaa", "masuk login "+response.body().getData());
                } else {
                    Log.d("dataaaaaaaaaa", "ga masuk login");
                    loginUserMvp.LoginFail(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                loginUserMvp.LoginFail(t.getMessage());
            }
        });
    }
}
