package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.GetUserResource;
import dev.app.enak.Model.ListArtikelResource;

public interface HomeUserMvp {

    void loadData(GetUserResource getUserResources);
    void loadArtikel(List<ListArtikelResource> listArtikelResources);
    void DataEmpty(String message);
    void DataNull();
}
