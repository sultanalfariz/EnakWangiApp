package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTernakSakitResource {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("id_laporan")
    @Expose
    private Integer idLaporan;
    @SerializedName("id_petugas")
    @Expose
    private Integer idPetugas;
    @SerializedName("diagnosa")
    @Expose
    private String diagnosa;
    @SerializedName("gejala")
    @Expose
    private String gejala;
    @SerializedName("morb_mort")
    @Expose
    private String morbMort;

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

    public Integer getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(Integer idLaporan) {
        this.idLaporan = idLaporan;
    }

    public Integer getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Integer idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public String getMorbMort() {
        return morbMort;
    }

    public void setMorbMort(String morbMort) {
        this.morbMort = morbMort;
    }

}
