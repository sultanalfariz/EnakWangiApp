package dev.app.enak.Config;

import dev.app.enak.Model.CekIdResponse;
import dev.app.enak.Model.DetailArtikelResponse;
import dev.app.enak.Model.DetailIbResponse;
import dev.app.enak.Model.DetailLaporanResponse;
import dev.app.enak.Model.DetailTernakResponse;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Model.GetPetugasResponse;
import dev.app.enak.Model.GetUserResponse;
import dev.app.enak.Model.ListArtikelResponse;
import dev.app.enak.Model.ListIbResponse;
import dev.app.enak.Model.ListLaporanResponse;
import dev.app.enak.Model.ListTernakResponse;
import dev.app.enak.Model.LoginPetugasResponse;
import dev.app.enak.Model.LoginResponse;
import dev.app.enak.Model.StatusResponse;
import dev.app.enak.Model.SuksesResponse;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface API_Service {

    //POST
    @FormUrlEncoded
    @POST("loginPengguna")
    Call<LoginResponse> loginUser (@Field("nik") String nik,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("loginPetugas")
    Call<LoginPetugasResponse> loginPetugas (@Field("nip") String nip,
                                            @Field("password") String password);

    @FormUrlEncoded
    @POST("addPassword/{id}")
    Call<GetUserResponse> addPassword(@Path("id") String id,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("cekidentitas")
    Call<CekIdResponse> cekIdentitas(@Field("nik") String nik,
                                     @Field("tgl_lahir") String tgl_lahir);

    @Multipart
    @POST("tambahTernak")
    Call<StatusResponse> tambahTernak(@Part("id_pemilik") Integer id_pemilik,
                                      @Part("nama_ternak") RequestBody nama_ternak,
                                      @Part("jenis_ternak") RequestBody jenis_ternak,
                                      @Part("jenis_kelamin") RequestBody jenis_kelamin,
                                      @Part("kode_induk") RequestBody kode_induk,
                                      @Part("kode_pejantan") RequestBody kode_pejantan,
                                      @Part("alamat_kandang") RequestBody alamat_kandang,
                                      @Part("kel_kandang") RequestBody kel_kandang,
                                      @Part("kec_kandang") RequestBody kec_kandang,
                                      @Part("rt") RequestBody rt,
                                      @Part("rw") RequestBody rw,
                                      @Part("tgl_lahir") RequestBody tgl_lahir,
                                      @Part MultipartBody.Part gambar_depan);

    @FormUrlEncoded
    @POST("tambahLaporan")
    Call<StatusResponse> tambahLaporan (@Field("id_ternak") Integer id_ternak,
                                        @Field("id_pemilik") Integer id_pemilik,
                                        @Field("keterangan") String keterangan);

    @FormUrlEncoded
    @POST("mintaIb")
    Call<StatusResponse> mintaIb (@Field("id_ternak") Integer id_ternak,
                                @Field("id_pemilik") Integer id_pemilik,
                                @Field("keterangan") String keterangan);

    @FormUrlEncoded
    @POST("ubahStatusIbHamil/{id_permintaan_ib}")
    Call<StatusResponse> ubahStatusIbHamil(@Path("id_permintaan_ib") String id_permintaan_ib,
                                           @Field("hamil") String hamil);

    @FormUrlEncoded
    @POST("inputTglLahirIb/{id_permintaan_ib}")
    Call<StatusResponse> inputTglLahir(@Path("id_permintaan_ib") String id_permintaan_ib,
                                       @Field("tgl_lahir") String tgl_lahir);

    @FormUrlEncoded
    @POST("ubahStatusIbLahir/{id_permintaan_ib}")
    Call<StatusResponse> ubahStatusIbLahir(@Path("id_permintaan_ib") String id_permintaan_ib,
                                           @Field("lahir") String lahir);

    @FormUrlEncoded
    @POST("ubahNamaPengguna")
    Call<StatusResponse> ubahNamaUser (@Field("id") Integer id,
                                       @Field("nama_tampilan") String nama_tampilan);

    @Multipart
    @POST("ubahFotoPengguna")
    Call<SuksesResponse> ubahFotoUser (@Part("id") Integer id,
                                       @Part MultipartBody.Part foto);

    @FormUrlEncoded
    @POST("updatePasswordPengguna/{id}")
    Call<SuksesResponse> ubahPasswordUser(@Path("id") String id,
                                         @Field("passwordLama") String passwordLama,
                                         @Field("passwordBaru") String passwordBaru);

    @FormUrlEncoded
    @POST("tinjauTernak/{id_ternak}")
    Call<SuksesResponse> tanggapiTernak(@Path("id_ternak") String id_ternak,
                                      @Field("tgl_peninjauan") String tgl_peninjauan);

    @FormUrlEncoded
    @POST("verifikasiTernak/{id_ternak}")
    Call<SuksesResponse> verifikasiTernak(@Path("id_ternak") String id_ternak,
                                          @Field("nama_ternak") String nama_ternak,
                                          @Field("jenis_ternak") String jenis_ternak,
                                          @Field("jenis_kelamin") String jenis_kelamin,
                                          @Field("kode_induk") String kode_induk,
                                          @Field("kode_pejantan") String kode_pejantan,
                                          @Field("tgl_lahir") String tgl_lahir);

    @Multipart
    @POST("ubahFotoDepanTernak")
    Call<SuksesResponse> ubahFotoTernak(@Part("id_ternak") Integer id_ternak,
                                       @Part MultipartBody.Part gambar_depan);

    @FormUrlEncoded
    @POST("tanggapiLaporan/{id_laporan}")
    Call<StatusResponse> tanggapiLaporan(@Path("id_laporan") String id_laporan,
                                         @Field("id_petugas") Integer id_petugas,
                                         @Field("keterangan") String keterangan,
                                         @Field("tgl_peninjauan") String tgl_peninjauan);

    @FormUrlEncoded
    @POST("tambahLayanan/{id_laporan}")
    Call<StatusResponse> tambahLayanan(@Path("id_laporan") String id_laporan,
                                       @Field("id_ternak") Integer id_ternak,
                                       @Field("id_petugas") Integer id_petugas,
                                       @Field("jenis_obat") String jenis_obat,
                                       @Field("obat") String obat,
                                       @Field("dosis") String dosis);

    @FormUrlEncoded
    @POST("diagnosaSakit/{id_laporan}")
    Call<StatusResponse> diagnosaSakit(@Path("id_laporan") String id_laporan,
                                       @Field("id_ternak") Integer id_ternak,
                                       @Field("id_petugas") Integer id_petugas,
                                       @Field("diagnosa") String diagnosa,
                                       @Field("gejala") String gejala,
                                       @Field("morb_mort") String morb_mort);

    @FormUrlEncoded
    @POST("tanggapiIB/{id_permintaan_ib}")
    Call<StatusResponse> tanggapiIb(@Path("id_permintaan_ib") String id_permintaan_ibid_permintaan_ib,
                                     @Field("id_petugas") Integer id_petugas,
                                     @Field("keterangan") String keterangan,
                                     @Field("tgl_peninjauan") String tgl_peninjauan);

    @FormUrlEncoded
    @POST("ubahStatusIbBirahi/{id_permintaan_ib}")
    Call<StatusResponse> ubahStatusIbBirahi(@Path("id_permintaan_ib") String id_permintaan_ib,
                                            @Field("id_petugas") Integer id_petugas,
                                            @Field("birahi") String birahi,
                                            @Field("tgl_suntik_ib") String tgl_suntik_ib);

    @Multipart
    @POST("ubahFotoPetugas")
    Call<SuksesResponse> ubahFotoPetugas (@Part("id") Integer id,
                                          @Part MultipartBody.Part foto);

    @FormUrlEncoded
    @POST("updatePasswordPetugas/{id}")
    Call<SuksesResponse> ubahPasswordPetugas(@Path("id") String id,
                                            @Field("passwordLama") String passwordLama,
                                            @Field("passwordBaru") String passwordBaru);

    //GET

    @GET("getUser/{id}")
    Call<GetUserResponse> getUser(@Path("id") String id);

    @GET("getPetugas/{id}")
    Call<GetPetugasResponse> getPetugas(@Path("id") String id);

    @GET("listTernakPemilikVerify/{id}")
    Call<ListTernakResponse> listTernakPemilikVerif(@Path("id") String id);

    @GET("listTernakPemilikNotVerify/{id}")
    Call<ListTernakResponse> listTernakPemilikNotVerif(@Path("id") String id);

    @GET("listArtikel")
    Call<ListArtikelResponse> listArtikel();

    @GET("detailArtikel/{id}")
    Call<DetailArtikelResponse> detailArtikel(@Path("id") String id);

    @GET("detailTernak/{id_ternak}")
    Call<DetailTernakResponse> detailTernak(@Path("id_ternak") String id_ternak);

    @GET("laporanPemilikBlmDitanggapi/{id}")
    Call<ListLaporanResponse> listLaporanBelumPemilik(@Path("id") String id);

    @GET("laporanPemilikSelesai/{id}")
    Call<ListLaporanResponse> listLaporanPemilikSelesai(@Path("id") String id);

    @GET("detailLaporan/{id}")
    Call<DetailLaporanResponse> detailLaporan(@Path("id") String id);

    @GET("IbPemilikBlmDitanggapi/{id}")
    Call<ListIbResponse> listIbPemilikWait(@Path("id") String id);

    @GET("IbPemilikProses/{id}")
    Call<ListIbResponse> listIbPemilikProses(@Path("id") String id);

    @GET("IbPemilikSelesai/{id}")
    Call<ListIbResponse> listIbPemilikSelesai(@Path("id") String id);

    @GET("detailIb/{id}")
    Call<DetailIbResponse> detailIb(@Path("id") String id);

    @GET("listTernakPetugasWaitVerify")
    Call<ListTernakResponse> listTernakPetugasWaitVerify();

    @GET("listTernakPetugasPeninjauan")
    Call<ListTernakResponse> listTernakPetugasPeninjauan();

    @GET("laporanPetugasBlmDitanggapi")
    Call<ListLaporanResponse> listLaporanPetugasWait();

    @GET("laporanPetugasPeninjauan")
    Call<ListLaporanResponse> listLaporanPetugasTinjau();

    @GET("laporanPetugasSelesai")
    Call<ListLaporanResponse> listLaporanPetugasSelesai();

    @GET("IbPetugasBlmDitanggapi")
    Call<ListIbResponse> listIbPetugasWait();

    @GET("IbPetugasTinjau")
    Call<ListIbResponse> listIbPetugasTinjau();

    @GET("IbPetugasProses")
    Call<ListIbResponse> listIbPetugasProses();

    @GET("IbPetugasSelesai")
    Call<ListIbResponse> listIbPetugasSelesai();
}
