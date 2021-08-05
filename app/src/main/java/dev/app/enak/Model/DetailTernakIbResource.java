package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTernakIbResource {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("id_permintaan_ib")
    @Expose
    private Integer idPermintaanIb;
    @SerializedName("id_petugas")
    @Expose
    private Integer idPetugas;
    @SerializedName("birahi")
    @Expose
    private String birahi;
    @SerializedName("tgl_suntik_ib")
    @Expose
    private String tglSuntikIb;
    @SerializedName("hamil")
    @Expose
    private String hamil;
    @SerializedName("lahir")
    @Expose
    private String lahir;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
    }

    public Integer getIdPermintaanIb() {
        return idPermintaanIb;
    }

    public void setIdPermintaanIb(Integer idPermintaanIb) {
        this.idPermintaanIb = idPermintaanIb;
    }

    public Integer getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Integer idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getBirahi() {
        return birahi;
    }

    public void setBirahi(String birahi) {
        this.birahi = birahi;
    }

    public String getTglSuntikIb() {
        return tglSuntikIb;
    }

    public void setTglSuntikIb(String tglSuntikIb) {
        this.tglSuntikIb = tglSuntikIb;
    }

    public String getHamil() {
        return hamil;
    }

    public void setHamil(String hamil) {
        this.hamil = hamil;
    }

    public String getLahir() {
        return lahir;
    }

    public void setLahir(String lahir) {
        this.lahir = lahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }
}
