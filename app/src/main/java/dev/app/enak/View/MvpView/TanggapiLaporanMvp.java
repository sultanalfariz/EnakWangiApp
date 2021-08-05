package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailLaporanResource;

public interface TanggapiLaporanMvp {
    void PostSuccess();
    void PostFailed();
    void LoadData(List<DetailLaporanResource> detailLaporanResources);
    void DataNull();
}
