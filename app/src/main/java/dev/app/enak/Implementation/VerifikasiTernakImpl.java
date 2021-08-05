package dev.app.enak.Implementation;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.DetailTernakResource;
import dev.app.enak.Model.DetailTernakResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Model.SuksesResponse;
import dev.app.enak.Presenter.VerifikasiTernakPresenter;
import dev.app.enak.View.MvpView.VerifikasiTernakMvp;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiTernakImpl implements VerifikasiTernakPresenter {

    VerifikasiTernakMvp verifikasiTernakMvp;
    List<DetailTernakResource> detailTernakResource = new ArrayList<>();

    public VerifikasiTernakImpl(VerifikasiTernakMvp verifikasiTernakMvp){
        this.verifikasiTernakMvp = verifikasiTernakMvp;
    }

    @Override
    public void verifyTernak(String id_ternak, String nama_ternak, String jenis_ternak, String jenis_kelamin, String kode_induk, String kode_pejantan, String tgl_lahir) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<SuksesResponse> call = service.verifikasiTernak(id_ternak, nama_ternak, jenis_ternak, jenis_ternak, kode_induk, kode_pejantan, tgl_lahir);
        call.enqueue(new Callback<SuksesResponse>() {
            @Override
            public void onResponse(Call<SuksesResponse> call, Response<SuksesResponse> response) {
                if (response.body().getStatus().equals("Sukses")){
                    verifikasiTernakMvp.VerifySuccess();
                } else {
                    verifikasiTernakMvp.VerifyFail(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SuksesResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "error = "+t.getMessage());
                verifikasiTernakMvp.VerifyFail(t.getMessage());
            }
        });
    }

    @Override
    public void changeImg(Integer id_ternak, File gambar_depan) {
        Log.d("dataaaaaaaaaaaaaaa","upload");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),gambar_depan);
        Log.d("dataaaaa requestbody","data ="+requestBody);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("gambar_depan",gambar_depan.getName(),requestBody);
        Log.d("dataaaaa part","data ="+partImage);
        API_Service apiServices = BaseService.getApiClient().create(API_Service.class);
        Call<SuksesResponse> call = apiServices.ubahFotoTernak(id_ternak, partImage);
        call.enqueue(new Callback<SuksesResponse>() {
            @Override
            public void onResponse(Call<SuksesResponse> call, Response<SuksesResponse> response) {
                Log.d("dataaaaaaaaaa", "ON RESPONSE  : " + response.body().getStatus());
                if (response.body().getStatus().equals("Sukses")){
                    verifikasiTernakMvp.ChangeImgSuccess();
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
    public void detailTernak(String id_ternak) {
        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<DetailTernakResponse> call = service.detailTernak(id_ternak);
        call.enqueue(new Callback<DetailTernakResponse>() {
            @Override
            public void onResponse(Call<DetailTernakResponse> call, Response<DetailTernakResponse> response) {
                detailTernakResource = response.body().getData();

                if (detailTernakResource == null){
                    verifikasiTernakMvp.DataNull();
                } else if (detailTernakResource != null){
                    verifikasiTernakMvp.LoadData(detailTernakResource);
                }
            }

            @Override
            public void onFailure(Call<DetailTernakResponse> call, Throwable t) {
                Log.d("dataaaaaaa", "error = "+t.toString());
            }
        });
    }
}
