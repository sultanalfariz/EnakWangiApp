package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailLaporanResource {
    @SerializedName("id_ternak")
    @Expose
    private String idTernak;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("alamat_kandang")
    @Expose
    private String alamatKandang;
    @SerializedName("rt")
    @Expose
    private String rt;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("kel_kandang")
    @Expose
    private String kelKandang;
    @SerializedName("kec_kandang")
    @Expose
    private String kecKandang;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("tanggapan")
    @Expose
    private List<DetailLaporanTanggapanResource> detailLaporanTanggapanResource = null;
    @SerializedName("layanan")
    @Expose
    private List<DetailLaporanLayananResource> detailLaporanLayananResource = null;
    @SerializedName("riwayat")
    @Expose
    private List<DetailLaporanRiwayatResource> detailLaporanRiwayatResource = null;

    public String getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(String idTernak) {
        this.idTernak = idTernak;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getAlamatKandang() {
        return alamatKandang;
    }

    public void setAlamatKandang(String alamatKandang) {
        this.alamatKandang = alamatKandang;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getKelKandang() {
        return kelKandang;
    }

    public void setKelKandang(String kelKandang) {
        this.kelKandang = kelKandang;
    }

    public String getKecKandang() {
        return kecKandang;
    }

    public void setKecKandang(String kecKandang) {
        this.kecKandang = kecKandang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public List<DetailLaporanTanggapanResource> getTanggapan() {
        return detailLaporanTanggapanResource;
    }

    public void setTanggapan(List<DetailLaporanTanggapanResource> detailLaporanTanggapanResource) {
        this.detailLaporanTanggapanResource = detailLaporanTanggapanResource;
    }

    public List<DetailLaporanLayananResource> getLayanan() {
        return detailLaporanLayananResource;
    }

    public void setLayanan(List<DetailLaporanLayananResource> detailLaporanLayananResource) {
        this.detailLaporanLayananResource = detailLaporanLayananResource;
    }

    public List<DetailLaporanRiwayatResource> getRiwayat() {
        return detailLaporanRiwayatResource;
    }

    public void setRiwayat(List<DetailLaporanRiwayatResource> detailLaporanRiwayatResource) {
        this.detailLaporanRiwayatResource = detailLaporanRiwayatResource;
    }
}
