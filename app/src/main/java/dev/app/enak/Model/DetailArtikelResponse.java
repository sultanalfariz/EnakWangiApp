package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailArtikelResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private DetailArtikelResource detailArtikelResource;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DetailArtikelResource getData() {
        return detailArtikelResource;
    }

    public void setData(DetailArtikelResource detailArtikelResource) {
        this.detailArtikelResource = detailArtikelResource;
    }

}
