package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserResource {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_kk")
    @Expose
    private String idKk;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("nama_tampilan")
    @Expose
    private String namaTampilan;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("rt")
    @Expose
    private String rt;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("agama")
    @Expose
    private String agama;
    @SerializedName("pendidikan")
    @Expose
    private String pendidikan;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("ternak_wait")
    @Expose
    private Integer ternakWait;
    @SerializedName("ternak_verify")
    @Expose
    private Integer ternakVerify;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdKk() {
        return idKk;
    }

    public void setIdKk(String idKk) {
        this.idKk = idKk;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNamaTampilan() {
        return namaTampilan;
    }

    public void setNamaTampilan(String namaTampilan) {
        this.namaTampilan = namaTampilan;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTernakWait() {
        return ternakWait;
    }

    public void setTernakWait(Integer ternakWait) {
        this.ternakWait = ternakWait;
    }

    public Integer getTernakVerify() {
        return ternakVerify;
    }

    public void setTernakVerify(Integer ternakVerify) {
        this.ternakVerify = ternakVerify;
    }
}
