package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.LoginPetugasResponse;
import dev.app.enak.Model.LoginResponse;
import dev.app.enak.Presenter.LoginPetugasPresenter;
import dev.app.enak.View.MvpView.LoginPetugasMvp;
import dev.app.enak.View.MvpView.LoginUserMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPetugasImpl implements LoginPetugasPresenter {

    LoginPetugasMvp loginPetugasMvp;
    LoginPetugasResponse loginPetugasResponse;

    public LoginPetugasImpl (LoginPetugasMvp loginPetugasMvp){
        this.loginPetugasMvp = loginPetugasMvp;
    }

    @Override
    public void Login(String nip, String password) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<LoginPetugasResponse> call = service.loginPetugas(nip, password);
        call.enqueue(new Callback<LoginPetugasResponse>() {
            @Override
            public void onResponse(Call<LoginPetugasResponse> call, Response<LoginPetugasResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    loginPetugasMvp.LoginSuccess(response.body().getData());
                    Log.d("dataaaaaaaaaa", "masuk login "+response.body().getData());
                } else {
                    Log.d("dataaaaaaaaaa", "ga masuk login");
                    loginPetugasMvp.LoginFail(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginPetugasResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                loginPetugasMvp.LoginFail(t.getMessage());
            }
        });
    }
}
