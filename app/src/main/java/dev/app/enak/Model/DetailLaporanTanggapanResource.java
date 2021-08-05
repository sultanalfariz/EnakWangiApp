package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailLaporanTanggapanResource {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_laporan")
    @Expose
    private String idLaporan;
    @SerializedName("id_petugas")
    @Expose
    private Object idPetugas;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tgl_peninjauan")
    @Expose
    private String tglPeninjauan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTglPeninjauan() {
        return tglPeninjauan;
    }

    public void setTglPeninjauan(String tglPeninjauan) {
        this.tglPeninjauan = tglPeninjauan;
    }
}
