package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTernakResource {

    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("id_pemilik")
    @Expose
    private Integer idPemilik;
    @SerializedName("nama_ternak")
    @Expose
    private String namaTernak;
    @SerializedName("jenis_ternak")
    @Expose
    private String jenisTernak;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("kode_induk")
    @Expose
    private String kodeInduk;
    @SerializedName("kode_pejantan")
    @Expose
    private String kodePejantan;
    @SerializedName("alamat_kandang")
    @Expose
    private String alamatKandang;
    @SerializedName("kel_kandang")
    @Expose
    private String kelKandang;
    @SerializedName("kec_kandang")
    @Expose
    private String kecKandang;
    @SerializedName("rt")
    @Expose
    private String rt;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gambar_depan")
    @Expose
    private String gambarDepan;
    @SerializedName("tgl_peninjauan")
    @Expose
    private String tglPeninjauan;

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
    }

    public Integer getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(Integer idPemilik) {
        this.idPemilik = idPemilik;
    }

    public String getNamaTernak() {
        return namaTernak;
    }

    public void setNamaTernak(String namaTernak) {
        this.namaTernak = namaTernak;
    }

    public String getJenisTernak() {
        return jenisTernak;
    }

    public void setJenisTernak(String jenisTernak) {
        this.jenisTernak = jenisTernak;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getKodeInduk() {
        return kodeInduk;
    }

    public void setKodeInduk(String kodeInduk) {
        this.kodeInduk = kodeInduk;
    }

    public String getKodePejantan() {
        return kodePejantan;
    }

    public void setKodePejantan(String kodePejantan) {
        this.kodePejantan = kodePejantan;
    }

    public String getAlamatKandang() {
        return alamatKandang;
    }

    public void setAlamatKandang(String alamatKandang) {
        this.alamatKandang = alamatKandang;
    }

    public String getKelKandang() {
        return kelKandang;
    }

    public void setKelKandang(String kelKandang) {
        this.kelKandang = kelKandang;
    }

    public String getKecKandang() {
        return kecKandang;
    }

    public void setKecKandang(String kecKandang) {
        this.kecKandang = kecKandang;
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

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGambarDepan() {
        return gambarDepan;
    }

    public void setGambarDepan(String gambarDepan) {
        this.gambarDepan = gambarDepan;
    }

    public String getTglPeninjauan() {
        return tglPeninjauan;
    }

    public void setTglPeninjauan(String tglPeninjauan) {
        this.tglPeninjauan = tglPeninjauan;
    }
}
