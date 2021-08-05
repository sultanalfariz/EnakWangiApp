package dev.app.enak.Implementation;

import android.util.Log;

import java.io.File;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Model.GetPetugasResponse;
import dev.app.enak.Model.LoginPetugasResponse;
import dev.app.enak.Model.LoginResponse;
import dev.app.enak.Model.SuksesResponse;
import dev.app.enak.Presenter.PengaturanAkunPetugasPresenter;
import dev.app.enak.View.MvpView.PengaturanAkunPetugasMvp;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaturanAkunPetugasImpl implements PengaturanAkunPetugasPresenter {

    PengaturanAkunPetugasMvp pengaturanAkunPetugasMvp;
    GetPetugasResource getPetugasResource;

    public PengaturanAkunPetugasImpl(PengaturanAkunPetugasMvp pengaturanAkunPetugasMvp){
        this.pengaturanAkunPetugasMvp = pengaturanAkunPetugasMvp;
    }

    @Override
    public void ubahPassword(String id, String PasswodLama, final String PasswordBaru, final String nip) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<SuksesResponse> call = service.ubahPasswordPetugas(id, PasswodLama, PasswordBaru);
        call.enqueue(new Callback<SuksesResponse>() {
            @Override
            public void onResponse(Call<SuksesResponse> call, Response<SuksesResponse> response) {
                Log.d("dataaaaaaa", "response = "+response.body().getStatus().toString());
                if (response.body().getStatus().equals("Sukses")){
                    pengaturanAkunPetugasMvp.Relogin(nip,PasswordBaru);
                } else {
                    pengaturanAkunPetugasMvp.PostFailPass(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SuksesResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void ubahFoto(Integer id, File foto) {
        Log.d("dataaaaaaaaaaaaaaa","upload");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),foto);
        Log.d("dataaaaa requestbody","data ="+requestBody);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("foto",foto.getName(),requestBody);
        Log.d("dataaaaa part","data ="+partImage);
        API_Service apiServices = BaseService.getApiClient().create(API_Service.class);
        Call<SuksesResponse> call = apiServices.ubahFotoPetugas(id, partImage);
        call.enqueue(new Callback<SuksesResponse>() {
            @Override
            public void onResponse(Call<SuksesResponse> call, Response<SuksesResponse> response) {
                Log.d("dataaaaaaaaaa", "ON RESPONSE  : " + response.body().getStatus());
                if (response.body().getStatus().equals("Sukses")){
                    pengaturanAkunPetugasMvp.PostSuccess();
                }else {
                    Log.d("dataaaaaaaaaaa","gagal");
                }
            }

            @Override
            public void onFailure(Call<SuksesResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "ON RESPONSE Fail : " + t);
            }
        });
    }

    @Override
    public void Relogin(String nip, String password) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<LoginPetugasResponse> call = service.loginPetugas(nip, password);
        call.enqueue(new Callback<LoginPetugasResponse>() {
            @Override
            public void onResponse(Call<LoginPetugasResponse> call, Response<LoginPetugasResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    pengaturanAkunPetugasMvp.PostSuccessPass(response.body().getData());
                    Log.d("dataaaaaaaaaa", "masuk login "+response.body().getData());
                } else {
                    Log.d("dataaaaaaaaaa", "ga masuk login");
                    pengaturanAkunPetugasMvp.PostFail();
                }
            }

            @Override
            public void onFailure(Call<LoginPetugasResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                pengaturanAkunPetugasMvp.PostFail();
            }
        });
    }

    @Override
    public void getPetugas(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<GetPetugasResponse> call = service.getPetugas(id);
        call.enqueue(new Callback<GetPetugasResponse>() {
            @Override
            public void onResponse(Call<GetPetugasResponse> call, Response<GetPetugasResponse> response) {
                getPetugasResource = response.body().getData();
                if (getPetugasResource != null){
                    pengaturanAkunPetugasMvp.LoadData(getPetugasResource);
                }
            }

            @Override
            public void onFailure(Call<GetPetugasResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
