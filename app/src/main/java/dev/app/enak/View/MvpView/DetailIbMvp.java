package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailIbResource;
import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.ListIbResource;

public interface DetailIbMvp {
    void LoadData(List<DetailIbResource> detailIbResources);
    void DataNull();
    void PostSuccess();
    void PostFail();
    void PostTglSuccess();
}
