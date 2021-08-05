package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTernakLayananResource {

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
