package dev.app.enak.Implementation;

import android.util.Log;

import java.io.File;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.GetUserResponse;
import dev.app.enak.Model.LoginResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Model.SuksesResponse;
import dev.app.enak.Presenter.PengaturanAkunUserPresenter;
import dev.app.enak.View.MvpView.PengaturanAkunUserMvp;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaturanAkunUserImpl implements PengaturanAkunUserPresenter {

    PengaturanAkunUserMvp pengaturanAkunUserMvp;
    GetUserResource getUserResource;

    public PengaturanAkunUserImpl(PengaturanAkunUserMvp pengaturanAkunUserMvp){
        this.pengaturanAkunUserMvp = pengaturanAkunUserMvp;
    }

    @Override
    public void ubahPassword(String id, String PasswodLama, final String PasswordBaru, final String nik) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<SuksesResponse> call = service.ubahPasswordUser(id, PasswodLama, PasswordBaru);
        call.enqueue(new Callback<SuksesResponse>() {
            @Override
            public void onResponse(Call<SuksesResponse> call, Response<SuksesResponse> response) {
                Log.d("dataaaaaaa", "response = "+response.body().getStatus().toString());
               if (response.body().getStatus().equals("Sukses")){
                    pengaturanAkunUserMvp.Relogin(nik,PasswordBaru);
                } else {
                    pengaturanAkunUserMvp.PostFailPass(response.body().getMessage());
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
        Call<SuksesResponse> call = apiServices.ubahFotoUser(id, partImage);
        call.enqueue(new Callback<SuksesResponse>() {
            @Override
            public void onResponse(Call<SuksesResponse> call, Response<SuksesResponse> response) {
                Log.d("dataaaaaaaaaa", "ON RESPONSE  : " + response.body().getStatus());
                if (response.body().getStatus().equals("Sukses")){
                    pengaturanAkunUserMvp.PostSuccessUpload();
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
    public void ubahNama(Integer id, String nama_tampilan) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.ubahNamaUser(id, nama_tampilan);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    pengaturanAkunUserMvp.PostSuccess();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void Relogin(String nik, String password) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<LoginResponse> call = service.loginUser(nik, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    pengaturanAkunUserMvp.PostSuccessPass(response.body().getData());
                    Log.d("dataaaaaaaaaa", "masuk login "+response.body().getData());
                } else {
                    Log.d("dataaaaaaaaaa", "ga masuk login");
                    pengaturanAkunUserMvp.PostFail();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                pengaturanAkunUserMvp.PostFail();
            }
        });
    }

    @Override
    public void getUser(String id) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<GetUserResponse> call = service.getUser(id);
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                getUserResource = response.body().getData();
                Log.d("dataaaaaaaaa", "respon "+response.body().getStatus());
               if (getUserResource != null){
                   Log.d("dataaaaaaaaa", "masuk get user");
                    pengaturanAkunUserMvp.LoadData(getUserResource);
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
