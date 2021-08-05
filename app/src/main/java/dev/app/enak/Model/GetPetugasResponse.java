package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPetugasResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private GetPetugasResource getPetugasResource;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GetPetugasResource getData() {
        return getPetugasResource;
    }

    public void setData(GetPetugasResource getPetugasResource) {
        this.getPetugasResource = getPetugasResource;
    }

}
