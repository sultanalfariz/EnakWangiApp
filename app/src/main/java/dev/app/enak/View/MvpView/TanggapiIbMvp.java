package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Model.DetailLaporanResource;

public interface TanggapiIbMvp {
    void PostSuccess();
    void PostFailed();
    void LoadData(List<DetailIbResource> detailIbResource);
    void DataNull();
}
