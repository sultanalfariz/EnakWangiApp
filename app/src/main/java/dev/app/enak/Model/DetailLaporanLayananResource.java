package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailLaporanLayananResource {

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
    @SerializedName("jenis_obat")
    @Expose
    private String jenisObat;
    @SerializedName("obat")
    @Expose
    private String obat;
    @SerializedName("dosis")
    @Expose
    private String dosis;

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

    public String getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(String jenisObat) {
        this.jenisObat = jenisObat;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
}
