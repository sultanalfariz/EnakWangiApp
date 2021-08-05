package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListIbResource {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_ternak")
    @Expose
    private String idTernak;
    @SerializedName("nama_ternak")
    @Expose
    private String namaTernak;
    @SerializedName("gambar_depan")
    @Expose
    private String gambarDepan;
    @SerializedName("id_pemilik")
    @Expose
    private String idPemilik;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tgl_peninjauan")
    @Expose
    private String tglPeninjauan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(String idTernak) {
        this.idTernak = idTernak;
    }

    public String getNamaTernak() {
        return namaTernak;
    }

    public void setNamaTernak(String namaTernak) {
        this.namaTernak = namaTernak;
    }

    public String getGambarDepan() {
        return gambarDepan;
    }

    public void setGambarDepan(String gambarDepan) {
        this.gambarDepan = gambarDepan;
    }

    public String getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(String idPemilik) {
        this.idPemilik = idPemilik;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglPeninjauan() {
        return tglPeninjauan;
    }

    public void setTglPeninjauan(String tglPeninjauan) {
        this.tglPeninjauan = tglPeninjauan;
    }
}
