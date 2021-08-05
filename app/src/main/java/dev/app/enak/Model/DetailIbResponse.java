package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailIbResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DetailIbResource> detailIbResources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailIbResource> getData() {
        return detailIbResources;
    }

    public void setData(List<DetailIbResource> detailIbResources) {
        this.detailIbResources = detailIbResources;
    }

}
