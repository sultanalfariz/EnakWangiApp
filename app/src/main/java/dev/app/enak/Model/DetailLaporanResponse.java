package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailLaporanResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DetailLaporanResource> detailLaporanResources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailLaporanResource> getData() {
        return detailLaporanResources;
    }

    public void setData(List<DetailLaporanResource> detailLaporanResources) {
        this.detailLaporanResources = detailLaporanResources;
    }
}
