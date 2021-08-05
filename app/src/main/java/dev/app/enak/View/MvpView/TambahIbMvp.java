package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.ListTernakResource;

public interface TambahIbMvp {
    void PostSuccess();
    void PostFailed();
    void LoadDataTernak(List<ListTernakResource> listTernakResources);
    void DataEmpty(String message);
}
