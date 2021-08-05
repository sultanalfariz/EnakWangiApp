package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPetugasResource {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("ternak_wait")
    @Expose
    private Integer ternakWait;
    @SerializedName("ternak_tinjau")
    @Expose
    private Integer ternakTinjau;
    @SerializedName("ternak_verify")
    @Expose
    private Integer ternakVerify;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTernakWait() {
        return ternakWait;
    }

    public void setTernakWait(Integer ternakWait) {
        this.ternakWait = ternakWait;
    }

    public Integer getTernakTinjau() {
        return ternakTinjau;
    }

    public void setTernakTinjau(Integer ternakTinjau) {
        this.ternakTinjau = ternakTinjau;
    }

    public Integer getTernakVerify() {
        return ternakVerify;
    }

    public void setTernakVerify(Integer ternakVerify) {
        this.ternakVerify = ternakVerify;
    }
}
