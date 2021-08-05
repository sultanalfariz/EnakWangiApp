package dev.app.enak.Implementation;

import android.util.Log;

import java.io.File;

import dev.app.enak.Config.API_Service;
import dev.app.enak.Config.BaseService;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Model.SuksesResponse;
import dev.app.enak.Presenter.TambahTernakPresenter;
import dev.app.enak.View.MvpView.TambahTernakMvp;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahTernakImpl implements TambahTernakPresenter {

    TambahTernakMvp tambahTernakMvp;

    public TambahTernakImpl (TambahTernakMvp tambahTernakMvp){
        this.tambahTernakMvp = tambahTernakMvp;
    }


    @Override
    public void TambahTernak(Integer id_pemilik, String nama_ternak, String jenis_ternak, String jenis_kelamin, String kode_induk,
                             String kode_pejantan, String alamat_kandang, String kel_kandang, String kec_kandang, String rt, String rw,
                             String tgl_lahir, File gambar_depan) {

        Log.d("dataaaaaaaaaaaaaaa","upload");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),gambar_depan);
        Log.d("dataaaaa requestbody","data ="+requestBody);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("gambar_depan",gambar_depan.getName(),requestBody);
        Log.d("dataaaaa part","data ="+partImage);
        Log.d("dataaaaaa idp ", id_pemilik.toString());
        Log.d("dataaaaaa nm ", nama_ternak.toString());
        Log.d("dataaaaaa jt ", jenis_ternak.toString());
        Log.d("dataaaaaa jk", jenis_kelamin.toString());
        Log.d("dataaaaaa alt ", alamat_kandang);
        Log.d("dataaaaaa kel kec ", kel_kandang+kec_kandang);
        Log.d("dataaaaaa rt rw ", rt+rw);
        Log.d("dataaaaaa tgl ", tgl_lahir);
        Log.d("dataaaaaa partImage", partImage.toString());

        RequestBody nt = RequestBody.create(MultipartBody.FORM, nama_ternak);
        RequestBody jt = RequestBody.create(MultipartBody.FORM, jenis_ternak);
        RequestBody jk = RequestBody.create(MultipartBody.FORM, jenis_kelamin);
        RequestBody ki = RequestBody.create(MultipartBody.FORM, kode_induk);
        RequestBody kp = RequestBody.create(MultipartBody.FORM, kode_pejantan);
        RequestBody ak = RequestBody.create(MultipartBody.FORM, alamat_kandang);
        RequestBody kel = RequestBody.create(MultipartBody.FORM, kel_kandang);
        RequestBody kec = RequestBody.create(MultipartBody.FORM, kec_kandang);
        RequestBody rtr = RequestBody.create(MultipartBody.FORM, rt);
        RequestBody rwr = RequestBody.create(MultipartBody.FORM, rw);
        RequestBody tgl = RequestBody.create(MultipartBody.FORM, tgl_lahir);


        API_Service service = BaseService.getApiClient().create(API_Service.class);
        Call<StatusResponse> call = service.tambahTernak(id_pemilik, nt, jt, jk, ki, kp,
                ak, kel, kec, rtr, rwr, tgl, partImage);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()){
                    tambahTernakMvp.PostSuccess();
                }else {
                    Log.d("dataaaaaa", "error "+response.body().toString());
                    tambahTernakMvp.PostFailed();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("dataaaaaaaaaa", "ON RESPONSE Fail : " + t.getMessage().toString());
            }
        });
    }
}
