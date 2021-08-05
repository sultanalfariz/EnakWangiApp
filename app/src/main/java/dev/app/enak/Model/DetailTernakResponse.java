package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailTernakResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DetailTernakResource> detailTernakResources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailTernakResource> getData() {
        return detailTernakResources;
    }

    public void setData(List<DetailTernakResource> detailTernakResources) {
        this.detailTernakResources = detailTernakResources;
    }

}
