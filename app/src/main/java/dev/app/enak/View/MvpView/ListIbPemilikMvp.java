package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.ListIbResource;
import dev.app.enak.Model.ListLaporanResource;

public interface ListIbPemilikMvp {
    void LoadIbWait(List<ListIbResource> listIbResources);
    void LoadIbSelesai(List<ListIbResource> listIbResources);
    void LoadIbProses(List<ListIbResource> listIbResources);
    void DataEmpty(String message);
    void DataNull();
}
