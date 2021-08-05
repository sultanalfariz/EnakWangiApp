package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailTernakResource {

    @SerializedName("nama_ternak")
    @Expose
    private String namaTernak;
    @SerializedName("jenis_ternak")
    @Expose
    private String jenisTernak;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("kode_pejantan")
    @Expose
    private String kodePejantan;
    @SerializedName("kode_induk")
    @Expose
    private String kodeInduk;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("tgl_peninjauan")
    @Expose
    private String tglPeninjauan;
    @SerializedName("gambar_depan")
    @Expose
    private String gambarDepan;
    @SerializedName("alamat_kandang")
    @Expose
    private String alamatKandang;
    @SerializedName("kel_kandang")
    @Expose
    private String kelKandang;
    @SerializedName("kec_kandang")
    @Expose
    private String kecKandang;
    @SerializedName("rt_kdg")
    @Expose
    private String rtKdg;
    @SerializedName("rw_kdg")
    @Expose
    private String rwKdg;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("rt")
    @Expose
    private String rt;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("riwayat")
    @Expose
    private List<DetailTernakSakitResource> detailTernakSakitResources = null;
    @SerializedName("ib")
    @Expose
    private List<DetailTernakIbResource> detailTernakIbResources = null;
    @SerializedName("layanan")
    @Expose
    private List<DetailTernakLayananResource> detailTernakLayananResources = null;

    public String getNamaTernak() {
        return namaTernak;
    }

    public void setNamaTernak(String namaTernak) {
        this.namaTernak = namaTernak;
    }

    public String getJenisTernak() {
        return jenisTernak;
    }

    public void setJenisTernak(String jenisTernak) {
        this.jenisTernak = jenisTernak;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getKodePejantan() {
        return kodePejantan;
    }

    public void setKodePejantan(String kodePejantan) {
        this.kodePejantan = kodePejantan;
    }

    public String getKodeInduk() {
        return kodeInduk;
    }

    public void setKodeInduk(String kodeInduk) {
        this.kodeInduk = kodeInduk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getTglPeninjauan() {
        return tglPeninjauan;
    }

    public void setTglPeninjauan(String tglPeninjauan) {
        this.tglPeninjauan = tglPeninjauan;
    }

    public String getGambarDepan() {
        return gambarDepan;
    }

    public void setGambarDepan(String gambarDepan) {
        this.gambarDepan = gambarDepan;
    }

    public String getAlamatKandang() {
        return alamatKandang;
    }

    public void setAlamatKandang(String alamatKandang) {
        this.alamatKandang = alamatKandang;
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

    public String getRtKdg() {
        return rtKdg;
    }

    public void setRtKdg(String rtKdg) {
        this.rtKdg = rtKdg;
    }

    public String getRwKdg() {
        return rwKdg;
    }

    public void setRwKdg(String rwKdg) {
        this.rwKdg = rwKdg;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public List<DetailTernakSakitResource> getRiwayat() {
        return detailTernakSakitResources;
    }

    public void setRiwayat(List<DetailTernakSakitResource> detailTernakSakitResources) {
        this.detailTernakSakitResources = detailTernakSakitResources;
    }

    public List<DetailTernakIbResource> getIb() {
        return detailTernakIbResources;
    }

    public void setIb(List<DetailTernakIbResource> detailTernakIbResources) {
        this.detailTernakIbResources = detailTernakIbResources;
    }

    public List<DetailTernakLayananResource> getLayanan() {
        return detailTernakLayananResources;
    }

    public void setLayanan(List<DetailTernakLayananResource> detailTernakLayananResources) {
        this.detailTernakLayananResources = detailTernakLayananResources;
    }
}
