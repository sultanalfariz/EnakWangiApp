package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailArtikelResource {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_petugas")
    @Expose
    private Integer idPetugas;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("isi_artikel")
    @Expose
    private String isiArtikel;
    @SerializedName("gambar")
    @Expose
    private String gambar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Integer idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsiArtikel() {
        return isiArtikel;
    }

    public void setIsiArtikel(String isiArtikel) {
        this.isiArtikel = isiArtikel;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
