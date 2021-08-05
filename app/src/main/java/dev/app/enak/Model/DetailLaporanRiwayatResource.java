package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailLaporanRiwayatResource {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_ternak")
    @Expose
    private String idTernak;
    @SerializedName("id_laporan")
    @Expose
    private String idLaporan;
    @SerializedName("id_petugas")
    @Expose
    private Object idPetugas;
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

    public String getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(String idTernak) {
        this.idTernak = idTernak;
    }

    public String getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(String idLaporan) {
        this.idLaporan = idLaporan;
    }

    public Object getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Object idPetugas) {
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
