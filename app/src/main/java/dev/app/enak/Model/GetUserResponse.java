package dev.app.enak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private GetUserResource getUserResource;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GetUserResource getData() {
        return getUserResource;
    }

    public void setData(GetUserResource getUserResource) {
        this.getUserResource = getUserResource;
    }

}
