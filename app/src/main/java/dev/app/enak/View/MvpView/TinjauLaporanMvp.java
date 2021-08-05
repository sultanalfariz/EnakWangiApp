package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailLaporanResource;
import dev.app.enak.Model.DetailTernakResource;

public interface TinjauLaporanMvp {
    void PostSuccess();
    void PostFailed();
    void LoadData(List<DetailLaporanResource> detailLaporanResources);
    void DataNull();
}
