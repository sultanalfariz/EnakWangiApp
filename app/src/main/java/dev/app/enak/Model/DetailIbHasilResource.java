package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailIbHasilResource {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_ternak")
    @Expose
    private String idTernak;
    @SerializedName("id_permintaan_ib")
    @Expose
    private String idPermintaanIb;
    @SerializedName("id_petugas")
    @Expose
    private Object idPetugas;
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

    public String getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(String idTernak) {
        this.idTernak = idTernak;
    }

    public String getIdPermintaanIb() {
        return idPermintaanIb;
    }

    public void setIdPermintaanIb(String idPermintaanIb) {
        this.idPermintaanIb = idPermintaanIb;
    }

    public Object getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Object idPetugas) {
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
