package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailIbTanggapanResource {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_permintaan_ib")
    @Expose
    private String idPermintaanIb;
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
