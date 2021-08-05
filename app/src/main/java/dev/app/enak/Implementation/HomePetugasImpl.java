package dev.app.enak.Implementation;

import android.util.Log;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Model.GetPetugasResponse;
import dev.app.enak.Model.GetUserResponse;
import dev.app.enak.Presenter.HomePetugasPresenter;
import dev.app.enak.View.MvpView.HomePetugasMvp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePetugasImpl implements HomePetugasPresenter {

    HomePetugasMvp homePetugasMvp;
    GetPetugasResource getPetugasResource;

    public HomePetugasImpl(HomePetugasMvp homePetugasMvp){
        this.homePetugasMvp = homePetugasMvp;
    }

    @Override
    public void getPetugas(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<GetPetugasResponse> call = service.getPetugas(id);
        call.enqueue(new Callback<GetPetugasResponse>() {
            @Override
            public void onResponse(Call<GetPetugasResponse> call, Response<GetPetugasResponse> response) {
                getPetugasResource = response.body().getData();
                if (getPetugasResource == null){
                    Log.d("dataaaaaa", "response: "+response.body().getStatus());
                    homePetugasMvp.DataNUll();
                } else if (getPetugasResource != null){
                    homePetugasMvp.LoadData(getPetugasResource);
                }
            }

            @Override
            public void onFailure(Call<GetPetugasResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
