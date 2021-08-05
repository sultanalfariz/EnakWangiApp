package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.ListLaporanResource;

public interface ListLaporanPemilikMvp {

    void LoadLaporan(List<ListLaporanResource> listLaporanResources);
    void LoadLaporanSelesai(List<ListLaporanResource> listLaporanResources);
    void DataEmpty(String message);
    void DataNull();
}
