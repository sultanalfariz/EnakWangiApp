package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.ListLaporanResource;

public interface ListLaporanPetugasMvp {
    void LoadLaporanWait(List<ListLaporanResource> listLaporanResource);
    void LoadLaporanTinjau(List<ListLaporanResource> listLaporanResource);
    void LoadLaporanSelesai(List<ListLaporanResource> listLaporanResource);
    void DataEmpty(String message);
    void DataNull();
}
