package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.ListIbResource;

public interface ListIbPetugasMvp {
    void LoadIbWait(List<ListIbResource> listIbResources);
    void LoadIbTinjau(List<ListIbResource> listIbResources);
    void LoadIbProses(List<ListIbResource> listIbResources);
    void LoadIbSelesai(List<ListIbResource> listIbResources);
    void DataEmpty(String message);
    void DataNull();
}
