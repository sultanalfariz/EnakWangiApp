package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailIbResource {

    @SerializedName("id_ternak")
    @Expose
    private String idTernak;
    @SerializedName("nama_ternak")
    @Expose
    private String namaTernak;
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
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
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
    private List<DetailIbTanggapanResource> detailIbTanggapanResource = null;
    @SerializedName("ib")
    @Expose
    private List<DetailIbHasilResource> detailIbHasilResource = null;

    public String getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(String idTernak) {
        this.idTernak = idTernak;
    }

    public String getNamaTernak() {
        return namaTernak;
    }

    public void setNamaTernak(String namaTernak) {
        this.namaTernak = namaTernak;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public List<DetailIbTanggapanResource> getTanggapan() {
        return detailIbTanggapanResource;
    }

    public void setTanggapan(List<DetailIbTanggapanResource> detailIbTanggapanResource) {
        this.detailIbTanggapanResource = detailIbTanggapanResource;
    }

    public List<DetailIbHasilResource> getIb() {
        return detailIbHasilResource;
    }

    public void setIb(List<DetailIbHasilResource> detailIbHasilResource) {
        this.detailIbHasilResource = detailIbHasilResource;
    }
}
