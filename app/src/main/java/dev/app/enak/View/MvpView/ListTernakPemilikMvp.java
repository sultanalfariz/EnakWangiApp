package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.ListTernakResource;

public interface ListTernakPemilikMvp {

    void LoadTernakWait(List<ListTernakResource> listTernakResources);
    void LoadTernakVerify(List<ListTernakResource> listTernakResources);
    void DataEmpty(String message);
    void DataNull();
}
